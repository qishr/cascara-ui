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


package io.github.qishr.cascara.ui.control;

import java.io.File;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;

import io.github.qishr.cascara.schema.structure.SchemaNode;
import io.github.qishr.cascara.ui.form.FieldMetadata;
import javafx.beans.property.Property;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.FileChooser;

public class FilePicker extends HBox {
    private final TextField pathField = new TextField();
    private final Button browseButton = new Button("...");
    private final SchemaNode schema;
    private URI baseUri = null; // The URI of the document being edited

    public FilePicker(Property<String> node, String[] extensions, FieldMetadata meta) {
        this.schema = meta.getSchema();
        this.setSpacing(5);
        this.getStyleClass().add("file-picker-field");

        pathField.setPromptText("Select file...");
        HBox.setHgrow(pathField, Priority.ALWAYS);

        pathField.textProperty().addListener((obs, old, val) -> node.setValue(val));

        Object currentVal = node.getValue();
        pathField.setText(currentVal != null ? currentVal.toString() : "");

        browseButton.setOnAction(e -> {
            FileChooser chooser = new FileChooser();
            if (extensions != null && extensions.length > 0) {
                chooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter("Supported Files", extensions)
                );
            }

            // Set initial directory based on current text if it's a valid path
            try {
                Path current = Path.of(pathField.getText());
                if (Files.exists(current)) {
                    chooser.setInitialDirectory(current.getParent().toFile());
                }
            } catch (Exception ignored) {}

            File selected = chooser.showOpenDialog(this.getScene().getWindow());
            if (selected != null) {
                boolean mustBeAbsolute = schema.getBooleanOption("absolute", false);

                if (mustBeAbsolute) {
                    pathField.setText(selected.getAbsolutePath());
                } else {
                    pathField.setText(relativize(selected));
                }
            }
        });

        this.getChildren().addAll(pathField, browseButton);
    }

    public FilePicker(Property<?> node, boolean mustBeAbsolute, String[] extensions) {
        // this.schema = node.getSchema();
        schema = null;
        this.setSpacing(5);
        this.getStyleClass().add("file-picker-field");

        pathField.setPromptText("Select file...");
        HBox.setHgrow(pathField, Priority.ALWAYS);

        // pathField.textProperty().addListener((obs, old, val) -> node.setValue(val));

        Object currentVal = node.getValue();
        pathField.setText(currentVal != null ? currentVal.toString() : "");

        browseButton.setOnAction(e -> {
            FileChooser chooser = new FileChooser();
            if (extensions != null && extensions.length > 0) {
                chooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter("Supported Files", extensions)
                );
            }

            // Set initial directory based on current text if it's a valid path
            try {
                Path current = Path.of(pathField.getText());
                if (Files.exists(current)) {
                    chooser.setInitialDirectory(current.getParent().toFile());
                }
            } catch (Exception ignored) {}

            File selected = chooser.showOpenDialog(this.getScene().getWindow());
            if (selected != null) {
                // boolean mustBeAbsolute = schema.getBooleanOption("absolute", false);

                if (mustBeAbsolute) {
                    pathField.setText(selected.getAbsolutePath());
                } else {
                    pathField.setText(relativize(selected));
                }
            }
        });

        this.getChildren().addAll(pathField, browseButton);
    }

    public StringProperty textProperty() {
        return pathField.textProperty();
    }

    public String getText() {
        return pathField.getText();
    }

    public void setText(String text) {
        pathField.setText(text);
    }

    public void setBaseUri(URI uri) {
        this.baseUri = uri;
    }

    private String relativize(File selected) {
        if (baseUri == null || !baseUri.getScheme().equalsIgnoreCase("file")) {
            return selected.getAbsolutePath();
        }

        try {
            Path docPath = Path.of(baseUri).toAbsolutePath().getParent();
            Path selectedPath = selected.toPath().toAbsolutePath();

            // .normalize() helps resolve any ".." or "." in the paths before relativizing
            return docPath.relativize(selectedPath).normalize().toString().replace("\\", "/");
        } catch (Exception e) {
            // Fallback for different drives or invalid URIs
            return selected.getAbsolutePath();
        }
    }
}