package io.github.qishr.cascara.ui.demo;

import io.github.qishr.cascara.schema.structure.SchemaNode;
import io.github.qishr.cascara.ui.control.EnumOptionChooser;
import io.github.qishr.cascara.ui.demo.SampleData.SampleEnum;
import io.github.qishr.cascara.ui.form.Field;
import io.github.qishr.cascara.ui.form.ObjectFieldFactory;

import javafx.scene.layout.VBox;

public class SamplesSchemaDriven {
    private VBox view = new VBox();

    public SamplesSchemaDriven() {
        SampleData sampleData = new SampleData();
        sampleData.sampleEnum.set(SampleEnum.ONE);

        //TODO use SPI to get option providers

        // Example 1: Factory-created control
        ObjectFieldFactory factory = new ObjectFieldFactory(sampleData);
        Field field = factory.createLabeledField("sampleEnum");
        view.getChildren().add(field);

        factory.setOnChangeFieldValue(b -> {
            // This runs if any field in the object changes
            System.out.println("Value: " + sampleData.get("sampleEnum"));
        });

        // Example 2: Explicitly created control
        SchemaNode objectSchemma  = sampleData.getObjectSchema();
        EnumOptionChooser chooser = new EnumOptionChooser(objectSchemma.getProperty("sampleEnum"), SampleEnum.ONE);
        view.getChildren().add(chooser);
    }

    public VBox getView() {
        return view;
    }
}
