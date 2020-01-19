/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handlers;

import Connection.SocketHandler;
import ControllerBase.ActionHandler;
import DAOController.UserController;
import Entities.EntityWrapper;
import Entities.UserEntity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Reham
 */
public class LogoutHandler implements ActionHandler{
    private Gson gson;
    private UserController userController;
    
    @Override
    public void handleAction(String requestJsonObject, PrintStream printStream) {
        try {
            gson = new GsonBuilder().serializeNulls().setDateFormat("MMM dd, yyyy h:mm:ss a").create();
            userController = new UserController();
            
            JSONObject jsonObject = new JSONObject(requestJsonObject);
            String userJsonObject  = jsonObject.getJSONObject("entity").toString();
            
            UserEntity user = gson.fromJson(userJsonObject, UserEntity.class);
            user.setUserStatus(0);
            ArrayList<UserEntity> friends = userController.findAllUserFriends(user.getId());
            
            String friendOnlineJsonObject = gson.toJson(new EntityWrapper("offline friend", "UserEntity", user));    
            SocketHandler socketHandler;
            
            for(UserEntity friend : friends){
               if(SocketHandler.getOnlineIds().contains(friend.getId())){
                   
                    int i = 0;
                    while((SocketHandler.socketHandlers.get(i).getUserId()) != friend.getId())
                        i++; 
                  
                    socketHandler = SocketHandler.socketHandlers.get(i);
                    socketHandler.printResponse(friendOnlineJsonObject);
               } 
            }
            
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
    }
    
}
