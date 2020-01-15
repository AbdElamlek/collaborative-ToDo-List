/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handlers;

import ControllerBase.ActionHandler;
import DAOController.UserController;
import Entities.EntityWrapper;
import Entities.UserEntity;
import com.google.gson.Gson;
import java.io.PrintStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author ahmedpro
 */
public class SearchFriendHandler implements ActionHandler {

    private int userId;

    public SearchFriendHandler(int userId) {
        this.userId = userId;
    }

    @Override
    public void handleAction(String requestJsonObject, PrintStream printStream) {

        try {
            Gson gson = new Gson();
            UserController controller = new UserController();

            JSONObject jsonObject = new JSONObject(requestJsonObject);
            String friendEntityJson = jsonObject.getJSONObject("entity").toString();
            UserEntity friendEntity = gson.fromJson(friendEntityJson, UserEntity.class);
            // The received entity has only user name and we need more data
            // like id
            String friendUserName = friendEntity.getUserName();
            friendEntity = controller.findByUserName(friendUserName);
            // Get the user friend's list to check if that user name is already
            // exist in the friend list or not            
            List<UserEntity> list = controller.findAllUserFriends(userId);
            boolean isFriendExist = false;
            for (int i = 0; i < list.size(); i++) {
                if (friendUserName.equals(list.get(i).getUserName())) {
                    isFriendExist = true;
                    break;
                }
            }
            
            // Get the user entity to check if the user name is the same of 
            // user name for searching
            UserEntity userEntity = controller.findById(userId);
            
            EntityWrapper entityWrapper;
            if (friendEntity == null) {
                // The user name is not exist
                friendEntity = new UserEntity();
                friendEntity.setId(-1);
                entityWrapper = new EntityWrapper("searchFriend", "UserEntity", friendEntity);

            } else if (isFriendExist) {
                // The friend is already exist
                friendEntity.setId(-2);
                entityWrapper = new EntityWrapper("searchFriend", "UserEntity", friendEntity);
            } else if (userEntity.getUserName().equals(friendUserName)) {
                friendEntity.setId(-3);
                entityWrapper = new EntityWrapper("searchFriend", "UserEntity", friendEntity);
            } else {
                entityWrapper = new EntityWrapper("searchFriend", "UserEntity", friendEntity);
            }

            String entityWrapperJson = gson.toJson(entityWrapper);
            printStream.println(entityWrapperJson);

        } catch (JSONException ex) {
            System.out.println(ex);
        }

    }

}
