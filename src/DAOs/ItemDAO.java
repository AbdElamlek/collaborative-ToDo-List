/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import Entities.ItemEntity;
import java.util.ArrayList;

/**
 *
 * @author Abd-Elmalek
 */
public interface ItemDAO {
    ArrayList<ItemEntity> findAllItems();
    ArrayList<ItemEntity> findtemById();
    boolean insertItem(ItemEntity item);
    boolean updateItem(ItemEntity item);
    boolean deleteItem(ItemEntity item);
}
