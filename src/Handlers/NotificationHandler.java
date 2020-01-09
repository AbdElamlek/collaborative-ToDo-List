/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handlers;

import ControllerBase.ActionHandler;
import DAOController.NotificationController;
import Entities.NotificationEntity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.PrintStream;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Reham
 */
public class NotificationHandler implements ActionHandler{
    private Gson gson;
    private NotificationController notificationController;
    
    @Override
    public void handleAction(String requestJsonObject, PrintStream printStream) {
        gson = new GsonBuilder().serializeNulls().setDateFormat("MMM dd, yyyy h:mm:ss a").create();
        notificationController = new NotificationController();
        
        try{
            JSONObject jsonObject = new JSONObject(requestJsonObject);
            String notificationJsonObject  = jsonObject.getJSONObject("entity").toString();
            
            NotificationEntity notification = gson.fromJson(notificationJsonObject, NotificationEntity.class);
            notificationController.insert(notification);
            
        }catch(JSONException ex){
            ex.printStackTrace();
        }
    }
    
}
