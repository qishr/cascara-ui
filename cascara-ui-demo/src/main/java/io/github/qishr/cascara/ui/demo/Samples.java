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

import io.github.qishr.cascara.ui.language.Localization;

import javafx.geometry.Insets;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;

public class Samples {
    private VBox view;

    public Samples() {
        view = new VBox(16);
        view.setPadding(new Insets(0,0,0,0));

        TabPane tabs = new TabPane();

        Tab controls = new Tab("Controls");
        Localization.bind(controls, "title.controls");
        controls.setContent(new SamplesControls().getView());
        controls.setClosable(false);
        tabs.getTabs().add(controls);

        Tab lists = new Tab("Lists");
        Localization.bind(lists, "title.lists");
        lists.setContent(new SamplesTabular().getView());
        lists.setClosable(false);
        tabs.getTabs().add(lists);

        // Tab boxes = new Tab("Boxes");
        // Localization.bind(boxes, "title.boxes");
        // boxes.setContent(buildBoxesShowcase());
        // boxes.setClosable(false);
        // tabs.getTabs().add(boxes);

        Tab boxes = new Tab("Schema-Driven");
        Localization.bind(boxes, "title.schema-driven-controls");
        boxes.setContent(new SamplesSchemaDriven().getView());
        boxes.setClosable(false);
        tabs.getTabs().add(boxes);

        view.getChildren().addAll(tabs);
    }

    public VBox getView() { return view; }

    // public VBox buildBoxesShowcase() {
    //     Label test = new Label("Test");
    //     TitledPane titledPane = new TitledPane();
    //     titledPane.setText("Text");
    //     titledPane.setContent(test);
    //     VBox box = new VBox();
    //     box.setSpacing(20);
    //     box.setPadding(new Insets(20, 10, 10, 10));
    //     box.getChildren().addAll(titledPane);
    //     return box;
    // }
}
