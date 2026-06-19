package io.github.qishr.cascara.ui.demo;

import io.github.qishr.cascara.ui.demo.SampleData.SampleEnum;
import io.github.qishr.cascara.ui.form.Field;
import io.github.qishr.cascara.ui.form.ObjectFieldFactory;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class SamplesSchemaDriven {
    private VBox view = new VBox();

    public SamplesSchemaDriven() {
        SampleData sampleData = new SampleData();
        sampleData.sampleEnum.set(SampleEnum.ONE);


        // Example 1: Factory-created control
        ObjectFieldFactory factory = new ObjectFieldFactory(sampleData);

        Node[] controls = new Node[] {
            // Row 0
            factory.createField("sampleEnum"),
            factory.createField("text")
        };

        GridPane grid = layout(controls);


        // Field enumField = factory.createField("sampleEnum");
        // view.getChildren().add(enumField);

        // Field stringField = factory.createField("text");
        // view.getChildren().add(stringField);


        // factory.setOnChangeFieldValue(b -> {
        //     // This runs if any field in the object changes
        //     System.out.println("Value: " + sampleData.get("sampleEnum"));
        // });

        // // Example 2: Explicitly created control
        // SchemaNode objectSchemma  = sampleData.getObjectSchema();
        // EnumOptionChooser chooser = new EnumOptionChooser(objectSchemma.getProperty("sampleEnum"), SampleEnum.ONE);
        // view.getChildren().add(chooser);


        view.getChildren().add(grid);
        view.setAlignment(Pos.TOP_CENTER);
        view.setSpacing(20);
        view.setPadding(new Insets(20, 10, 10, 10));
    }

    public VBox getView() {
        return view;
    }

    private GridPane layout(Node[] controls) {
        GridPane grid = new GridPane();
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setAlignment(Pos.TOP_CENTER);

        int row = 0;
        int col = 0;

        for (Node control : controls) {
            String title = control.getClass().getSimpleName();
            if (control instanceof Field field) {
                title = field.getName();
            }
            TitledPane titledPane = new TitledPane(
                title,
                control
            );
            titledPane.setCollapsible(false);

            // Set max width to force controls to stretch and align in the grid
            titledPane.setMaxWidth(Double.MAX_VALUE);
            // grid.setBackground(Background.fill(Paint.valueOf("#14452f")));

            // Ensure the control itself takes up space
            if (control instanceof Control) {
                ((Control)control).setMaxWidth(Double.MAX_VALUE);
            }

            grid.add(titledPane, col, row);

            col++;
            if (col == 4) {
                col = 0;
                row++;
            }
        }
        return grid;
    }
}
