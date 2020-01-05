/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collaborative.todo.list;

import Connection.DataBaseConnection;
import DAOController.NotificationController;
import DAOController.RequestController;
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

        UserController userController = new UserController();
        ToDoController doController = new ToDoController();
        TaskController taskController = new TaskController();
        NotificationController notificationController = new NotificationController();
        RequestController requestController=new RequestController();
        StarategyController sc_todo = new StarategyController(doController);
        StarategyController sc_task = new StarategyController(taskController);
        StarategyController sc_user = new StarategyController(userController);
        StarategyController sc_notification = new StarategyController(notificationController);
        StarategyController sc_request = new StarategyController(requestController);
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
       
        /*eman kamal --->Test queries of ToDocontroller && TaskController && UserController */
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
        //sc_todo.insert(todoEntity1);
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
        //sc_todo.insert(todoEntity2);
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
        //USERCONTROLLER
        //insert1
        UserEntity userEntity1 = new UserEntity();
        userEntity1.setFirstName("walaa");
        userEntity1.setLastName("wagdi");
        userEntity1.setUserName("walaaelshora");
        userEntity1.setEmail("walaa@gmail.com");
        userEntity1.setPassword("123456");
        sc_user.insert(userEntity1);
        //insert2
        UserEntity userEntity2 = new UserEntity();
        userEntity2.setFirstName("yasmeen");
        userEntity2.setLastName("ashraf");
        userEntity2.setUserName("yasmenashraf");
        userEntity2.setEmail("yasmin@gmail.com");
        userEntity2.setPassword("123456");
        sc_user.insert(userEntity2);
        //update
        userEntity1.setFirstName("sara");
        userEntity1.setLastName("ali");
        userEntity1.setUserName("saraali");
        userEntity1.setEmail("sara@gmail.com");
        userEntity1.setPassword("123456");
        userEntity1.setId(1003);
        boolean user_u = sc_user.update(userEntity1);
        System.out.println(user_u);
        //findbyid
        UserEntity tst_UserEntity = (UserEntity) sc_user.findById(1);
        System.out.println("FindByIdTest");
        System.out.println("firstname " + tst_UserEntity.getFirstName() + "\n"
                + "lastname " + tst_UserEntity.getLastName() + "\n"
                + "username " + tst_UserEntity.getUserName() + "\n"
                + "email " + tst_UserEntity.getEmail() + "\n"
                + "password " + tst_UserEntity.getPassword() + "\n");
        //findall
        ArrayList<UserEntity> users_list = sc_user.findAll();
        if (users_list.size() > 0) {
            UserEntity tst_UserEntity2 = users_list.get(2);
            System.out.println("FindAllTest");
            System.out.println("firstname " + tst_UserEntity2.getFirstName() + "\n"
                    + "lastname " + tst_UserEntity2.getLastName() + "\n"
                    + "username " + tst_UserEntity2.getUserName() + "\n"
                    + "email " + tst_UserEntity2.getEmail() + "\n"
                    + "password " + tst_UserEntity2.getPassword() + "\n");
        }
        //delete 
        userEntity2.setId(1004);
        boolean user_d = sc_user.delete(userEntity2);
        System.out.println(user_d);

        //NOTIFICATION CONTROLLER 
        //insert1 
        NotificationEntity notificationEntity1 = new NotificationEntity();
        Date time1 = Date.valueOf("2020-01-03");
        notificationEntity1.setTime(time1);
        notificationEntity1.setType(1);
        notificationEntity1.setReceivedUserId(1);
        notificationEntity1.setSentUserId(2);
        //sc_notification.insert(notificationEntity1);
        //insert2
        NotificationEntity notificationEntity2 = new NotificationEntity();
        Date time2 = Date.valueOf("2020-01-04");
        notificationEntity2.setTime(time2);
        notificationEntity2.setType(2);
        notificationEntity2.setReceivedUserId(1003);
        notificationEntity2.setSentUserId(1009);
        //sc_notification.insert(notificationEntity2);
        //update 
        Date time3 = Date.valueOf("2020-01-07");
        notificationEntity2.setTime(time3);
        notificationEntity2.setType(1);
        notificationEntity2.setReceivedUserId(1);
        notificationEntity2.setSentUserId(2);
        notificationEntity2.setId(2);
        boolean notification2_u = sc_notification.update(notificationEntity2);
        System.out.println(notification2_u);
        //delete 
        notificationEntity2.setId(3);
        boolean notification_d = sc_notification.delete(notificationEntity2);
        System.out.println(notification_d);
        //findbyid
        NotificationEntity tst_notificationEntity1 = (NotificationEntity) sc_notification.findById(1);
        System.out.println("FindByIdTest");
        System.out.println("time " + tst_notificationEntity1.getTime() + "\n"
                + "type " + tst_notificationEntity1.getType() + "\n"
                + "recievedUserId " + tst_notificationEntity1.getReceivedUserId() + "\n"
                + "sentUserId " + tst_notificationEntity1.getSentUserId());
        //findall
        ArrayList<NotificationEntity> notification_list = sc_notification.findAll();
        if (notification_list.size() > 0) {
            NotificationEntity tst_notificationEntity2 = notification_list.get(1);
            System.out.println("FindByAllTest");
            System.out.println("time " + tst_notificationEntity2.getTime() + "\n"
                    + "type " + tst_notificationEntity2.getType() + "\n"
                    + "recievedUserId " + tst_notificationEntity2.getReceivedUserId() + "\n"
                    + "sentUserId " + tst_notificationEntity2.getSentUserId());
        }
        
         //REQUESTCONTROLLER 
        //insert1 
        RequestEntity requestEntity1 = new RequestEntity();
        Date time4 = Date.valueOf("2020-01-10");
        requestEntity1.setTime(time4);
        requestEntity1.setType(1);
        requestEntity1.setReceivedUserId(2);
        requestEntity1.setSentUserId(1);
        //sc_request.insert(requestEntity1);
        //insert2
        RequestEntity requestEntity2 = new RequestEntity();
        Date time5 = Date.valueOf("2020-01-20");
        requestEntity2.setTime(time5);
        requestEntity2.setType(2);
        requestEntity2.setReceivedUserId(1);
        requestEntity2.setSentUserId(2);
        //sc_request.insert(requestEntity2);
        //insert3
        RequestEntity requestEntity3 = new RequestEntity();
        Date time7 = Date.valueOf("2020-01-25");
        requestEntity3.setTime(time5);
        requestEntity3.setType(2);
        requestEntity3.setReceivedUserId(1);
        requestEntity3.setSentUserId(2);
        //sc_request.insert(requestEntity3);
        //update 
        Date time6 = Date.valueOf("2020-01-07");
        requestEntity2.setTime(time6);
        requestEntity2.setType(1);
        requestEntity2.setReceivedUserId(1);
        requestEntity2.setSentUserId(2);
        requestEntity2.setId(2);
        boolean requestEntity2_u = sc_request.update(requestEntity2);
        System.out.println(requestEntity2_u);
        //delete 
        requestEntity3.setId(3);
        boolean request_d = sc_request.delete(requestEntity3);
        System.out.println(request_d);
        //findbyid
        RequestEntity tst_requestEntity1 = (RequestEntity) sc_request.findById(1);
        System.out.println("FindByIdTest");
        System.out.println("time " + tst_requestEntity1.getTime() + "\n"
                + "type " + tst_requestEntity1.getType() + "\n"
                + "recievedUserId " + tst_requestEntity1.getReceivedUserId() + "\n"
                + "sentUserId " + tst_requestEntity1.getSentUserId());
        //findall
        ArrayList<RequestEntity> request_list = sc_request.findAll();
        if (request_list.size() > 0) {
            RequestEntity tst_requestEntity2 = request_list.get(1);
            System.out.println("FindByAllTest");
            System.out.println("time " + tst_requestEntity2.getTime() + "\n"
                    + "type " + tst_requestEntity2.getType() + "\n"
                    + "recievedUserId " + tst_requestEntity2.getReceivedUserId() + "\n"
                    + "sentUserId " + tst_requestEntity2.getSentUserId());
        }
        /*eman kamal*/
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
