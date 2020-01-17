/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOController;

import ControllerBase.AdapterInterface;
import Entities.EntityWrapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 *
 * @author pc
 */
public class AdapterController implements AdapterInterface {

    GsonBuilder gsonBuilder;
    Gson gson;

    public AdapterController() {
        gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.serializeNulls().setDateFormat("MMM dd, yyyy h:mm:ss a").create();
        
    }

    @Override
    public String entity2Json(EntityWrapper entity) {
        String json=gson.toJson(entity);
        return json;
    }

    @Override
    public EntityWrapper json2Entity(String json) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
