/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handlers;

import ControllerBase.ActionHandler;
import DAOController.AdapterController;
import DAOController.CollaboratorRequestController;
import DAOController.ToDoController;
import DAOController.UserController;
import Entities.CollaborationRequestEntity;
import Entities.CollaboratorDTO;
import Entities.ToDoEntity;
import Entities.UserEntity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author pc
 */
public class AcceptCollaboratorRequestHandler implements ActionHandler {

    @Override
    public void handleAction(String requestJsonObject, PrintStream printStream) {
        try {
            Gson gson = new GsonBuilder().serializeNulls().setDateFormat("MMM dd, yyyy h:mm:ss a").create();
            JSONObject jsonObject = new JSONObject(requestJsonObject);
            String collaboratorJsonObject = jsonObject.getJSONObject("entity").toString();
            CollaboratorDTO collaboratorDTO = gson.fromJson(collaboratorJsonObject, CollaboratorDTO.class);
            
            AdapterController ac = new AdapterController();
            ToDoController tdc = new ToDoController();
            UserController uc=new UserController();
            CollaboratorRequestController crc=new CollaboratorRequestController();
            
            CollaborationRequestEntity cre=crc.findById(collaboratorDTO.getReqId());
            if (tdc.insertUserTodoCollaboration(cre.getReceivedUserId(), cre.getTodoId())) {
                //collaborator is added successfully to todo
                UserEntity collaboratorInfo=uc.findById(cre.getReceivedUserId());
                crc.delete(cre);
            }else{
                //failed to add collaborator !
            }

        } catch (JSONException ex) {
            Logger.getLogger(AcceptCollaboratorRequestHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
