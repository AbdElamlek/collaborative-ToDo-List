package collaborative.todo.list;

import com.jfoenix.controls.JFXButton;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class OnlineUserItemBase extends AnchorPane {

    protected final Label userNameLabel;
    protected final JFXButton closeButton;

    public OnlineUserItemBase() {

        userNameLabel = new Label();
        closeButton = new JFXButton();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(43.0);
        setPrefWidth(329.0);

        userNameLabel.setLayoutX(5.0);
        userNameLabel.setPrefHeight(43.0);
        userNameLabel.setPrefWidth(150.0);

        closeButton.setLayoutX(243.0);

        getChildren().add(userNameLabel);
        getChildren().add(closeButton);

    }
    
    
    public void setUserName(String s) {
        userNameLabel.setText(s);
    }
}
