/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handlers;

import Connection.SocketHandler;
import ControllerBase.ActionHandler;
import DAOController.FriendRequestController;
import DAOController.UserController;
import Entities.EntityWrapper;
import Entities.FriendRequestEntity;
import Entities.UserEntity;
import com.google.gson.Gson;
import java.io.PrintStream;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author ahmedpro
 */
public class DeleteFriendHandler implements ActionHandler {

    @Override
    public void handleAction(String requestJsonObject, PrintStream printStream) {

        Gson gson = new Gson();
        FriendRequestController frc = new FriendRequestController();
        UserController uc = new UserController();
        try {

            // Create json object
            JSONObject jsonObject = new JSONObject(requestJsonObject);

            // Get request entity string from json.
            String requestEntityJson = jsonObject.getJSONObject("entity").toString();
            FriendRequestEntity requestEntity = gson.fromJson(requestEntityJson, FriendRequestEntity.class);

            // Update database 
            int receivedUserId = requestEntity.getReceivedUserId();
            int sentUserId = requestEntity.getSentUserId();
            uc.deleteFriend(sentUserId, receivedUserId);
            uc.deleteFriend(receivedUserId, sentUserId);

            // Check if the receiving user is online
            boolean isOnline = false;
            SocketHandler friendSocket = null;
            for (int i = 0; i < SocketHandler.getOnlineIds().size(); i++) {
                if (receivedUserId == SocketHandler.getOnlineIds().get(i)) {
                    isOnline = true;
                    friendSocket = SocketHandler.socketHandlers.get(i);
                    break;
                }
            }

            // If the friend is online send a order to delete the user
            if (isOnline) {
                // send a notification to the online user
                EntityWrapper entityWrapper = new EntityWrapper("deleteFriend", "RequestEntity", requestEntity);
                String entityWrapperJson = gson.toJson(entityWrapper);
                friendSocket.printResponse(entityWrapperJson);
            }

        } catch (JSONException ex) {
            System.out.println(ex);
        }
    }

}
