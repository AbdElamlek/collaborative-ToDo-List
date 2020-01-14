/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handlers;

import ControllerBase.ActionHandler;
import DAOController.CollaboratorRequestController;
import DAOController.CommentController;
import DAOController.ItemController;
import DAOController.NotificationController;
import DAOController.FriendRequestController;
import DAOController.TaskController;
import DAOController.ToDoController;
import DAOController.UserController;
import Entities.EntityWrapper;
import Entities.ItemEntity;
import Entities.TaskEntity;
import Entities.ToDoEntity;
import Entities.UserEntity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.function.Consumer;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Reham
 */
public class LoginHandler implements ActionHandler{
    private Gson gson;
    private UserController userController;
    private Consumer<Integer> userIdSetter;
    
    public void assignUserIdToSocket(Consumer<Integer> userIdSetter){
        this.userIdSetter = userIdSetter;
    }
    
    @Override
    public void handleAction(String requestJsonObject, PrintStream printStream) {
        
        gson = new GsonBuilder().serializeNulls().setDateFormat("MMM dd, yyyy h:mm:ss a").create();
        userController = new UserController();
        try {  
            JSONObject jsonObject = new JSONObject(requestJsonObject);
            String userJsonObject  = jsonObject.getJSONObject("entity").toString();
            System.out.println("******json: "+userJsonObject);
            UserEntity user = gson.fromJson(userJsonObject, UserEntity.class);
            
            UserEntity retrievedUser = userController.findByUsernameAndPassword(user.getUserName(), user.getPassword());
            
            if(retrievedUser != null){
                if(userIdSetter != null)
                    userIdSetter.accept(retrievedUser.getId());
                
                ToDoController todoController = new ToDoController();
                ItemController itemController = new ItemController();
                TaskController taskController = new TaskController();
                CommentController commentController = new CommentController();
                
                // MY TODO LISTS
                
                ArrayList<ToDoEntity> userTodoLists = todoController.findByOwnerId(retrievedUser.getId());
                for(ToDoEntity todo : userTodoLists){
                    todo.setCollaboratorList(userController.findAllListCollaborators(todo.getId()));
                    todo.setRequestedCollaboratorList(userController.findAllListRequestedCollaborators(todo.getId()));
                    ArrayList<ItemEntity> todoItems = itemController.findByTodoId(todo.getId());
                    
                    for(ItemEntity item : todoItems){
                        ArrayList<TaskEntity> tasks = taskController.findByItemId(item.getId());
                        for(TaskEntity task : tasks)
                            task.setCommentsList(commentController.findByTaskId(task.getId()));
                        item.setTasksList(tasks);
                    }
                    todo.setItemsList(todoItems);  
                }
                retrievedUser.setTodoList(userTodoLists);
                
                // THE TODO LISTS I COLLABORATE IN
                
                ArrayList<ToDoEntity> collaborationTodoLists = todoController.findAllUserCollaboratedInTodos(retrievedUser.getId());
                for(ToDoEntity todo : collaborationTodoLists){
                    ArrayList<ItemEntity> todoItems = itemController.findByTodoId(todo.getId());
                    
                    for(ItemEntity item : todoItems){
                        ArrayList<TaskEntity> tasks = taskController.findByItemId(item.getId());
                        for(TaskEntity task : tasks)
                            task.setCommentsList(commentController.findByTaskId(task.getId()));
                        item.setTasksList(tasks);
                    }
                    todo.setItemsList(todoItems);  
                }
                retrievedUser.setCollaboratorList(collaborationTodoLists);
                
                retrievedUser.setTasksList(taskController.findAllUserAssignedTasks(retrievedUser.getId()));

                retrievedUser.setFriendList(userController.findAllUserFriends(retrievedUser.getId()));

                NotificationController notificationController = new NotificationController();
                retrievedUser.setNotificationList(notificationController.findByReceiverId(retrievedUser.getId()));

                FriendRequestController friendRequestController = new FriendRequestController();
                retrievedUser.setFriendRequestList(friendRequestController.findByReceiverId(retrievedUser.getId()));  
                
                CollaboratorRequestController collaboratorRequestController = new CollaboratorRequestController();
                retrievedUser.setCollaborationRequestList(collaboratorRequestController.findByReceiverId(retrievedUser.getId()));
                
                //TaskAssignmentRequestController taskAssignmentRequestController = new TaskAssignmentRequestController();
                //retrievedUser.setTaskAssignmentRequestList(taskAssignmentRequestController.findByReceiverId(retrievedUser.getId()));
            }
            String responseJsonObject = gson.toJson(new EntityWrapper("logIn", "UserEntity", retrievedUser));
            System.out.println("response Json before send: " + responseJsonObject);
            printStream.println(responseJsonObject);
            
        }catch(JSONException ex){
            ex.printStackTrace();
        }
    }
    
}
