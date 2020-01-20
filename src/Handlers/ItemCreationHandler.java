/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handlers;

import Connection.SocketHandler;
import ControllerBase.ActionHandler;
import DAOController.ItemController;
import DAOController.ToDoController;
import DAOController.UserController;
import Entities.EntityWrapper;
import Entities.ItemEntity;
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
 * @author Omnia
 */
public class ItemCreationHandler implements ActionHandler{
 
    private Gson gson;
    private ItemController itemController;
    private UserController userController;
    ToDoController toDoController ;
        
    @Override
  
    public void handleAction(String requestJsonObject, PrintStream printStream) {
        itemController = new ItemController();
        userController = new UserController();
        toDoController = new ToDoController();
        gson = new GsonBuilder().serializeNulls().setDateFormat("MMM dd, yyyy h:mm:ss a").create();
        try{
            JSONObject jsonObject = new JSONObject(requestJsonObject);
            String iTemJsonObject  = jsonObject.getJSONObject("entity").toString();
            ItemEntity item = gson.fromJson(iTemJsonObject, ItemEntity.class);
            
            if(itemController.insert(item)){
                String responseJsonObject = gson.toJson(new EntityWrapper("create item", "ItemEntity", item));
                
                System.out.println("--------------------------------item created----------"+responseJsonObject);
                printStream.println(responseJsonObject);
                     int ownerId = toDoController.findById(item.getTodoId()).getOwnerId();
                     List<UserEntity> collaborators = userController.findAllListCollaborators(item.getTodoId());
                     List<Integer> onLineUsers = SocketHandler.getOnlineIds();
                     for(UserEntity userEntity :collaborators){
                     if(onLineUsers.contains(userEntity.getId())){
                        
                        for(int i =0; i< SocketHandler.socketHandlers.size();i++){
                            if(SocketHandler.socketHandlers.get(i).getUserId() == userEntity.getId() 
                                || SocketHandler.socketHandlers.get(i).getUserId() == ownerId){
                                       SocketHandler.socketHandlers.get(i).printResponse(responseJsonObject);
                                
                            }
                        }
                        
                        
                     }
                     
                     }
            }
        }catch(JSONException ex){
            ex.printStackTrace();
        }
    }
    
}
