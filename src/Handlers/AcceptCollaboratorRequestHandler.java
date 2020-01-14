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
import DAOController.ToDoController;
import DAOController.UserController;
import Entities.CollaborationRequestEntity;
import Entities.CollaboratorDTO;
import Entities.EntityWrapper;
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
            String collaborationRequestJsonObject = jsonObject.getJSONObject("entity").toString();
            CollaborationRequestEntity collaborationRequestEntity = gson.fromJson(collaborationRequestJsonObject, CollaborationRequestEntity.class);
            
            int todoId = collaborationRequestEntity.getTodoId();
            int senderId = collaborationRequestEntity.getSentUserId();
            int receiverId = collaborationRequestEntity.getReceivedUserId();
            
            ToDoController toDoController = new ToDoController();
            CollaboratorRequestController collaboratorRequestController = new CollaboratorRequestController();
            UserController userController = new UserController();
            
            ArrayList<UserEntity> todoCollaborators = userController.findAllListCollaborators(todoId);
            SocketHandler socketHandler;
             
            if(toDoController.insertUserTodoCollaboration(todoId, receiverId)){
                collaboratorRequestController.delete(collaborationRequestEntity);

                String responseJsonObject = gson.toJson(new EntityWrapper("accept collaborator request", "CollaboratorDTO", new CollaboratorDTO(userController.findById(receiverId), senderId, todoId)));

                // SEND TO ALL OTHER ONLINE COLLABORATORS
                
                for(UserEntity collaborator : todoCollaborators){
                    int indexOfCollaboratorId = SocketHandler.getOnlineIds().indexOf(collaborator.getId());

                    if(indexOfCollaboratorId != -1){
                      socketHandler = SocketHandler.socketHandlers.get(indexOfCollaboratorId);
                      socketHandler.printResponse(responseJsonObject);
                    }      
                }
                
                //SEND TO OWNER IF ONLINE
                
                int indexOfOwnerId = SocketHandler.getOnlineIds().indexOf(senderId);
                if(indexOfOwnerId != -1){
                      socketHandler = SocketHandler.socketHandlers.get(indexOfOwnerId);
                      socketHandler.printResponse(responseJsonObject);
                }
            }
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
    }

}
