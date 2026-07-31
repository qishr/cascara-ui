// # License & Terms
//
// This file is part of **Cascara**.
//
// **Cascara** is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program. If not, see <https://www.gnu.org/licenses/>.
//
// ---
//
// ## Special Runtime Exception
//
// As a special exception, the copyright holders of this library give you
// permission to link this library with independent modules to produce an
// executable, regardless of the license terms of these independent modules,
// and to copy and distribute the resulting executable under terms of your
// choice, provided that you also meet, for each linked independent module,
// the terms and conditions of the license of that module.
//
// An independent module is a module which is not derived from or based on
// this library. If you modify this library, you may extend this exception
// to your version of the library, but you are not obligated to do so. If
// you do not wish to do so, delete this exception statement from your
// version.


package io.github.qishr.cascara.ui.schema;

import io.github.qishr.cascara.common.diagnostic.Diagnostic;
import io.github.qishr.cascara.common.diagnostic.SilentCollectingReporter;
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
    private YamlScalar createMockScalar(Object value, int line, int col) {
        YamlScalar node = new YamlScalar(value, ScalarStyle.PLAIN, null);
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
        YamlMap dataNode = new YamlMap();
        YamlScalar key = createMockScalar("age", 5, 1);
        YamlScalar value = createMockScalar(16, 5, 10);
        dataNode.put(new YamlMapEntry(key, value));

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
        YamlSequence seqNode = new YamlSequence();
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
