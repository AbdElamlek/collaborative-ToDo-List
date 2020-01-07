/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handlers;

import ControllerBase.ActionHandler;
import DAOController.TaskController;
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

    @Override
    public void handleAction(String requestJsonObject, PrintStream printStream) {
        try {
                gson = new GsonBuilder().serializeNulls().setDateFormat("MMM dd, yyyy h:mm:ss a").create();
                taskController = new TaskController();
                JSONObject jsonObject = new JSONObject(requestJsonObject);
                String requestTaskJsonObject  = jsonObject.getJSONObject("entity").toString();
                System.out.println("******json: "+requestTaskJsonObject);
                TaskEntity taskEntity = gson.fromJson(requestTaskJsonObject, TaskEntity.class);
                if(taskController.insertUserTaskAssignment(Integer.parseInt(taskEntity.getAssignedUsersList().get(taskEntity.getAssignedUsersList().size()-1)),taskEntity.getId())){
                    System.out.println("inserted");
                }
            } catch (JSONException ex) {
                ex.printStackTrace();
            }
    }
    
}
