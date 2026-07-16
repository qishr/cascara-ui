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

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class Section extends AbstractFormComponent {

    private Label arrow = new Label("▼ ");
    private HBox collapsibleArea = null;

    public Section() {
        this(null);
    }

    public Section(FieldLabel title) {
        super();
        this.title = title;
        setPadding(new Insets(4, 4, 4, 4));
        HBox.setHgrow(this, Priority.ALWAYS);
    }

    public Section(FieldLabel title, int depth) {
        super();
        this.title = title;
        setPadding(new Insets(4, 4, 4, 4));
    }

    public FieldLabel getTitle() {
        return title;
    }

    @Override
    protected void onTextChanged() {}

    @Override
    protected void performLayout() {
        title.setHeading(true);

        // TODO: This was in the old code. Make it work again.
        // title.getStyleClass().add(StucturalEditorStyle.CATEGORY_HEADING);

        List<Node> nodes = new ArrayList<>();

        if (collapsibleArea == null || collapsibleArea.getChildren().isEmpty()) {
            getChildren().setAll(title);
            if (description != null) {
                getChildren().setAll(description);
            }
        } else {
            HBox headerArea = new HBox();

            arrow.setStyle("-fx-font-family: 'monospace'; -fx-cursor: hand;");
            headerArea.setCursor(Cursor.HAND);
            headerArea.getChildren().add(arrow);
            headerArea.getChildren().add(title);

            // Toggle logic
            headerArea.setOnMouseClicked(e -> {
                boolean isVisible = collapsibleArea.isVisible();
                collapsibleArea.setVisible(!isVisible);
                collapsibleArea.setManaged(!isVisible);
                arrow.setText(!isVisible ? "▼ " : "▶ ");
            });

            collapsibleArea.setSpacing(8);
            collapsibleArea.setPadding(new Insets(16, 4 ,0, 32));

            nodes.add(headerArea);
            if (description != null) {
                getChildren().setAll(description);
            }
            nodes.add(collapsibleArea);
        }
        getChildren().setAll(nodes);
    }


    public Node getCollapsibleContent() {
        return collapsibleArea;
    }

    public void addCollapsibleContent(Node node) {
        if (collapsibleArea == null) {
            collapsibleArea = new HBox();
        }
        collapsibleArea.getChildren().add(node);
        performLayout();
    }
}
