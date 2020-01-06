/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handlers;

import ControllerBase.ActionHandler;
import DAOController.NotificationController;
import DAOController.RequestController;
import DAOController.TaskController;
import DAOController.ToDoController;
import DAOController.UserController;
import Entities.EntityWrapper;
import Entities.UserEntity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.PrintStream;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Reham
 */
public class LoginHandler implements ActionHandler{
    private Gson gson;
    private UserController userController;
    
    @Override
    public void handleAction(String requestJsonObject, PrintStream printStream) {
        
        gson = new GsonBuilder().serializeNulls().create();
        userController = new UserController();
        try {  
            JSONObject jsonObject = new JSONObject(requestJsonObject);
            String userJsonObject  = jsonObject.getJSONObject("entity").toString();
            System.out.println("******json: "+userJsonObject);
            UserEntity user = gson.fromJson(userJsonObject, UserEntity.class);
            
            UserEntity retrievedUser = userController.findByUsernameAndPassword(user.getUserName(), user.getPassword());
            
            if(retrievedUser != null){
                System.out.println("****SERVER FOUND");
                ToDoController todoController = new ToDoController();
                retrievedUser.setTodoList(todoController.findByOwnerId(retrievedUser.getId()));
                retrievedUser.setColaboartedList(todoController.findAllUserCollaboratedInTodos(retrievedUser.getId()));

                TaskController taskController = new TaskController();
                retrievedUser.setTasksList(taskController.findAllUserAssignedTasks(retrievedUser.getId()));

                retrievedUser.setFriendList(userController.findAllUserFriends(retrievedUser.getId()));

                NotificationController notificationController = new NotificationController();
                retrievedUser.setNotificationList(notificationController.findByReceiverId(retrievedUser.getId()));

                RequestController requestController = new RequestController();
                retrievedUser.setRequestList(requestController.findByReceiverId(retrievedUser.getId()));   
            }
            
            printStream.println(gson.toJson(new EntityWrapper("logIn", "UserEntity", retrievedUser)));
            
        }catch(JSONException ex){
            ex.printStackTrace();
        }
    }
    
}
