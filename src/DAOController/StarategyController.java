/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOController;

import DAOs.BaseDAO;
import java.util.ArrayList;

/**
 *
 * @author Abd-Elmalek
 */
public class StarategyController<T> implements BaseDAO<T> {

    BaseDAO baseDAO;
    T baseEntity;

    public StarategyController(BaseDAO baseDAO, T baseEntity) {
        this.baseDAO = baseDAO;
        this.baseEntity = baseEntity;
    }

    public boolean update(T entity) {

        return baseDAO.update(baseEntity);
    }

    public boolean insert(T entity) {

        return baseDAO.insert(baseEntity);
    }

    public boolean delete(T entity) {

        return baseDAO.delete(baseEntity);
    }

    public ArrayList<T> findAll() {

        return baseDAO.findAll();

    }

    public T findById(String id) {

        return (T) baseDAO.findById(id);
    }

}
