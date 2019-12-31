/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOController;

import Connection.DataBaseConnection;
import DAOs.BaseDAO;
import Entities.BaseEntity;
import Entities.UserEntity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Abd-Elmalek
 */
public class UserController <UserDAO> implements BaseDAO<UserEntity>{
     private Connection con = DataBaseConnection.getInstance();
    @Override
    public ArrayList<UserEntity> findAll() {
        System.out.println("user");
        return new ArrayList<>();
    }

    @Override
    public UserEntity findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean insert(UserEntity entity) {
        /* EMAN KAMAL */
        int rows_affected = 0;
        try {
            PreparedStatement pst = con.prepareStatement("insert into user values (?,?,?,?,?)");
            pst.setString(1, entity.getFirstName());
            pst.setString(2, entity.getLastName());
            pst.setString(3, entity.getUserName());
            pst.setString(4, entity.getEmail());
            pst.setString(5, entity.getPassword());
            rows_affected = pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TaskController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (rows_affected > 0) {
            return true;
        } else {
            return false;
        }
      /* EMAN KAMAL */
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