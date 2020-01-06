/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import DAOController.NotificationController;
import DAOController.RequestController;
import DAOController.TaskController;
import DAOController.ToDoController;
import DAOController.UserController;
import Entities.EntityWrapper;
import Entities.UserEntity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author yyy
 */
public class UserActionHandler implements ActionHandler{
    private Gson gson;
    private UserController userController;

    @Override
    public String handleAction(String requestJsonObject) {
         System.out.println("****SERVER handleAction");
         gson = new Gson();
        userController = new UserController();
        String responseJsonObject = "";
        try {  
            JSONObject jsonObject = new JSONObject(requestJsonObject);
            String userJsonObject  = jsonObject.getJSONObject("entity").toString();
            System.out.println("******json: "+userJsonObject);
            UserEntity user = gson.fromJson(userJsonObject, UserEntity.class);

            switch(jsonObject.getString("action").toString()){
                case "logIn":
                    requestJsonObject = handleLogin(user); 
            }
            
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        return responseJsonObject;
    }
    
    public String handleLogin(UserEntity user){
        UserEntity retrievedUser = userController.findByUsernameAndPassword(user.getUserName(), user.getPassword());
        System.out.println("****SERVER handleLogin");
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
           
        return gson.toJson(new EntityWrapper("logIn", "UserEntity", retrievedUser));
    }
    
}
