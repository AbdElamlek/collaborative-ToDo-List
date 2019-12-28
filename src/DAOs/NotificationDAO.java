/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import Entities.BaseEntity;
import Entities.NotificationEntity;
import java.util.ArrayList;

/**
 *
 * @author Abd-Elmalek
 */
public interface NotificationDAO extends BaseDAO {
    ArrayList<BaseEntity> findAll();
    BaseEntity findById(String id);
    boolean insert(NotificationEntity notification);
    boolean update(NotificationEntity notification);
    boolean delete(NotificationEntity notification);
    
}
