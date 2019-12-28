/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import Entities.BaseEntity;
import Entities.TaskEntity;
import java.util.ArrayList;

/**
 *
 * @author Abd-Elmalek
 */
public interface TaskDAO extends BaseDAO {
    ArrayList<BaseEntity> findAll();
    BaseEntity findById(String id);
    boolean insert(TaskEntity task);
    boolean update(TaskEntity task);
    boolean delete(TaskEntity task);
}
