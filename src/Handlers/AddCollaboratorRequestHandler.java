/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handlers;

import Connection.SocketHandler;
import ControllerBase.ActionHandler;
import DAOController.AdapterController;
import DAOController.CollaboratorRequestController;
import DAOController.FriendRequestController;
import DAOController.NotificationController;
import DAOController.UserController;
import Entities.EntityWrapper;
import Entities.CollaborationRequestEntity;
import Entities.NotificationEntity;
import Entities.UserEntity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author pc
 */
public class AddCollaboratorRequestHandler implements ActionHandler {

    @Override
    public void handleAction(String requestJsonObject, PrintStream printStream) {
        try {
            Gson gson = new Gson();
            JSONObject jsonObject = new JSONObject(requestJsonObject);
            String reqJsonObject = jsonObject.getJSONObject("entity").toString();
            CollaborationRequestEntity cre = gson.fromJson(reqJsonObject, CollaborationRequestEntity.class);
            CollaboratorRequestController crc = new CollaboratorRequestController();
            AdapterController ac = new AdapterController();
            UserController uc = new UserController();
            NotificationController nc = new NotificationController();
            SocketHandler sh;
            if (crc.insert(cre)) {
                
                NotificationEntity ne = new NotificationEntity();
                UserEntity ue = uc.findById(cre.getSentUserId());
                cre.setMessage(ue.getUserName()+ "Collaborator Request");
                ne.setMsg(ue.getUserName() + " sent add collaborator request");
                ne.setReceivedUserId(cre.getReceivedUserId());
                ne.setSentUserId(cre.getSentUserId());
                ne.setTime(cre.getTime());
                System.out.println("collaboratorRequestEntity date: "+cre.getTime());
                if (nc.insert(ne)) {
                    //Notification and request is sent to the reciever 
                    String requestJson = ac.entity2Json(new EntityWrapper("recieve collaborator request", "CollaborationRequestEntity", cre));
                    String notificationJson = ac.entity2Json(new EntityWrapper("recieve collaborator notification", "NotificationEntity", ne));
                    System.out.println("sender:  "+ue.getUserName()+"\n"+requestJson);
                    System.out.println("sender: "+ue.getUserName()+"\n"+notificationJson);
                    
                    int indexOfOnlineCollaborator = SocketHandler.getOnlineIds().indexOf(cre.getReceivedUserId());
                    if (indexOfOnlineCollaborator != -1) {
                        System.out.println("server///online collaborator id: " + indexOfOnlineCollaborator);
                        sh = SocketHandler.socketHandlers.get(indexOfOnlineCollaborator);
                        sh.printResponse(requestJson);
                        sh.printResponse(notificationJson);
                    }else{
                        System.out.println("no one is online");
                    }
                } else {
                    //insertion failed !
                }
            } else {
                //request hasn't been added!
            }
        } catch (JSONException ex) {
            Logger.getLogger(AddCollaboratorRequestHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
