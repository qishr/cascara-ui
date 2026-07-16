package io.github.qishr.cascara.ui.data;

import io.github.qishr.cascara.common.lang.type.PrimitiveType;

public class PropertyMetadata {
    private PrimitiveType schemaType;
    private String mediaType;
    private boolean isDeclaredProperty;

    public PropertyMetadata(PrimitiveType schemaType, String mediaType, boolean isDeclaredProperty) {
        this.schemaType = schemaType;
        this.mediaType = mediaType;
        this.isDeclaredProperty = isDeclaredProperty;
    }

    public PrimitiveType getSchemaType() { return schemaType; }
    public String getMediaType() { return mediaType; }
    public boolean isDeclaredProperty() { return isDeclaredProperty; }
}
