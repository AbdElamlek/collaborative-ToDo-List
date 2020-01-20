package collaborative.todo.list;

import Connection.ServerSocketHandler;
import Connection.SocketHandler;
import DAOController.ToDoController;
import Entities.ToDoEntity;
import com.jfoenix.controls.JFXButton;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class FxmlStatisticsBase extends AnchorPane {

    protected final Button start;
    protected final Button stop;
    protected final Button refresh;
    protected final ScrollPane scroll1;
    protected final ScrollPane scroll2;
    protected final AnchorPane onlineUsersPane;
    protected final Label onlineUsersTitle;
    protected final VBox onlineUsersBox;
    protected final AnchorPane statisticsPane;
    protected final Label statisticsTitle;
    protected final VBox statisticsBox;
    private int size1 = 0;
    private int size2 = 0;

    private ServerSocketHandler server;

    public FxmlStatisticsBase() {

        start = new Button();
        stop = new Button();
        refresh = new Button();
        scroll1 = new ScrollPane();
        scroll2 = new ScrollPane();
        onlineUsersPane = new AnchorPane();
        onlineUsersTitle = new Label();
        onlineUsersBox = new VBox();
        statisticsPane = new AnchorPane();
        statisticsTitle = new Label();
        statisticsBox = new VBox();

        scroll1.setMaxHeight(USE_PREF_SIZE);
        scroll1.setMaxWidth(USE_PREF_SIZE);
        scroll1.setMinHeight(USE_PREF_SIZE);
        scroll1.setMinWidth(USE_PREF_SIZE);
        scroll1.setPrefHeight(43.0);
        scroll1.setPrefWidth(329.0);
        scroll1.setLayoutX(0.0);
        scroll1.setLayoutY(24.0);
        scroll1.setPrefHeight(298.0);
        scroll1.setPrefWidth(329.0);

        scroll2.setLayoutX(406.0);
        scroll2.setPrefHeight(324.0);
        scroll2.setPrefWidth(329.0);
        scroll2.setLayoutY(0.0);
        scroll2.setPrefHeight(31.0);
        scroll2.setPrefWidth(329.0);
        scroll2.setLayoutY(30.0);
        scroll2.setPrefHeight(292.0);
        scroll2.setPrefWidth(329.0);

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(512.0);
        setPrefWidth(735.0);

        start.setId("start");
        start.setLayoutX(170.0);
        start.setLayoutY(435.0);
        start.setMnemonicParsing(false);
        start.setPrefHeight(31.0);
        start.setPrefWidth(128.0);
        start.setText("start");
        start.addEventHandler(ActionEvent.ACTION, (action) -> {
            server = new ServerSocketHandler();
            server.startServer();
            start.setDisable(true);
            stop.setDisable(false);
        });

        stop.setId("stop");
        stop.setLayoutX(340.0);
        stop.setLayoutY(435.0);
        stop.setMnemonicParsing(false);
        stop.setPrefHeight(31.0);
        stop.setPrefWidth(128.0);
        stop.setText("stop");
        stop.addEventHandler(ActionEvent.ACTION, (action) -> {
            server.closeServer();
            start.setDisable(false);
            stop.setDisable(true);
        });

        refresh.setId("refresh");
        refresh.setLayoutX(512.0);
        refresh.setLayoutY(435.0);
        refresh.setMnemonicParsing(false);
        refresh.setPrefHeight(31.0);
        refresh.setPrefWidth(128.0);
        refresh.setText("refresh");
        refresh.addEventHandler(ActionEvent.ACTION, (action) -> {
            setonlineUsersBox(SocketHandler.getOnlineUsersName());
            setStatisticsBox();
        });

        onlineUsersPane.setPrefHeight(324.0);
        onlineUsersPane.setPrefWidth(329.0);

        onlineUsersTitle.setLayoutY(0.0);
        onlineUsersTitle.setPrefHeight(39.0);
        onlineUsersTitle.setPrefWidth(329.0);
        onlineUsersTitle.setText("online users: ");

        onlineUsersBox.setLayoutX(0.0);
        onlineUsersBox.setLayoutY(24.0);
        onlineUsersBox.setPrefHeight(298.0);
        onlineUsersBox.setPrefWidth(329.0);

        statisticsPane.setLayoutX(406.0);
        statisticsPane.setPrefHeight(324.0);
        statisticsPane.setPrefWidth(329.0);

        statisticsTitle.setLayoutY(0.0);
        statisticsTitle.setPrefHeight(31.0);
        statisticsTitle.setPrefWidth(329.0);
        statisticsTitle.setText("todo lists statistics");

        statisticsBox.setLayoutY(30.0);
        statisticsBox.setPrefHeight(292.0);
        statisticsBox.setPrefWidth(329.0);

        onlineUsersPane.getChildren().add(onlineUsersTitle);
        onlineUsersPane.getChildren().add(onlineUsersBox);
        statisticsPane.getChildren().add(statisticsTitle);
        statisticsPane.getChildren().add(statisticsBox);
        scroll1.setContent(onlineUsersBox);
        scroll2.setContent(statisticsBox);

        getChildren().add(start);
        getChildren().add(stop);
        getChildren().add(refresh);
        getChildren().add(scroll1);
        getChildren().add(scroll2);

        setStatisticsBox();

    }

    public void closeServerSocket() {
        if (server != null) {
            server.closeServer();
        }
    }

    public void setonlineUsersBox(String[] list) {
        if (size1 > 0) {
            onlineUsersBox.getChildren().remove(0, size1);
        }
        onlineUsersTitle.setText("online users: " + list.length);
        size1 = list.length;
        if (size1 > 0) {
            for (String list1 : list) {
                OnlineUserItemBase o = new OnlineUserItemBase();
                o.setUserName(list1);
                onlineUsersBox.getChildren().add(o);
            }
        }
    }

    public void setStatisticsBox() {
        if (size2 > 0) {
            statisticsBox.getChildren().remove(0, size2);
        }

        ToDoController tdc = new ToDoController();
        ArrayList<ToDoEntity> list = tdc.findAll();
        size2 = list.size();
        if (!list.isEmpty()) {
            statisticsTitle.setText("todo lists: " + list.size());
            for (int i = 0; i < list.size(); i++) {
                TodoStatisticsItemBase item = new TodoStatisticsItemBase();
                if (list.get(i).getStatus() == 0) {
                    item.setTodoLabel(list.get(i).getTitle() + " \t not finished");
                } else {
                    item.setTodoLabel(list.get(i).getTitle() + "\t finished");
                }
                statisticsBox.getChildren().add(item);
            }
        }

    }
}
