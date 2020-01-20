/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handlers;

import Connection.SocketHandler;
import ControllerBase.ActionHandler;
import DAOController.TaskController;
import DAOController.ToDoController;
import DAOController.UserController;
import DTOs.Accept_RejectTaskDTO;
import Entities.EntityWrapper;
import Entities.TaskEntity;
import Entities.UserEntity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.PrintStream;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Abd-Elmalek
 */
public class WithdrawFromTaskHandler implements ActionHandler{
    private Gson gson;
    private TaskController taskController;
    private UserController userController;
    private ToDoController todoController;

    @Override
    public void handleAction(String requestJsonObject, PrintStream printStream) {
        try {
                gson = new GsonBuilder().serializeNulls().setDateFormat("MMM dd, yyyy h:mm:ss a").create();
                taskController = new TaskController();
                userController = new UserController();
                todoController = new ToDoController();
                
                JSONObject jsonObject = new JSONObject(requestJsonObject);
                String requestTaskJsonObject  = jsonObject.getJSONObject("entity").toString();
                System.out.println("******json: "+requestTaskJsonObject);
                
                Accept_RejectTaskDTO accept_RejectTaskDTO = gson.fromJson(requestTaskJsonObject, Accept_RejectTaskDTO.class);
                
                if(taskController.deleteUserTaskAssignment(accept_RejectTaskDTO.getUserId(),accept_RejectTaskDTO.getTask().getId())){
                    System.out.println("deleted");
                    ArrayList<UserEntity> todoCollaborators = userController.findAllListCollaborators(accept_RejectTaskDTO.getTodoId());
                    
                    int ownerId = todoController.findById(accept_RejectTaskDTO.getTodoId()).getOwnerId();
                    
                    String responseJson = gson.toJson(new EntityWrapper("withdraw from task", "accept_RejectTaskDTO", accept_RejectTaskDTO));
                    
                    SocketHandler socketHandler;

                    for(UserEntity collaborator : todoCollaborators){
                        if(SocketHandler.getOnlineIds().contains(collaborator.getId())){
                            int i = 0;
                            while((SocketHandler.socketHandlers.get(i).getUserId()) != collaborator.getId())
                                i++;
                            if(SocketHandler.socketHandlers.get(i).getUserId() != accept_RejectTaskDTO.getUserId()){
                                socketHandler = SocketHandler.socketHandlers.get(i);
                                socketHandler.printResponse(responseJson);
                            }
                        }
                    }
                    
                    if(accept_RejectTaskDTO.getUserId() != ownerId )
                        if(SocketHandler.getOnlineIds().contains(ownerId)){
                            int i = 0;
                            while((SocketHandler.socketHandlers.get(i).getUserId()) != ownerId)
                                i++;

                            socketHandler = SocketHandler.socketHandlers.get(i);
                            socketHandler.printResponse(responseJson); 
                        }
                }
            } catch (JSONException ex) {
                ex.printStackTrace();
            }
    }
    
}
