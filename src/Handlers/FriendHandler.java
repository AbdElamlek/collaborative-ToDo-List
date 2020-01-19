/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handlers;

import ControllerBase.ActionHandler;
import DAOController.FriendRequestController;
import DAOController.UserController;
import Entities.EntityWrapper;
import Entities.UserEntity;
import com.google.gson.Gson;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author ahmedpro
 */
public class FriendHandler implements ActionHandler {

    @Override
    public void handleAction(String requestJsonObject, PrintStream printStream) {
        
        Gson gson = new Gson();
        UserController controller = new UserController();
        try {
            JSONObject jsonObject = new JSONObject(requestJsonObject);
            String friendJson = jsonObject.getJSONObject("entity").toString();
            UserEntity receivedFriendEntity = gson.fromJson(friendJson, UserEntity.class);
            UserEntity responseFriendFriend = controller.findByUserName(receivedFriendEntity.getUserName());
            EntityWrapper entityWrapper = new EntityWrapper("searchFriend", "UserEntity", responseFriendFriend);
            String entityWrapperString = gson.toJson(entityWrapper);
            printStream.println(entityWrapperString);
        } catch (JSONException ex) {
            System.out.println(ex);
        }
    }
    
}
