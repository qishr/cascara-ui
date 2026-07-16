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


package io.github.qishr.cascara.ui.demo;

import io.github.qishr.cascara.ui.demo.SampleData.SampleEnum;
import io.github.qishr.cascara.ui.form.Field;
import io.github.qishr.cascara.ui.form.LabelPosition;
import io.github.qishr.cascara.ui.form.ObjectFieldFactory;
import io.github.qishr.cascara.ui.language.Localization;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SamplesSchemaDriven {
    private VBox view = new VBox(20);

    public SamplesSchemaDriven() {
        SampleData sampleData = new SampleData();
        sampleData.sampleEnum.set(SampleEnum.ONE);

        ObjectFieldFactory factory = new ObjectFieldFactory(sampleData);

        Field[] fields = new Field[] {
            // Row 0
            factory.createLabeledField("sampleEnum"),
            factory.createLabeledField("sampleText"),
        };

        Node controls = layoutControls(fields);
        Node search = buildSearchBox(fields);

        view.getChildren().addAll(search, controls);
        view.setAlignment(Pos.TOP_CENTER);
        view.setPadding(new Insets(20, 10, 10, 10));
    }

    public VBox getView() {
        return view;
    }

    private HBox buildSearchBox(Field[] fields) {
        HBox searchBox = new HBox(8);
        Localization.bindDirection(searchBox);
        Label searchLabel = new Label("Search:");
        Localization.bind(searchLabel, "label.search");
        TextField searchText = new TextField();
        searchBox.getChildren().addAll(searchLabel, searchText);
        searchBox.setAlignment(Pos.CENTER);
        for (Field field : fields) {
            field.queryProperty().bind(searchText.textProperty());
        }
        return searchBox;
    }

    private GridPane layoutControls(Field[] fields) {
        GridPane grid = new GridPane();
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setAlignment(Pos.TOP_CENTER);

        int row = 0;
        int col = 0;

        for (Field field : fields) {
            field.setLabelPosition(LabelPosition.ABOVE);

            VBox container = new VBox();
            container.setPadding(new Insets(20));
            container.setAlignment(Pos.CENTER);
            container.setStyle("""
                -fx-border-radius: 4px;
                -fx-border-color: -color-control-foreground;
                -fx-border-width: 1px;
            """);
            container.getChildren().add(field);

            // Set max width to force controls to stretch and align in the grid
            container.setMaxWidth(Double.MAX_VALUE);
            // grid.setBackground(Background.fill(Paint.valueOf("#14452f")));

            // Ensure the control itself takes up space
            field.setMaxWidth(Double.MAX_VALUE);

            grid.add(container, col, row);

            col++;
            if (col == 4) {
                col = 0;
                row++;
            }
        }
        return grid;
    }
}
