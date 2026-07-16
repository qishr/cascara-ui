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

import javafx.beans.InvalidationListener;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public abstract class AbstractFormComponent extends VBox {
    protected final SimpleStringProperty query = new SimpleStringProperty(this, "query");
    protected FieldLabel description;
    protected FieldLabel title;

    protected InvalidationListener listener = i -> internalChange();

    protected AbstractFormComponent() {
        HBox.setHgrow(this, Priority.ALWAYS);
        setSpacing(4);
        query.addListener(listener);
    }

    public void setTitle(FieldLabel title) {
        if (this.title != null) {
            this.title.textProperty().removeListener(listener);
            this.title.queryProperty().bind(query);
        }
        this.title = title;
        if (title != null) {
            title.textProperty().addListener(listener);
            title.queryProperty().bind(query);
        }
        performLayout();
    }

    public void setDescription(FieldLabel description) {
        if (this.description != null) {
            this.description.textProperty().removeListener(listener);
            this.description.queryProperty().unbind();
        }
        this.description = description;
        if (description != null) {
            description.textProperty().addListener(listener);
            description.queryProperty().bind(query);
        }
        performLayout();
    }

    protected void internalChange() {
        updateSearchHighlight();
        onTextChanged();
    }

    protected abstract void onTextChanged();
    protected abstract void performLayout();

    public String getQuery() {
        return query.get();
    }

    protected void updateSearchHighlight() {
        if (title != null) {
            title.formatText();
        }
        if (description != null) {
            description.formatText();
        }
    }
}
