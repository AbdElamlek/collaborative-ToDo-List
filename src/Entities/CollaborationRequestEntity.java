/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author pc
 */
public class CollaborationRequestEntity extends RequestEntity{
    private int todoId;

    public CollaborationRequestEntity(int todoId, int id, Date time,int receivedUserId, int sentUserId) {
        super(id, time,receivedUserId, sentUserId);
        this.todoId = todoId;
    }
    

    public int getTodoId() {
        return todoId;
    }

    public void setTodoId(int todoId) {
        this.todoId = todoId;
    }
    
}
