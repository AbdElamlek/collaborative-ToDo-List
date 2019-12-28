/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import Entities.BaseEntity;
import Entities.ItemEntity;
import java.util.ArrayList;

/**
 *
 * @author Abd-Elmalek
 */
public interface ItemDAO extends BaseDAO {
    ArrayList<BaseEntity> findAll();
    BaseEntity findById(String id);
    boolean insert(ItemEntity item);
    boolean update(ItemEntity item);
    boolean deleteI(ItemEntity item);
}
