/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handlers;

import ControllerBase.ActionHandler;
import DAOController.FriendRequestController;
import Entities.FriendRequestEntity;
import com.google.gson.Gson;
import java.io.PrintStream;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author ahmedpro
 */
public class DeclineFriendHandler implements ActionHandler {

    @Override
    public void handleAction(String requestJsonObject, PrintStream printStream) {
        Gson gson = new Gson();
        FriendRequestController frc = new FriendRequestController();
        try {

            // Create json object
            JSONObject jsonObject = new JSONObject(requestJsonObject);

            // Get request entity string from json.
            String requestEntityJson = jsonObject.getJSONObject("entity").toString();
            FriendRequestEntity requestEntity = gson.fromJson(requestEntityJson, FriendRequestEntity.class);

            // delete request from friend request table.
            frc.delete(requestEntity);
            
        } catch (JSONException ex) {
            System.out.println(ex);
        }
    }

}
