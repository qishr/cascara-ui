package io.github.qishr.cascara.ui.form;

import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class Section extends AbstractFormComponent {

    private Label arrow = new Label("▼ ");
    private HBox collapsibleArea = null;

    public Section(FieldLabel title) {
        super();
        this.title = title;
        setPadding(new Insets(4, 4, 4, 4));
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
        // title.getStyleClass().add(StucturalEditorStyle.CATEGORY_HEADING);

        if (collapsibleArea == null || collapsibleArea.getChildren().isEmpty()) {
            getChildren().setAll(title);

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
            getChildren().setAll(headerArea, collapsibleArea);
        }
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
