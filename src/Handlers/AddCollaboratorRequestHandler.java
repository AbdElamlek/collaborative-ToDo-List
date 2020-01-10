/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handlers;

import ControllerBase.ActionHandler;
import DAOController.AdapterController;
import DAOController.CollaboratorRequestController;
import DAOController.FriendRequestController;
import Entities.EntityWrapper;
import Entities.CollaborationRequestEntity;
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
            Gson gson = new GsonBuilder().serializeNulls().setDateFormat("MMM dd, yyyy h:mm:ss a").create();
            JSONObject jsonObject = new JSONObject(requestJsonObject);
            String reqJsonObject = jsonObject.getJSONObject("entity").toString();
            CollaborationRequestEntity cre = gson.fromJson(reqJsonObject,CollaborationRequestEntity.class);
            CollaboratorRequestController crc=new CollaboratorRequestController();
            AdapterController ac = new AdapterController();
            if (crc.insert(cre)) {
                System.out.println("server " + cre.getId());
                String reqJson = ac.entity2Json(new EntityWrapper("add collaborator request", "CollaborationRequestEntity", cre));
                System.out.println("server sent reqjson: " + reqJson);
                //printStream.println(reqJson);
            }
        } catch (JSONException ex) {
            Logger.getLogger(AddCollaboratorRequestHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
