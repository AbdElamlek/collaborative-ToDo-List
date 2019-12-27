/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import Entities.UserEntity;
import java.util.ArrayList;

/**
 *
 * @author Abd-Elmalek
 */
public interface UserDAO {
    
    ArrayList<UserEntity> findAllUsers();
    ArrayList<UserEntity> findUserById();
    ArrayList<UserEntity> findByName();
    boolean insertUser(UserEntity user);
    boolean updateUser(UserEntity user);
    boolean deleteUser(UserEntity user);
    
}
