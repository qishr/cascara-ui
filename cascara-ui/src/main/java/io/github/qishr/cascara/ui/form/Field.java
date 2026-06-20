package io.github.qishr.cascara.ui.form;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import io.github.qishr.cascara.common.data.TableData;
import io.github.qishr.cascara.common.diagnostic.LocalizableRuntimeException;
import io.github.qishr.cascara.common.diagnostic.code.GenericDiagnosticCode;
import io.github.qishr.cascara.schema.structure.SchemaNode;
import io.github.qishr.cascara.schema.util.ValidationResult;
import io.github.qishr.cascara.ui.style.custom.FormStyle;

import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class Field extends AbstractFormComponent {
    protected FieldMetadata metadata;

    protected ObjectProperty<?> scalarData;
    protected ObservableList<?> arrayData;

    private FieldValidator onValidate;

    protected ViewAndControl inputControl;
    private Label errorLabel;

    @SuppressWarnings("unchecked")
    public Field(Observable observable, ViewAndControl inputControl, FieldMetadata metadata) {
        super();
        this.inputControl = inputControl;
        this.metadata = metadata;

        setSpacing(3);
        setPadding(new Insets(4));

        if (inputControl == null || inputControl.view() == null) {
            throw new LocalizableRuntimeException(GenericDiagnosticCode.UNEXPECTED_NULL, "inputControl");
        }

        if (observable instanceof ObservableList<?> list) {
            this.arrayData = list;
            // TODO: What was this for? Table contents?
            list.addListener(new ListChangeListener<Object>() {
                public void onChanged(Change<? extends Object> c) {
                    onTextChanged();
                }
            });
        } else if (observable instanceof ObjectProperty scalar) {
            this.scalarData = scalar;
            scalar.addListener((obs, oldVal, newVal) -> {
                onTextChanged();
            });
        } else {
            System.out.println("ERROR");
        }

        performLayout();
    }

    @Override
    protected void onTextChanged() {
        applyValidationStyle();

        // Content highlighting
        applyHighlighting();
    }

    @Override
    protected void performLayout() {
        List<Node> nodes = new ArrayList<>();

        if (title != null) {
            nodes.add(title);
        }

        if (inputControl.view() != null) {
            if (getInputControl() instanceof CheckBox  && description != null) {
                HBox hbox = new HBox(inputControl.view(), description);
                hbox.setSpacing(8);
                nodes.add(hbox);
            } else {
                if (description != null) {
                    nodes.add(description);
                }
                nodes.add(inputControl.view());
            }
        }

        if (errorLabel != null) {
            nodes.add(errorLabel);
        }

        if (nodes.isEmpty()) {
            getChildren().clear();
        } else {
            getChildren().setAll(nodes);
        }
    }

    protected void applyHighlighting() {
        boolean isMatch = false;
        if (scalarData != null && scalarData.get() != null) {
            String text = scalarData.get().toString();
            if (text != null) {
                String filter = getQuery();
                if (filter != null && !filter.isEmpty() && text.toLowerCase().contains(filter.toLowerCase())) {
                    isMatch = true;
                }
            }
        }

        // TODO: Highlighting of search matches in input controls
        if (getInputControl() instanceof Control) {
            if (isMatch) {
                // node.getStyleClass().add(TextInputStyle.SEARCH_MATCH);
                // Border border = Border.stroke(Paint.valueOf("aqua"));
                // node.setBorder(border);
            } else {
                // node.getStyleClass().remove(TextInputStyle.SEARCH_MATCH);
                // node.setBorder(Border.EMPTY);
            }
        }
    }

    public FieldLabel getLabel() {
        return title;
    }

    public void setLabel(FieldLabel label) {
        super.setTitle(label);
    }

    //
    // Arrays
    //

    public void setAddRowHandler(Runnable addRow) {
        if (metadata != null) {
            metadata.setAddRowHandler(addRow);
        }
    }

    public void setRemoveRowHandler(Consumer<TableData> addRow) {
        if (metadata != null) {
            metadata.setRemoveRowHandler(addRow);
        }
    }

    //
    // Meta
    //

    public StringProperty queryProperty() { return query; }

    public Node getInputControl() {
        if (inputControl == null) return null;
        return inputControl.control();
    }

    public FieldMetadata getMetadata() {
        return metadata;
    }

    public boolean isArray() {
        return metadata == null ? false : metadata.isArrayField();
    }

    public String getName() {
        return metadata.getName();
    }

    //
    // Validation
    //

    /// If onValidate is set, errorLabel is created.
    /// If it's unset, errorLabel will be null.
    public void setOnValidate(FieldValidator callback) {
        this.onValidate = callback;
        if (onValidate == null) {
            if (errorLabel != null) {
                errorLabel = null;
            }
        } else {
            if (errorLabel == null) {
                errorLabel = new Label();
                // cascara://organizer/CASC-000257D1 Use validation error colors from theme
                errorLabel.setStyle("-fx-text-fill: #f48771; -fx-font-size: 0.9em;");
                errorLabel.setWrapText(true);
            }
        }
        applyValidationStyle();
    }

    private void applyValidationStyle() {
        if (onValidate == null) return;
        if (scalarData == null) return;

        SchemaNode schema = metadata.getSchema();
        Object value = scalarData.getValue();
        String path = metadata.getName();

        ValidationResult result = onValidate.performValidation(value, path, schema);
        boolean hasError = !result.isValid();

        if (hasError) {
            if (!inputControl.view().getStyleClass().contains(FormStyle.INPUT_ERROR)) {
                inputControl.view().getStyleClass().add(FormStyle.INPUT_ERROR);
            }
            errorLabel.setText(result.getMessages().get(0).text());
            errorLabel.setVisible(true);
        } else {
            inputControl.view().getStyleClass().remove(FormStyle.INPUT_ERROR);
            errorLabel.setVisible(false);
        }
    }


}
