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


package io.github.qishr.cascara.ui.option;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.github.qishr.cascara.schema.rule.EnumRule;
import io.github.qishr.cascara.schema.rule.ValidationRule;
import io.github.qishr.cascara.schema.structure.SchemaNode;
import javafx.beans.property.Property;

public class EnumOptionProvider extends AbstractOptionProvider {
    public static final String NAME = "enum";
    // private List<String> enumValues;
    private Option activeOption;
    private List<Option> options = new ArrayList<>();

    public EnumOptionProvider(SchemaNode schema, String initialStringValue) {
        super(NAME, null, null, null);
        // this.enumValues = findEnumValues(schema);
        // if (enumValues == null) {
        //     throw new UiSchemaException(UiDiagnosticCode.EMPTY_ENUM);
        // }
        activeOption = populateEnumOptions(options, schema, initialStringValue);
    }

    public EnumOptionProvider(SchemaNode schema, Enum<?> initialValue) {
        this(schema, initialValue == null ? null : initialValue.toString());
        // super(NAME, null, null, null);
        // this.enumValues = findEnumValues(schema);
        // if (enumValues == null) {
        //     throw new UiSchemaException(UiDiagnosticCode.EMPTY_ENUM);
        // }
        // String initialValueString = initialValue == null ? null : initialValue.toString();

        // activeOption = populateEnumOptions(options, schema, initialValueString);

        // Localization.localeProperty().addListener((obs, oldSet, newSet) -> {
        //     listeners.forEach(Runnable::run);
        // });
    }

    // TODO: setSchema and setInitialValue

    public Option getOption(Enum<?> enumValue) {
        String enumValueString = enumValue.toString();
        for (Option option : options) {
            if (option.getOptionId().equals(enumValueString)) {
                return option;
            }
        }
        return null;
    }

    public static Option populateEnumOptions(List<Option> options, SchemaNode schema, String initialValueString) {
        options.clear();
        Option activeOption = null;
        List<String> strings = findEnumValues(schema);
        for (String item : strings) {
            SimpleStringOption option;

            if (schema.getExtension("x-i18n-enum") instanceof String enumKey) {
                String key = "enum." + enumKey + "." + item;
                option = new SimpleStringOption(item, item, key);
            } else {
                option = new SimpleStringOption(item, item);
            }

            if (initialValueString != null && initialValueString.equals(item)) {
                activeOption = option;
            }

            options.add(option);
        }
        return activeOption;
    }

    private static List<String> findEnumValues(SchemaNode schema) {
        if (schema == null) return null;

        // // TODO: This seems wrong.
        // // Should it not be: SchemaKeyword.TYPE.asString().equals(fieldName.get())
        // if ("type".equals(fieldName.get()) && SchemaKeyword.exists(fieldName.get())) {
        //     SchemaNode meta = fieldSchema.getMetaSchema();
        //     if (meta != null && isMetaSchema(meta.getOriginUri())) {
        //         return SchemaKeyword.TYPE.suggestions();
        //     }
        // }

        for (ValidationRule rule : schema.getRules()) {
            if (rule instanceof EnumRule enumRule) {
                return enumRule.getAllowedValues();
            }
        }
        return null;
    }

    // // TODO: This is dodgy. Needs drastically improved.
    // private boolean isMetaSchema(URI uri) {
    //     if (uri == null) return false;
    //     String uriString = uri.toString();
    //     return uriString.contains("json-schema.org") ||
    //             uriString.contains("cema-meta") ||
    //             uriString.contains("schema-service/schema");
    // }

    @Override
    public Option getActiveOption(Map<String,Property<?>> contextData, String parameter) {
        return activeOption;
    }

    @Override
    public List<Option> getOptions(Map<String,Property<?>> contextData, String parameter) {
        return options;
    }
}

