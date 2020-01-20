/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handlers;

import ControllerBase.ActionHandler;
import DAOController.AdapterController;
import DAOController.UserController;
import Entities.EntityWrapper;
import Entities.UserEntity;
import com.google.gson.Gson;
import java.io.PrintStream;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Abd-Elmalek
 */
public class SignUpHandler implements ActionHandler{
    
     private Consumer<Integer> userIdSetter;
    
    public void assignUserIdToSocket(Consumer<Integer> userIdSetter){
        this.userIdSetter = userIdSetter;
    }

    @Override
    public void handleAction(String responseJsonObject , PrintStream printStream) {
        try {
            Gson gson = new Gson();
            JSONObject jsonObject = new JSONObject(responseJsonObject);
            String userJsonObject = jsonObject.getJSONObject("entity").toString();
            UserEntity userEntity = gson.fromJson(userJsonObject, UserEntity.class);
            UserController userController = new UserController();
            AdapterController adapterController=new AdapterController();
            if (userController.insert(userEntity)) {
                //registerd!
                if(userIdSetter != null)
                    userIdSetter.accept(userEntity.getId());
                System.out.println("server : "+userEntity.getId());
                String userJson=adapterController.entity2Json(new EntityWrapper("signup","UserEntity",userEntity));
                System.out.println("server sent userjson: "+userJson);
                printStream.println(userJson);
            } else {
                //Not Registered
            }
        } catch (JSONException ex) {
           ex.printStackTrace();
        }
    }
    
}
