/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import Entities.ToDoEntity;
import java.util.ArrayList;

/**
 *
 * @author Abd-Elmalek
 */
public interface ToDoDAO {
    ArrayList<ToDoEntity> findAllIToDos();
    ArrayList<ToDoEntity> findToDoById();
    boolean insertToDo(ToDoEntity todo);
    boolean updateToDo(ToDoEntity todo);
    boolean deleteToDo(ToDoEntity todo);
}
