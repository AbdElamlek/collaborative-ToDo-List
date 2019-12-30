/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import Entities.BaseEntity;
import Entities.UserEntity;
import java.util.ArrayList;

/**
 *
 * @author Abd-Elmalek
 */
public interface UserDAO extends BaseDAO {
    
    ArrayList<BaseEntity> findAll();
    BaseEntity findById(int id);
    ArrayList<UserEntity> findByName();
    boolean insert(UserEntity user);
    boolean update(UserEntity user);
    boolean delete(UserEntity user);
    
}
