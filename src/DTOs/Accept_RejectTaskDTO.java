/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOs;

import Entities.BaseEntity;



/**
 *
 * @author Abd-Elmalek
 */
public class Accept_RejectTaskDTO implements BaseEntity {
    int userId;
    int TaskId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTaskId() {
        return TaskId;
    }

    public void setTaskId(int TaskId) {
        this.TaskId = TaskId;
    }
    
}
