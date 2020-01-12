/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOs;

import Entities.BaseEntity;
import Entities.TaskEntity;



/**
 *
 * @author Abd-Elmalek
 */
public class Accept_RejectTaskDTO implements BaseEntity {
    int userId;
    TaskEntity task;
    int todoId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public TaskEntity getTask() {
        return task;
    }

    public int getTodoId() {
        return todoId;
    }

    public void setTask(TaskEntity task) {
        this.task = task;
    }

    public void setTodoId(int todoId) {
        this.todoId = todoId;
    }

    
    
    
}
