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
import java.io.PrintStream;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author ahmedpro
 */
public class AddFriendHandler implements ActionHandler {

    @Override
    public void handleAction(String requestJsonObject, PrintStream printStream) {
        Gson gson = new Gson();
        try {
            
            // Don't forget to update database first
            
            JSONObject jsonObject = new JSONObject(requestJsonObject);
            
            // Get entity string from json.
            String friendJson = jsonObject.getJSONObject("entity").toString();
            // Reccieved entity.
            // Convert string entity to entity object.
            UserEntity friendEntity = gson.fromJson(friendJson, UserEntity.class);
            // Get friend's id
            int friendId = friendEntity.getId();
            // Check if the client that has this id is online
            boolean isOnline = false;
            SocketHandler socketHandler = null;
            for (int i = 0; i < SocketHandler.getOnlineIds().size(); i++) {
                if (friendId == SocketHandler.getOnlineIds().get(i)) {
                    isOnline = true;
                    socketHandler = SocketHandler.socketHandlers.get(i);
                    break;
                }
            }
            
            // If the clinet is online
            if (isOnline) {
                UserController userController = new UserController();
                UserEntity senterUser = userController.findById(socketHandler.getUserId());
                EntityWrapper entityWrapper = new EntityWrapper("addFriend", "UserEntity", senterUser);
                String entityWrapperString = gson.toJson(entityWrapper);
                socketHandler.printResponse(entityWrapperString);
            }
        } catch (JSONException ex) {
            System.out.println(ex);
        }
    }

}
