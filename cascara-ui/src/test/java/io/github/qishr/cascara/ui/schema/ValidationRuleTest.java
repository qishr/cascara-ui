package io.github.qishr.cascara.ui.schema;

import io.github.qishr.cascara.common.diagnostic.Diagnostic;
import io.github.qishr.cascara.common.diagnostic.SilentCollectingReporter;
import io.github.qishr.cascara.common.lang.util.QuoteStyle;
import io.github.qishr.cascara.schema.structure.ArraySchemaNode;
import io.github.qishr.cascara.schema.structure.AbstractSchemaNode;
import io.github.qishr.cascara.schema.structure.ObjectSchemaNode;
import io.github.qishr.cascara.schema.structure.ScalarSchemaNode;
import io.github.qishr.cascara.common.lang.type.PrimitiveType;
import io.github.qishr.cascara.schema.rule.MinValueRule;
import io.github.qishr.cascara.schema.rule.RegexRule;
import io.github.qishr.cascara.lang.yaml.ast.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

class ValidationRuleTest {

    /**
     * Helper to create a location-aware Scalar node for testing.
     */
    private YamlScalarNode createMockScalar(Object value, int line, int col) {
        YamlScalarNode node = new YamlScalarNode(line, col, PrimitiveType.of(value), String.valueOf(value), String.valueOf(value), QuoteStyle.PLAIN, null);
        // Create a token so the node has coordinate metadata
        // YamlToken mockToken = new YamlToken(null, String.valueOf(value), value, 0, line, col);
        // node.setStartToken(mockToken);
        return node;
    }

    @Test
    void testNestedValidation() {
        // Setup Schema: User { age: Integer(min: 18) }
        ObjectSchemaNode userSchema = new ObjectSchemaNode(null);
        AbstractSchemaNode age = new ScalarSchemaNode(PrimitiveType.INTEGER, null);
        age.addRule(new MinValueRule(18));
        userSchema.addProperty("age", age);

        // Setup Data AST: { age: 16 } at line 5, col 10
        YamlMapNode dataNode = new YamlMapNode();
        YamlScalarNode key = createMockScalar("age", 5, 1);
        YamlScalarNode value = createMockScalar(16, 5, 10);
        dataNode.put(new YamlMapEntryNode(key, value));

        List<Diagnostic> errorMessages = new ArrayList<>();
        SilentCollectingReporter reporter = new SilentCollectingReporter();
        reporter.setProblemCollector(p -> errorMessages.add(p));

        boolean valid = userSchema.validate(dataNode, "#/user", reporter);

        String path = errorMessages.get(0).getUri().toString();
        assertTrue(valid);
        assertEquals("#/user/age", path);
        assertEquals(5, errorMessages.get(0).getLine());
        assertEquals(10, errorMessages.get(0).getColumn());
    }

    @Test
    void testArrayValidation() {
        // Setup Schema: tags: Array<String(regex: ^[a-z]+$)>
        ArraySchemaNode tagsSchema = new ArraySchemaNode(null);
        AbstractSchemaNode tagItem = new ScalarSchemaNode(PrimitiveType.STRING, null);
        tagItem.addRule(new RegexRule("^[a-z]+$"));
        tagsSchema.setItemTemplate(tagItem);

        // Setup Data AST: ["valid", "INVALID123"]
        YamlSequenceNode seqNode = new YamlSequenceNode();
        seqNode.add(createMockScalar("valid", 1, 1));
        seqNode.add(createMockScalar("INVALID123", 2, 5));

        List<Diagnostic> result = new ArrayList<>();
        SilentCollectingReporter reporter = new SilentCollectingReporter();
        reporter.setProblemCollector(p -> result.add(p));

        // ValidationResult result = new ValidationResult();
        tagsSchema.validate(seqNode, "#/tags", reporter);

        assertFalse(result.isEmpty());
        // Verify we caught the second element specifically
        Diagnostic error = result.getFirst();
        String path = error.getUri().toString();
        assertEquals("#/tags[1]", path);
        assertEquals(2, error.getLine());
        assertEquals(5, error.getColumn());
    }
}
