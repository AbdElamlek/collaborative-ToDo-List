/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOController;

import DAOs.BaseDAO;
import Entities.BaseEntity;
import Entities.TaskEntity;
import Entities.ToDoEntity;
import java.util.ArrayList;

/**
 *
 * @author Abd-Elmalek
 */
public class ToDoController <ToDoDAO> implements BaseDAO<ToDoEntity> {

    @Override
    public ArrayList<ToDoEntity> findAll() {
       System.out.println("todo");
        return new ArrayList<ToDoEntity>();
    }

    @Override
    public ToDoEntity findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean insert(ToDoEntity entity) {
        System.out.println("todo");
        return true;
    }

    @Override
    public boolean update(ToDoEntity entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(ToDoEntity entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

}
