/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import Entities.TaskEntity;
import java.util.ArrayList;

/**
 *
 * @author Abd-Elmalek
 */
public interface TaskDAO {
    ArrayList<TaskEntity> findAllITasks();
    ArrayList<TaskEntity> findTaskById();
    boolean insertTask(TaskEntity task);
    boolean updateTask(TaskEntity task);
    boolean deleteTask(TaskEntity task);
}
