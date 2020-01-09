/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collaborative.todo.list;

import Connection.DataBaseConnection;
import DAOController.NotificationController;
import DAOController.FriendRequestController;
import DAOController.StarategyController;
import DAOController.TaskController;
import DAOController.ToDoController;
import DAOController.UserController;
import Entities.NotificationEntity;
import Entities.RequestEntity;
import Entities.TaskEntity;
import Entities.ToDoEntity;
import Entities.UserEntity;
import Utils.Constants;
import com.microsoft.sqlserver.jdbc.SQLServerDriver;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Abd-Elmalek
 */
public class CollaborativeTodoList extends Application {

    Connection con;
    Statement stmt;
    PreparedStatement pStatment;
    ResultSet rs;
    String queryString;
    FXMLDocumentBase root;

    @Override
    public void start(Stage stage) throws Exception {
        root = new FXMLDocumentBase();

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();


        con = DataBaseConnection.getInstance();
        stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
        
       

    }

    @Override
    public void stop() throws Exception {
        root.closeServerSocket();
    }
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
