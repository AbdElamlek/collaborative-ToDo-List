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
import Entities.RequestEntity;
import Entities.UserEntity;
import com.google.gson.Gson;
import java.io.PrintStream;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author ahmedpro
 */
public class AcceptFriendHandler implements ActionHandler {

    @Override
    public void handleAction(String requestJsonObject, PrintStream printStream) {
        Gson gson = new Gson();
        FriendRequestController frc = new FriendRequestController();
        UserController uc = new UserController();
        try {
            // Create json object
            JSONObject jsonObject = new JSONObject(requestJsonObject);

            // Send accept action to the friend that i accept your request
            // Get request entity string from json.
            String requestEntityJson = jsonObject.getJSONObject("entity").toString();
            RequestEntity requestEntity = gson.fromJson(requestEntityJson, RequestEntity.class);

            // Delete the request from request friend table
            int receivedUserId = requestEntity.getReceivedUserId();
            int sentUserId = requestEntity.getSentUserId();
            frc.delete(sentUserId, receivedUserId);
            
            UserEntity userEntity = uc.findById(sentUserId);
            UserEntity friendEntity = uc.findById(receivedUserId);

            // Update database for adding a friend
            uc.insertFriend(sentUserId, receivedUserId);

            // Check if the receiving user is online
            boolean isOnline = false;
            SocketHandler friendSocket = null;
            for (int i = 0; i < SocketHandler.getOnlineIds().size(); i++) {
                if (receivedUserId == SocketHandler.getOnlineIds().get(i)) {
                    isOnline = true;
                    friendSocket = SocketHandler.socketHandlers.get(i);
                    friendEntity.setUserStatus(1);
                    break;
                }
            }

            // If the friend is online send notification
            EntityWrapper entityWrapper;
            String entityWrapperJson;
            if (isOnline) {
                // send a message to the reques's sender to add this friend
                userEntity.setUserStatus(1);
                entityWrapper = new EntityWrapper("acceptFriend", "UserEntity", userEntity);
                entityWrapperJson = gson.toJson(entityWrapper);
                friendSocket.printResponse(entityWrapperJson);
            }

            // Send accept action to that user after update database successfully
            requestEntity.setMessage(userEntity.getUserName());
            entityWrapper = new EntityWrapper("acceptFriend", "RequestEntity", friendEntity);
            entityWrapperJson = gson.toJson(entityWrapper);
            printStream.println(entityWrapperJson);

        } catch (JSONException ex) {
            System.out.println(ex);
        }
    }

}
