/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handlers;

import ControllerBase.ActionHandler;
import DAOController.ToDoController;
import Entities.ToDoEntity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.PrintStream;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Reham
 */
public class ToDoUpdateHandler implements ActionHandler{

    private Gson gson;
    private ToDoController todoController;
    
    @Override
    public void handleAction(String requestJsonObject, PrintStream printStream) {
        gson = new GsonBuilder().serializeNulls().setDateFormat("MMM dd, yyyy h:mm:ss a").create();
        todoController = new ToDoController();
        
        try{
            JSONObject jsonObject = new JSONObject(requestJsonObject);
            String todoJsonObject  = jsonObject.getJSONObject("entity").toString();
            
            ToDoEntity todo = gson.fromJson(todoJsonObject, ToDoEntity.class);
            todoController.update(todo);
            
        }catch(JSONException ex){
            ex.printStackTrace();
        }
    }
    
}
