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


package io.github.qishr.cascara.ui.form;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Consumer;

import io.github.qishr.cascara.common.data.TabularData;
import io.github.qishr.cascara.common.diagnostic.code.GenericDiagnosticCode;
import io.github.qishr.cascara.schema.SchemaKeyword;
import io.github.qishr.cascara.common.lang.type.PrimitiveType;
import io.github.qishr.cascara.schema.rule.EnumRule;
import io.github.qishr.cascara.schema.rule.ValidationRule;
import io.github.qishr.cascara.schema.structure.ArraySchemaNode;
import io.github.qishr.cascara.schema.structure.SchemaNode;
import io.github.qishr.cascara.ui.data.UiDataException;
import io.github.qishr.cascara.ui.option.OptionProvider;
import io.github.qishr.cascara.ui.option.OptionProviderRegistry;
import io.github.qishr.cascara.ui.render.RendererFactory;
import io.github.qishr.cascara.ui.render.Renderers;
import io.github.qishr.cascara.ui.schema.DisplayColumns;
import io.github.qishr.cascara.ui.schema.DisplayToggle;
import io.github.qishr.cascara.ui.schema.Hidden;
import io.github.qishr.cascara.ui.schema.ItemsEditableConstraint;
import io.github.qishr.cascara.ui.schema.OptionConstraint;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class FieldMetadata {
    private final StringProperty fieldName = new SimpleStringProperty();
    private final StringProperty title = new SimpleStringProperty();
    private final SchemaNode fieldSchema;
    private final PrimitiveType schemaType;
    private final String format;
    private boolean hidden = false;
    private boolean displayToggle = false;
    private String providerParameter = null;
    private OptionProvider optionProvider;
    private final List<String> enumValues;
    private final String contentType;
    private SchemaNode itemsSchema;
    private Map<String,Property<?>> dataContext;
    protected boolean allowEdit = true;
    private boolean allowAdd = false;
    private boolean allowDelete = false;
    private Collection<ColumnMeta> columnMetas;
    private Runnable addRowHandler;
    private Consumer<TabularData> removeRow;
    private Runnable onChange;
    private boolean isArray = false;
    private boolean isObject = false;

    // private final List<RendererFactory<?>> rendererFactories;
    private final RendererFactory rendererFactory;
    private Renderers renderers;

    public static class ColumnMeta {
        private String name = "";
        private SchemaNode schema;

        public ColumnMeta(String name, SchemaNode schema) {
            this.name = name;
            this.schema = schema;
        }

        public String getName() { return name; }
        public SchemaNode getSchema() { return schema; }
    }

    public FieldMetadata(String fieldName, SchemaNode fieldSchema, RendererFactory rendererFactory) {
        this. fieldSchema = fieldSchema;
        this.fieldName.set(fieldName);
        this.rendererFactory = rendererFactory;
        String optionProviderId = null;

        if (fieldName == null) {
            throw new UiDataException(GenericDiagnosticCode.UNEXPECTED_NULL_PARAMETER, "fieldName", "FieldMetadata");
        }

        if (fieldSchema == null) {
            schemaType = PrimitiveType.STRING;
            format = null;
            providerParameter = null;
            enumValues = null;
            contentType = null;
        } else {
            schemaType = fieldSchema.getType();
            format = fieldSchema.getFormat();
            enumValues = findEnumValues(fieldSchema);
            title.set(fieldSchema.getTitle());

            if (fieldSchema.getExtension(Hidden.UI_HIDDEN) instanceof Boolean b) {
                hidden = b;
            }
            if (fieldSchema.getExtension(OptionConstraint.UI_OPTION_PROVIDER) instanceof Map option) {
                if (option.get(OptionConstraint.PARAMETER) instanceof String s) {
                    providerParameter = s;
                }
                if (option.get(OptionConstraint.NAME) instanceof String s) {
                    optionProviderId = s;
                }
            }
            if (fieldSchema.getExtension(DisplayToggle.UI_DISPLAY_TOGGLE) instanceof Boolean b) {
                displayToggle = b;
            }
            if (fieldSchema instanceof ArraySchemaNode array) {
                isArray = true;

                itemsSchema = array.getItemSchema();

                contentType = getItemsMediaType(array);

                this.allowAdd = !array.isReadOnly();
                this.allowDelete = !array.isReadOnly();

                if (fieldSchema.getExtension(ItemsEditableConstraint.UI_ITEMS_EDITABLE) instanceof Boolean itemsEditable) {
                    this.allowEdit = itemsEditable;
                }

                columnMetas = new ArrayList<>();
                if (fieldSchema.getExtension(DisplayColumns.UI_DISPLAY_COLUMNS) instanceof List displayColumns) {
                    for (Object object : displayColumns) {
                        if (object instanceof String columnName) {
                            SchemaNode columnSchema = itemsSchema.getProperty(columnName);
                            ColumnMeta columnMeta = new ColumnMeta(columnName, columnSchema);
                            columnMetas.add(columnMeta);
                        }
                    }
                } else {
                    for (Entry<String, SchemaNode> entry : itemsSchema.getProperties().entrySet()) {
                        ColumnMeta columnMeta = new ColumnMeta(entry.getKey(), entry.getValue());
                        columnMetas.add(columnMeta);
                    }
                }
            } else {
                contentType = fieldSchema.getContentMediaType();
                allowEdit = !fieldSchema.isReadOnly();
            }
            if (fieldSchema.getType() == PrimitiveType.OBJECT) {
                isObject = true;
            }
        }

        if (optionProviderId != null) {
            optionProvider = OptionProviderRegistry.get(optionProviderId);
        } else {
            optionProvider = null;
        }
    }

    public void setRenderers(Renderers renderers) {
        this.renderers = renderers;
    }

    public Renderers getRenderers() {
        return renderers;
    }

    public StringProperty nameProperty() { return fieldName; }
    public StringProperty titleProperty() { return title; }

    public void setDataContext(Map<String,Property<?>> context) { this.dataContext = context; }
    public void setColumnMetaList(Collection<ColumnMeta> v) { columnMetas = v; }
    public void setAddRowHandler(Runnable addRow) { this.addRowHandler = addRow; }
    public void setRemoveRowHandler(Consumer<TabularData> removeRow) { this.removeRow = removeRow; }
    public void setOnChange(Runnable onChange) { this.onChange = onChange; }
    public FieldMetadata setAllowEdit(boolean v) { allowEdit = v; return this; }
    public void setTitle(String v) { title.set(v); }
    public void setOptionProvider(OptionProvider v) { optionProvider = v; }

    public boolean allowEdit() { return allowEdit; }
    public boolean allowDelete() { return allowDelete; }
    public boolean allowAdd() { return allowAdd; }
    public String getName() { return fieldName.get(); }
    public SchemaNode getSchema() { return fieldSchema; }
    public SchemaNode getItemsSchema() { return itemsSchema; }
    public PrimitiveType getSchemaType() { return schemaType; }
    public String getFormat() { return format; }
    public OptionProvider getOptionProvider() { return optionProvider; }
    public String getProviderParameter() { return providerParameter; }
    public List<String> getEnumValues() { return enumValues; }
    public String getContentType() { return contentType; }
    public Map<String,Property<?>> getDataContext() { return dataContext; }
    public Collection<ColumnMeta> getColumnMetaList() { return columnMetas; }
    public Runnable getAddRowHandler() { return addRowHandler; }
    public Consumer<TabularData> getRemoveRowHandler() { return removeRow; }
    public Runnable getOnChange() { return onChange; }
    public String getTitle() { return title.get(); }

    public RendererFactory getRendererFactory() { return rendererFactory; }

    public boolean isHidden() { return hidden; }
    public boolean hasOptionProvider() { return optionProvider != null; }
    public boolean hasProviderParameter() { return providerParameter != null && !providerParameter.isEmpty(); }
    public boolean hasDisplayToggle() { return displayToggle; }

    public boolean isArrayField() { return isArray; }
    public boolean isObjectField() { return isObject; }
    public boolean hasMediaType() { return contentType != null && !contentType.isEmpty(); }

    public boolean isStringField() {
        return PrimitiveType.STRING == schemaType;
    }

    public boolean isBooleanField() { return schemaType == PrimitiveType.BOOLEAN; }

    public boolean isEnumField() { return enumValues != null && !enumValues.isEmpty(); }

    //
    //
    //






// TODO REmoves these


    private List<String> findEnumValues(SchemaNode schema) {
        if (schema == null) return null;

        // TODO: This seems wrong.
        // Should it not be: SchemaKeyword.TYPE.asString().equals(fieldName.get())
        if ("type".equals(fieldName.get()) && SchemaKeyword.exists(fieldName.get())) {
            SchemaNode meta = fieldSchema.getMetaSchema();
            if (meta != null && isMetaSchema(meta.getOriginUri())) {
                return SchemaKeyword.TYPE.suggestions();
            }
        }

        for (ValidationRule rule : schema.getRules()) {
            if (rule instanceof EnumRule enumRule) {
                return enumRule.getAllowedValues();
            }
        }
        return null;
    }

    // TODO: This is dodgy. Needs drastically improved.
    private boolean isMetaSchema(URI uri) {
        if (uri == null) return false;
        String uriString = uri.toString();
        return uriString.contains("json-schema.org") ||
                uriString.contains("cema-meta") ||
                uriString.contains("schema-service/schema");
    }









    private String getItemsMediaType(SchemaNode array) {
        if (itemsSchema == null) {
            return null;
        }
        String itemsMediaType = itemsSchema.getContentMediaType();
        if (array.getContentMediaType() != null && !array.getContentMediaType().isEmpty()) {
            itemsMediaType = array.getContentMediaType();
        }
        return itemsMediaType;
    }

}
