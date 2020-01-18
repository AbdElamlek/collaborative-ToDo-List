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
public class ItemDeleteHandler implements ActionHandler{

    private Gson gson;
    private ItemController itemController;
    private UserController userController;
    private ToDoController toDoController;
    @Override
    public void handleAction(String requestJsonObject, PrintStream printStream) {
        gson = new GsonBuilder().serializeNulls().setDateFormat("MMM dd, yyyy h:mm:ss a").create();
        itemController  = new ItemController();
        userController = new UserController();
        toDoController = new ToDoController();
        try{
            JSONObject jsonObject = new JSONObject(requestJsonObject);
            String itemJsonObject  = jsonObject.getJSONObject("entity").toString();
            System.out.println("------------------------------------------"+itemJsonObject);
            ItemEntity item = gson.fromJson(itemJsonObject, ItemEntity.class);
            
            if(itemController.delete(item)){
                System.out.println("deleted");
                  String responseJsonObject = gson.toJson(new EntityWrapper("delete item", "ItemEntity", item));
                List<UserEntity> collaborators = userController.findAllListCollaborators(item.getTodoId());
                     List<Integer> onLineUsers = SocketHandler.getOnlineIds();
                     ToDoEntity toDoEntity =toDoController.findById(item.getTodoId());
                     collaborators.stream().filter((userEntity) -> (onLineUsers.contains(userEntity.getId()))).forEachOrdered((userEntity) -> {
                         for(int i =0; i< SocketHandler.socketHandlers.size();i++){
                             if(SocketHandler.socketHandlers.get(i).getUserId()== userEntity.getId() || SocketHandler.socketHandlers.get(i).getUserId()==toDoEntity.getOwnerId()){
                                 SocketHandler.socketHandlers.get(i).printResponse(responseJsonObject);
                                 
                             }
                         }
                });
            }
            
        }catch(JSONException ex){
            ex.printStackTrace();
        }
    }

    
}
