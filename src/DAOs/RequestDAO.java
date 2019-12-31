/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import Entities.BaseEntity;
import Entities.RequestEntity;
import java.util.ArrayList;

/**
 *
 * @author Abd-Elmalek
 */
public interface RequestDAO extends BaseDAO {
    ArrayList<BaseEntity> findAll();
    ArrayList<RequestEntity> findtById(int id);
    boolean insert(RequestEntity request);
    boolean update(RequestEntity request);
    boolean delete(RequestEntity request);
}
