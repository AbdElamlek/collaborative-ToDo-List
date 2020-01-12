/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handlers;

import ControllerBase.ActionHandler;
import DAOController.ItemController;
import DAOController.ToDoController;
import Entities.ItemEntity;
import Entities.ToDoEntity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.PrintStream;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Omnia
 */
public class ItemDeleteHandler implements ActionHandler{

   private Gson gson;
    private ItemController itemController;
    
    @Override
    public void handleAction(String requestJsonObject, PrintStream printStream) {
        gson = new GsonBuilder().serializeNulls().setDateFormat("MMM dd, yyyy h:mm:ss a").create();
        itemController  = new ItemController();
        
        try{
            JSONObject jsonObject = new JSONObject(requestJsonObject);
            String itemJsonObject  = jsonObject.getJSONObject("entity").toString();
            
            ItemEntity item = gson.fromJson(itemJsonObject, ItemEntity.class);
            itemController.delete(item);
            
        }catch(JSONException ex){
            ex.printStackTrace();
        }
    }

    
}
