package io.github.qishr.cascara.ui.form;

import io.github.qishr.cascara.schema.structure.SchemaNode;

public interface FieldValidator {
    boolean performValidation(Object value, String path, SchemaNode schema);
}
