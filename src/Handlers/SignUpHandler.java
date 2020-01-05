/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handlers;

import ControllerBase.ActionHandler;
import DAOController.UserController;
import Entities.UserEntity;
import com.google.gson.Gson;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Abd-Elmalek
 */
public class SignUpHandler implements ActionHandler{

    @Override
    public void handleAction(String responseJsonObject , PrintStream printStream) {
        try {
            Gson gson = new Gson();
            JSONObject jsonObject = new JSONObject(responseJsonObject);
            String userJsonObject = jsonObject.getJSONObject("entity").toString();
            UserEntity userEntity = gson.fromJson(userJsonObject, UserEntity.class);
            UserController userController = new UserController();
            if (userController.insert(userEntity)) {
                //registerd!
                printStream.println(responseJsonObject);
            } else {
                //Not Registered
            }
        } catch (JSONException ex) {
           ex.printStackTrace();
        }
    }
    
}
