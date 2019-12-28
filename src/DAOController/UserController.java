/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOController;

import DAOs.BaseDAO;
import Entities.BaseEntity;
import Entities.UserEntity;
import java.util.ArrayList;

/**
 *
 * @author Abd-Elmalek
 */
public class UserController <UserDAO> implements BaseDAO<UserEntity>{

    @Override
    public ArrayList<UserEntity> findAll() {
        System.out.println("user");
        return new ArrayList<>();
    }

    @Override
    public UserEntity findById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean insert(UserEntity entity) {
       System.out.println("user");
        return true;
    }

    @Override
    public boolean update(UserEntity entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(UserEntity entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
   
   
    
}