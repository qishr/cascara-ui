// License & Terms
//
// This file is part of **Cascara Examples**.
//
// **Cascara Examples** are free software: you can redistribute
// them and/or modify them without restriction under the terms of
// the MIT License.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// MIT License for more details.

package io.github.qishr.cascara.ui.demo;

import io.github.qishr.cascara.schema.annotation.ContentMediaType;
import io.github.qishr.cascara.schema.annotation.SchemaDefinition;
import io.github.qishr.cascara.schema.annotation.SchemaProperty;
import io.github.qishr.cascara.schema.constraint.StringConstraint;
import io.github.qishr.cascara.ui.data.ObservableObject;
import javafx.beans.property.ObjectProperty;

@SchemaDefinition
public class SampleData extends ObservableObject {

    @SchemaProperty(titleKey = "title.sample-enum", descriptionKey = "text.sample-description", enumKey = "sample-enum")
    @StringConstraint(options = {"ONE", "TWO", "THREE"})
    public ObjectProperty<SampleEnum> sampleEnum;

    @SchemaProperty(titleKey = "title.sample-text", descriptionKey = "text.sample-description")
    public ObjectProperty<String> sampleText;

    @SchemaProperty
    @ContentMediaType("text/markdown")
    @StringConstraint(maxLength = 131072)
    public ObjectProperty<String> sampleMarkdown;

    public SampleData() {
        sampleText.set("Text");
        sampleMarkdown.set("# Markdown");
    }

    public static enum SampleEnum {
        ONE,
        TWO,
        THREE,
        FOUR
    }
}
