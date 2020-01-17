/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handlers;

import ControllerBase.ActionHandler;
import DAOController.CollaboratorRequestController;
import Entities.CollaborationRequestEntity;
import Entities.CollaboratorDTO;
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
public class RejectCollaboratorRequestHandler implements ActionHandler {

    @Override
    public void handleAction(String requestJsonObject, PrintStream printStream) {
        try {
            Gson gson = new GsonBuilder().serializeNulls().setDateFormat("MMM dd, yyyy h:mm:ss a").create();
            JSONObject jsonObject = new JSONObject(requestJsonObject);
            String collaborationRequestJsonObject = jsonObject.getJSONObject("entity").toString();
            CollaborationRequestEntity collaborationRequestEntity = gson.fromJson(collaborationRequestJsonObject, CollaborationRequestEntity.class);
            
            CollaboratorRequestController collaboratorRequestController = new CollaboratorRequestController();
            
            collaboratorRequestController.delete(collaborationRequestEntity);
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
    }

}
