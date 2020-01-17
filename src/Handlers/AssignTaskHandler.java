/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handlers;

import Connection.SocketHandler;
import ControllerBase.ActionHandler;
import DAOController.Accept_RejectTaskController;
import DAOController.NotificationController;
import Entities.Accept_RejectTaskEntity;
import Entities.EntityWrapper;
import Entities.NotificationEntity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.PrintStream;
import java.sql.Date;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Abd-Elmalek
 */
public class AssignTaskHandler implements ActionHandler{
    
    private Gson gson;
    private Accept_RejectTaskController accept_RejectTaskController;
    private NotificationController notificationController;

    @Override
    public void handleAction(String requestJsonObject, PrintStream printStream) {
         try {
             gson = new GsonBuilder().serializeNulls().setDateFormat("MMM dd, yyyy h:mm:ss a").create();
             accept_RejectTaskController = new Accept_RejectTaskController();
             JSONObject jsonObject = new JSONObject(requestJsonObject);
             String requestTaskJsonObject  = jsonObject.getJSONObject("entity").toString();
             System.out.println("******json: "+requestTaskJsonObject);
             Accept_RejectTaskEntity accept_RecjectTaskEntity = gson.fromJson(requestTaskJsonObject, Accept_RejectTaskEntity.class);
             if(accept_RejectTaskController.insert(accept_RecjectTaskEntity)){
                 //sendNotification to user
                 System.out.println("inserted");
                 notificationController = new NotificationController();
                 NotificationEntity notificationEntity = new NotificationEntity();
                 notificationEntity.setSentUserId(accept_RecjectTaskEntity.getSentUserId());
                 notificationEntity.setReceivedUserId(accept_RecjectTaskEntity.getReceivedUserId());
                 notificationEntity.setTime(new Date(new java.util.Date().getTime()));
                 if(notificationController.insert(notificationEntity)){
                     List<Integer> onLineUsers = SocketHandler.getOnlineIds();
                     if(onLineUsers.contains(notificationEntity.getReceivedUserId())){
                        EntityWrapper entityWrapper = new EntityWrapper("notification", "entity", notificationEntity);
                        String notoficationJsonResponse = gson.toJson(entityWrapper);
                        for(int i =0; i< SocketHandler.socketHandlers.size();i++){
                            if(SocketHandler.socketHandlers.get(i).getUserId()== notificationEntity.getReceivedUserId()){
                                SocketHandler.socketHandlers.get(i).printResponse(notoficationJsonResponse);
                                break;
                            }
                        }
                        
                        
                     }
                 }
             }
             
         } catch (JSONException ex) {
             ex.printStackTrace();
         }
    }
    
}
