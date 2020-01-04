/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collaborative.todo.list;

import Connection.ServerSocketHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 * 
 * @author ahmedpro
 */
public class FXMLDocumentBase extends AnchorPane {

    protected final Button start;
    protected final Button stop;
    private ServerSocketHandler server;

    public FXMLDocumentBase() {
        
        start = new Button();
        stop = new Button();
        
        setId("AnchorPane");
        setPrefHeight(434.0);
        setPrefWidth(569.0);

        start.setId("start");
        start.setLayoutX(118.0);
        start.setLayoutY(186.0);
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
        stop.setLayoutX(309.0);
        stop.setLayoutY(186.0);
        stop.setMnemonicParsing(false);
        stop.setPrefHeight(31.0);
        stop.setPrefWidth(128.0);
        stop.setText("stop");
        stop.addEventHandler(ActionEvent.ACTION, (action) -> {
            server.stopServer();
            start.setDisable(false);
            stop.setDisable(true);
        });

        getChildren().add(start);
        getChildren().add(stop);

    }
}