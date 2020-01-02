/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collaborative.todo.list;

import Connection.DataBaseConnection;
import DAOController.StarategyController;
import DAOController.TaskController;
import DAOController.ToDoController;
import DAOController.UserController;
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

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

        con = DataBaseConnection.getInstance();
        stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
        //UserEntity entity = new UserEntity();
        //UserController controller = new UserController();
        ToDoController doController = new ToDoController();
        TaskController taskController = new TaskController();
        StarategyController sc_todo = new StarategyController(doController);
        StarategyController sc_task = new StarategyController(taskController);
        //StarategyController starategyController = new StarategyController(controller, entity);
        //starategyController.insert(entity);
        //starategyController2.insert(doEntity);

        /*
        PreparedStatement stmt = con.prepareStatement("insert into "+Constants.TableNames.userTable+" (firstName,lastName,username,email,password) values ( ?, ?, ?, ?, ?);");
        System.err.println("insert into "+ Constants.TableNames.userTable +" (firstName,lastName,username,email,password) " + " values ( ?, ?, ?, ?, ?)");
        stmt.setString(1, "tujuo");
        stmt.setString(2, "sohhsgso");
        stmt.setString(3, "sosototo95");
        stmt.setString(4, "omnjdnksasdok95@gmail.com");
        stmt.setString(5, "52147895");
        stmt.executeUpdate();
       
        /*eman kamal --->Test queries of ToDocontroller && TaskController */
        //TODOCONTROLLER
        //insert1 
        ToDoEntity todoEntity1 = new ToDoEntity();
        String str1 = "2020-01-02";
        Date assignDate = Date.valueOf(str1);
        String str2 = "2020-01-07";
        Date deadlineDate = Date.valueOf(str2);
        todoEntity1.setTitle("todo1");
        todoEntity1.setAssignDate(assignDate);
        todoEntity1.setDeadLineDate(deadlineDate);
        todoEntity1.setOwnerId(1);
        todoEntity1.setStatus(1);
        sc_todo.insert(todoEntity1);
        //insert2
        ToDoEntity todoEntity2 = new ToDoEntity();
        String str3 = "2020-01-05";
        Date assignDate2 = Date.valueOf(str3);
        String str4 = "2020-01-10";
        Date deadlineDate2 = Date.valueOf(str4);
        todoEntity2.setTitle("todo2");
        todoEntity2.setAssignDate(assignDate2);
        todoEntity2.setDeadLineDate(deadlineDate2);
        todoEntity2.setOwnerId(2);
        todoEntity2.setStatus(0);
        sc_todo.insert(todoEntity2);
        //findall
        ArrayList<ToDoEntity> todos = sc_todo.findAll();
        if (todos.size() > 0) {
            ToDoEntity tst_todoEntity1 = todos.get(0);
            System.out.println("findAll Test");
            System.out.println("title " + tst_todoEntity1.getTitle() + "\n"
                    + "assignDate " + tst_todoEntity1.getAssignDate() + "\n"
                    + "deadlineDate " + tst_todoEntity1.getDeadLineDate() + "\n"
                    + "ownerId " + tst_todoEntity1.getOwnerId() + "\n"
                    + "status " + tst_todoEntity1.getStatus() + "\n");
        }
        //findbyId
        ToDoEntity tst_todoEntity2 = (ToDoEntity) sc_todo.findById(9);
        if (tst_todoEntity2 != null) {
            System.out.println("findById Test");
            System.out.println("title " + tst_todoEntity2.getTitle() + "\n"
                    + "assignDate " + tst_todoEntity2.getAssignDate() + "\n"
                    + "deadlineDate " + tst_todoEntity2.getDeadLineDate() + "\n"
                    + "ownerId " + tst_todoEntity2.getOwnerId() + "\n"
                    + "status " + tst_todoEntity2.getStatus() + "\n");
        }
        //update 
        String str5 = "2019-01-05";
        Date assignDate_u = Date.valueOf(str5);
        String str6 = "2019-01-10";
        Date deadlineDate_u = Date.valueOf(str6);
        todoEntity1.setTitle("todo_u");
        todoEntity1.setAssignDate(assignDate_u);
        todoEntity1.setDeadLineDate(deadlineDate_u);
        todoEntity1.setOwnerId(2);
        todoEntity1.setStatus(0);
        todoEntity1.setId(6);
        //System.out.println("id"+todoEntity1.getId());
        boolean todo_u = sc_todo.update(todoEntity1);
        System.out.println(todo_u);
        //delete 
        todoEntity2.setId(7);
        boolean todo_d = sc_todo.delete(todoEntity2);
        System.out.println(todo_d);

        //TASKCONTROLLER
        //insert1
        TaskEntity taskEntity1 = new TaskEntity();
        String desc1 = "desc1";
        int status1 = 0;
        int itemId1 = 1;
        taskEntity1.setDecription(desc1);
        taskEntity1.setStatus(status1);
        taskEntity1.setItemId(itemId1);
        sc_task.insert(taskEntity1);
        //insert2
        TaskEntity taskEntity2 = new TaskEntity();
        String desc2 = "desc2";
        int status2 = 0;
        int itemId2 = 1;
        taskEntity2.setDecription(desc2);
        taskEntity2.setStatus(status2);
        taskEntity2.setItemId(itemId2);
        sc_task.insert(taskEntity2);
        //findall
        ArrayList<TaskEntity> tasks = sc_task.findAll();
        if (tasks.size() > 0) {
            TaskEntity tst_taskEntity1 = tasks.get(0);
            System.out.println("findAll Test");
            System.out.println("description " + tst_taskEntity1.getDecription() + "\n"
                    + "status " + tst_taskEntity1.getStatus() + "\n"
                    + "itemid " + tst_taskEntity1.getItemId() + "\n"
            );
        }

        //findbyId
        TaskEntity tst_taskEntity2 = (TaskEntity) sc_task.findById(9);
        System.out.println("findById Test");
        System.out.println("description " + tst_taskEntity2.getDecription() + "\n"
                + "status " + tst_taskEntity2.getStatus() + "\n"
                + "itemid " + tst_taskEntity2.getItemId() + "\n"
        );
        //update
        String desc2_u = "desc_u";
        int status2_u = 1;
        int itemId2_u = 1;
        taskEntity1.setDecription(desc2_u);
        taskEntity1.setStatus(status2_u);
        taskEntity1.setItemId(itemId2_u);
        taskEntity1.setId(9);
        boolean task_u = sc_task.update(taskEntity1);
        System.out.println(task_u);
        //delete
        taskEntity2.setId(18);
        boolean task_d = sc_task.delete(taskEntity2);
        System.out.println(task_d);
        /*eman kamal*/
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
