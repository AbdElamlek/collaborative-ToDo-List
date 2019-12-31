/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOController;

import DAOs.BaseDAO;
import Entities.BaseEntity;
import Entities.RequestEntity;
import java.util.ArrayList;

/**
 *
 * @author Abd-Elmalek
 */
public class RequestController <RequestDAO> implements BaseDAO<RequestEntity>{

    @Override
    public ArrayList<RequestEntity> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RequestEntity findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean insert(RequestEntity entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(RequestEntity entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(RequestEntity entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


   
   
    
}
  
    

