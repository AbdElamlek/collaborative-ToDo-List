/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handlers;

import Connection.SocketHandler;
import ControllerBase.ActionHandler;
import DAOController.CommentController;
import DAOController.ToDoController;
import DAOController.UserController;
import Entities.CommentEntity;
import Entities.EntityWrapper;
import Entities.UserEntity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.PrintStream;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Abd-Elmalek
 */
public class CommentCreationHandler implements ActionHandler{
    private Gson gson;
    private UserController userController;
    private CommentController commentController;
    private ToDoController toDoController;
    
     @Override
    public void handleAction(String requestJsonObject, PrintStream printStream) {
            try {
                gson = new GsonBuilder().serializeNulls().setDateFormat("MMM dd, yyyy h:mm:ss a").create();
                userController = new UserController();
                commentController = new CommentController();
                toDoController = new ToDoController();
                JSONObject jsonObject = new JSONObject(requestJsonObject);
                String requestCommentJsonObject  = jsonObject.getJSONObject("entity").toString();
                System.out.println("******json: "+requestCommentJsonObject);
                CommentEntity commentEntity = gson.fromJson(requestCommentJsonObject, CommentEntity.class);
                if(commentController.insert(commentEntity)){
                    System.out.println("inserted");
                    System.out.println("comment id is "+commentEntity.getId());
                    System.out.println("item id is "+commentEntity.getItemId());
                    System.out.println("todo id is "+commentEntity.getTodoId());
                     EntityWrapper entityWrapper = new EntityWrapper("add comment", "entity", commentEntity);
                     String taskCreationJsonResponse = gson.toJson(entityWrapper);
                     List<UserEntity> collaborators = userController.findAllListCollaborators(commentEntity.getTodoId());
                     List<Integer> onLineUsers = SocketHandler.getOnlineIds();
                     int todoOwnerId = toDoController.findById(commentEntity.getTodoId()).getOwnerId();
                     if(collaborators.size() > 0){
                            for(UserEntity userEntity : collaborators){
                           if(onLineUsers.contains(userEntity.getId()) ||onLineUsers.contains(commentEntity.getCommentOwnerId())){

                               for(int i =0; i< SocketHandler.socketHandlers.size();i++){
                                   if(SocketHandler.socketHandlers.get(i).getUserId()== userEntity.getId()
                                   || SocketHandler.socketHandlers.get(i).getUserId() == commentEntity.getCommentOwnerId()
                                   || SocketHandler.socketHandlers.get(i).getUserId() == todoOwnerId){
                                       SocketHandler.socketHandlers.get(i).printResponse(taskCreationJsonResponse);

                                   }
                               }


                        }

                        }
                     }else{
                          for(int i =0; i< SocketHandler.socketHandlers.size();i++){
                                   if(SocketHandler.socketHandlers.get(i).getUserId() == commentEntity.getCommentOwnerId()){
                                       SocketHandler.socketHandlers.get(i).printResponse(taskCreationJsonResponse);

                                   }
                               }
                         
                     }
                }
            } catch (JSONException ex) {
                ex.printStackTrace();
            }
    }
}
