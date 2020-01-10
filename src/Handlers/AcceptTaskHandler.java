/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handlers;

import ControllerBase.ActionHandler;
import DAOController.Accept_RejectTaskController;
import DAOController.TaskController;
import DTOs.Accept_RejectTaskDTO;
import Entities.Accept_RejectTaskEntity;
import Entities.TaskEntity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.PrintStream;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Abd-Elmalek
 */
public class AcceptTaskHandler implements ActionHandler{
private Gson gson;
private TaskController taskController;
private Accept_RejectTaskController accept_RejectTaskController;

    @Override
    public void handleAction(String requestJsonObject, PrintStream printStream) {
        try {
                gson = new GsonBuilder().serializeNulls().setDateFormat("MMM dd, yyyy h:mm:ss a").create();
                taskController = new TaskController();
                accept_RejectTaskController = new Accept_RejectTaskController();
                JSONObject jsonObject = new JSONObject(requestJsonObject);
                String requestTaskJsonObject  = jsonObject.getJSONObject("entity").toString();
                System.out.println("******json: "+requestTaskJsonObject);
                Accept_RejectTaskDTO acceptTaskDTO = gson.fromJson(requestTaskJsonObject, Accept_RejectTaskDTO.class);
                if(taskController.insertUserTaskAssignment(acceptTaskDTO.getUserId(),acceptTaskDTO.getTaskId())){
                    System.out.println("inserted");
                    Accept_RejectTaskEntity accept_RejectTaskEntity = new Accept_RejectTaskEntity();
                    accept_RejectTaskEntity.setTaskId(acceptTaskDTO.getTaskId());
                    if(accept_RejectTaskController.delete(accept_RejectTaskEntity)){
                        System.out.println("request deleted");
                    }
                }
            } catch (JSONException ex) {
                ex.printStackTrace();
            }
    }
    
}
