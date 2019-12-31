/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import java.util.ArrayList;

/**
 *
 * @author Abd-Elmalek
 */
public interface BaseDAO<T> {
    
    ArrayList<T> findAll();
    T findById(int id);
    boolean insert(T entity);
    boolean update(T entity);
    boolean delete(T entity);
    
}
