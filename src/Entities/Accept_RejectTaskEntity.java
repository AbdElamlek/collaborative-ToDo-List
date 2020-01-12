/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author Abd-Elmalek
 */
public class Accept_RejectTaskEntity extends RequestEntity{
    
    int taskId;

    public Accept_RejectTaskEntity() {
        
    }

    public Accept_RejectTaskEntity(int id, Date time, int receivedUserId, int sentUserId, int taskId) {
        super(id, time, receivedUserId, sentUserId);
        this.taskId = taskId;
    }
    

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }
    
    
}
