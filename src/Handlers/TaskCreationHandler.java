/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handlers;

import Connection.SocketHandler;
import ControllerBase.ActionHandler;
import DAOController.ItemController;
import DAOController.TaskController;
import DAOController.ToDoController;
import DAOController.UserController;
import Entities.EntityWrapper;
import Entities.TaskEntity;
import Entities.ToDoEntity;
import Entities.UserEntity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.PrintStream;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Abd-Elmalek
 */
public class TaskCreationHandler implements ActionHandler{
    private Gson gson;
    private TaskController taskController;
    private ItemController itemController;
    private UserController userController;
    private ToDoController toDoController;
    
     @Override
    public void handleAction(String requestJsonObject, PrintStream printStream) {
            try {
                gson = new GsonBuilder().serializeNulls().setDateFormat("MMM dd, yyyy h:mm:ss a").create();
                taskController = new TaskController();
                itemController = new ItemController();
                userController = new UserController();
                toDoController = new ToDoController();
                JSONObject jsonObject = new JSONObject(requestJsonObject);
                String requestTaskJsonObject  = jsonObject.getJSONObject("entity").toString();
                System.out.println("******json: "+requestTaskJsonObject);
                TaskEntity taskEntity = gson.fromJson(requestTaskJsonObject, TaskEntity.class);
                if(taskController.insert(taskEntity)){
                    System.out.println("inserted");
                    System.out.println("task id is "+taskEntity.getId());
                    int todoId = itemController.findById(taskEntity.getItemId()).getTodoId();
                    System.out.println(todoId);
                    ToDoEntity toDoEntity = toDoController.findById(todoId);
                    System.out.println(toDoEntity.getOwnerId());
                     EntityWrapper entityWrapper = new EntityWrapper("create task", "entity", taskEntity);
                     String taskCreationJsonResponse = gson.toJson(entityWrapper);
                     List<UserEntity> collaborators = userController.findAllListCollaborators(todoId);
                     List<Integer> onLineUsers = SocketHandler.getOnlineIds();
                     
                     if(collaborators.size() > 0){
                            for(UserEntity userEntity : collaborators){
                           if(onLineUsers.contains(userEntity.getId()) ||onLineUsers.contains(toDoEntity.getOwnerId())){

                               for(int i =0; i< SocketHandler.socketHandlers.size();i++){
                                   if(SocketHandler.socketHandlers.get(i).getUserId()== userEntity.getId()
                                   || SocketHandler.socketHandlers.get(i).getUserId() == toDoEntity.getOwnerId()){
                                       SocketHandler.socketHandlers.get(i).printResponse(taskCreationJsonResponse);

                                   }
                               }


                        }

                        }
                     }else{
                          for(int i =0; i< SocketHandler.socketHandlers.size();i++){
                                   if(SocketHandler.socketHandlers.get(i).getUserId() == toDoEntity.getOwnerId()){
                                       SocketHandler.socketHandlers.get(i).printResponse(taskCreationJsonResponse);

                                   }
                               }
                         
                     }
                }
            } catch (JSONException ex) {
                ex.printStackTrace();
            }
    }
    
}
