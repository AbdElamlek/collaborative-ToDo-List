/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handlers;

import ControllerBase.ActionHandler;
import DAOController.Accept_RejectTaskController;
import DAOController.FriendRequestController;
import Entities.Accept_RejectTaskEntity;
import Entities.RequestEntity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.PrintStream;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Abd-Elmalek
 */
public class RejectTaskHandler implements ActionHandler{

   private Gson gson;
    private Accept_RejectTaskController accept_RejectTaskController;

    @Override
    public void handleAction(String requestJsonObject, PrintStream printStream) {
         try {
             gson = new GsonBuilder().serializeNulls().setDateFormat("MMM dd, yyyy h:mm:ss a").create();
             accept_RejectTaskController = new Accept_RejectTaskController();
             JSONObject jsonObject = new JSONObject(requestJsonObject);
             String requestTaskJsonObject  = jsonObject.getJSONObject("entity").toString();
             System.out.println("******json: "+requestTaskJsonObject);
             Accept_RejectTaskEntity accept_RejectTaskEntity = gson.fromJson(requestTaskJsonObject, Accept_RejectTaskEntity.class);
             if(accept_RejectTaskController.delete(accept_RejectTaskEntity)){
                 //sendNotification to user
                 System.out.println("deleted");
             }
             
         } catch (JSONException ex) {
             ex.printStackTrace();
         }
    }
    
}
