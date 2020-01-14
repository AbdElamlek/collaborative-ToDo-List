/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handlers;

import ControllerBase.ActionHandler;
import DAOController.ItemController;
import DAOController.ToDoController;
import Entities.EntityWrapper;
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
public class ItemCreationHandler implements ActionHandler{
 
    private Gson gson;
    private ItemController itemController;
        
    @Override
  
    public void handleAction(String requestJsonObject, PrintStream printStream) {
        itemController = new ItemController();
        gson = new GsonBuilder().serializeNulls().setDateFormat("MMM dd, yyyy h:mm:ss a").create();
        try{
            JSONObject jsonObject = new JSONObject(requestJsonObject);
            String iTemJsonObject  = jsonObject.getJSONObject("entity").toString();
            ItemEntity item = gson.fromJson(iTemJsonObject, ItemEntity.class);
            
            if(itemController.insert(item)){
                String responseJsonObject = gson.toJson(new EntityWrapper("create item", "ItemEntity", item));
                printStream.println(responseJsonObject);
            }
        }catch(JSONException ex){
            ex.printStackTrace();
        }
    }
    
}
