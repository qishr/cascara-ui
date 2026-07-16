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

import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;

public class EditableLabel extends HBox {
    Label label = new Label();
    TextField textField = new TextField();

    private OnChangeHandler onChange = null;
    public interface OnChangeHandler { void onChange(String value); }
    public void setOnChange(OnChangeHandler onChange) { this.onChange = onChange; }

    public StringProperty textProperty() { return label.textProperty(); }

    public void onChange(String value) {
        if (onChange != null) {
            onChange.onChange(value);
        }
    }

    public EditableLabel(String text) {
        this();
        setText(text);
    }

    public EditableLabel() {
        super();
        label.setOnMouseClicked(mouse -> beginEditing());
        label.styleProperty().addListener((obs, old, val) -> {
            setLabelAppearance();
        });
        setLabelAppearance();
        textField.setPadding(new Insets(9.5));
        textField.setOnKeyTyped(key -> {
            if (key.getCharacter().equals("\r") || key.getCharacter().equals("\t")) {
                finishEditing();
            }
        });
        textField.focusedProperty().addListener((obs,old,val) -> {
            if (!val) {
                finishEditing();
            }
        });
        getChildren().add(label);
        setAlignment(Pos.CENTER_LEFT);
        HBox.setHgrow(label, Priority.ALWAYS);
        HBox.setHgrow(textField, Priority.ALWAYS);
    }

    private void setLabelAppearance() {
        label.setCursor(Cursor.TEXT);
        label.setPadding(new Insets(9.5));
        label.setAlignment(Pos.CENTER_LEFT);
        label.setMouseTransparent(false);
        label.setPickOnBounds(true);
        label.setBackground(Background.fill(Color.RED));
    }

    private void beginEditing() {
        textField.setText(label.getText());
        getChildren().clear();
        getChildren().add(textField);
        textField.requestFocus();
    }

    private void finishEditing() {
        label.setText(textField.getText());
        getChildren().clear();
        getChildren().add(label);
        onChange(textField.getText());
    }

    public void setText(String text) {
        label.setText(text);
        textField.setText(text);
    }

    public String getText() {
        return textField.getText();
    }
}
