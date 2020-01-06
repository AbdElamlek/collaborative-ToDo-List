/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import DAOController.ToDoController;
import Entities.ToDoEntity;
import com.google.gson.Gson;
import java.sql.SQLException;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Reham
 */
public class ToDoActionHandler implements ActionHandler{
    private Gson gson;
    private ToDoController todoController;
    
    @Override
    public String handleAction(String requestJsonObject) {
        todoController = new ToDoController();
        String responseJsonObject = "";
        
        try{
            JSONObject jsonObject = new JSONObject(requestJsonObject);
            String todoJsonObject  = jsonObject.getJSONObject("entity").toString();
            ToDoEntity todo = gson.fromJson(todoJsonObject, ToDoEntity.class);
            
            switch(jsonObject.getJSONObject("action").toString()){
                case "create todo list":
                    responseJsonObject = handleTodoCreation(todo);
                
            }
        }catch(JSONException ex){
            ex.printStackTrace();
        }
        return responseJsonObject;
    }
    
    public String handleTodoCreation(ToDoEntity todo){
        todoController.insert(todo);
        return "";
    }
    
}
