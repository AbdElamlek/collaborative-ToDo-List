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
    String message;

    public CollaborationRequestEntity(int todoId, String message,int id, Date time, int receivedUserId, int sentUserId) {
        super(id, time, receivedUserId, sentUserId);
        this.todoId = todoId;
        this.message=message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    

    public int getTodoId() {
        return todoId;
    }

    public void setTodoId(int todoId) {
        this.todoId = todoId;
    }
    
}
