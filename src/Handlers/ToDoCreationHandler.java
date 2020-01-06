/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handlers;

import ControllerBase.ActionHandler;
import DAOController.ToDoController;
import Entities.EntityWrapper;
import Entities.ToDoEntity;
import com.google.gson.Gson;
import java.io.PrintStream;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Reham
 */
public class ToDoCreationHandler implements ActionHandler{
    private Gson gson;
    private ToDoController todoController;
    
    @Override
    public void handleAction(String requestJsonObject, PrintStream printStream) {
        todoController = new ToDoController();
        
        try{
            JSONObject jsonObject = new JSONObject(requestJsonObject);
            String todoJsonObject  = jsonObject.getJSONObject("entity").toString();
            ToDoEntity todo = gson.fromJson(todoJsonObject, ToDoEntity.class);
            
            if(todoController.insert(todo)){
                String responseJsonObject = gson.toJson(new EntityWrapper("create todo list", "ToDoEntity", todo));
                printStream.println(responseJsonObject);
            }
        }catch(JSONException ex){
            ex.printStackTrace();
        }
    }
    
}
