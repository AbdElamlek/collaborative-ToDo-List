/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handlers;

import Connection.SocketHandler;
import ControllerBase.ActionHandler;
import DAOController.ToDoController;
import DAOController.UserController;
import Entities.EntityWrapper;
import Entities.ToDoEntity;
import Entities.UserEntity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.PrintStream;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Reham
 */
public class ToDoUpdateHandler implements ActionHandler{

    private Gson gson;
    private ToDoController todoController;
    private UserController userController;
    
    @Override
    public void handleAction(String requestJsonObject, PrintStream printStream) {
        gson = new GsonBuilder().serializeNulls().setDateFormat("MMM dd, yyyy h:mm:ss a").create();
        todoController = new ToDoController();
        userController = new UserController();
        
        try{
            JSONObject jsonObject = new JSONObject(requestJsonObject);
            String todoJsonObject  = jsonObject.getJSONObject("entity").toString();
            
            ToDoEntity todo = gson.fromJson(todoJsonObject, ToDoEntity.class);
            todoController.update(todo);
            
            String responseJsonObject = gson.toJson(new EntityWrapper("update todo list", "ToDoEntity", todo));
            //supposing that ownly the owner can update it 
            ArrayList<UserEntity> todoCollaborators = userController.findAllListCollaborators(todo.getId());
            SocketHandler socketHandler;

            for(UserEntity collaborator : todoCollaborators){
                int indexOfCollaboratorId = SocketHandler.getOnlineIds().indexOf(collaborator.getId());
                
                if(indexOfCollaboratorId != -1){
                    System.out.println("i found user : "+collaborator.getId());
                  for (int i = 0; i < SocketHandler.socketHandlers.size(); i++) {
                      if (SocketHandler.socketHandlers.get(i).getUserId() == collaborator.getId()) {
                          socketHandler = SocketHandler.socketHandlers.get(i);
                          System.out.println("socketId: " + socketHandler.getId());
                          socketHandler.printResponse(responseJsonObject);
                          break;
                      }
                  }
                  
                }      
            }
        }catch(JSONException ex){
            ex.printStackTrace();
        }
    }
    
}
