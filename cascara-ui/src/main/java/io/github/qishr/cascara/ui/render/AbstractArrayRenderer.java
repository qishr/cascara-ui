package io.github.qishr.cascara.ui.render;

import io.github.qishr.cascara.common.lang.type.PrimitiveType;

public abstract class AbstractArrayRenderer extends AbstractRenderer {
    protected AbstractArrayRenderer(String contentType, PrimitiveType schemaType, String format) {
        super(contentType, schemaType, format);
    }
}
