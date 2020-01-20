package collaborative.todo.list;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class TodoStatisticsItemBase extends AnchorPane {

    protected final Label todoLabel;

    public TodoStatisticsItemBase() {

        todoLabel = new Label();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(43.0);
        setPrefWidth(329.0);

        todoLabel.setLayoutY(1.0);
        todoLabel.setPrefHeight(43.0);
        todoLabel.setPrefWidth(329.0);
        todoLabel.setText("Label");

        getChildren().add(todoLabel);

    }
    
    public void setTodoLabel(String s) {
        todoLabel.setText(s);
    }
}
