/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author pc
 */
public class CollaboratorDTO implements BaseEntity{
    private int collaboratorID;
    private int senderID;
    private int todoId;
    private int reqId;

    public int getReqId() {
        return reqId;
    }

    public void setReqId(int reqId) {
        this.reqId = reqId;
    }

    
    public CollaboratorDTO(int reqId) {
        this.reqId = reqId;
    }

    public CollaboratorDTO(int senderID, int todoId) {
        this.senderID = senderID;
        this.todoId = todoId;
    }

    
    public CollaboratorDTO(int collaboratorID, int senderID, int todoId) {
        this.collaboratorID = collaboratorID;
        this.senderID = senderID;
        this.todoId = todoId;
    }

    public int getCollaboratorID() {
        return collaboratorID;
    }

    public void setCollaboratorID(int collaboratorID) {
        this.collaboratorID = collaboratorID;
    }

    public int getSenderID() {
        return senderID;
    }

    public void setSenderID(int senderID) {
        this.senderID = senderID;
    }

    public int getTodoId() {
        return todoId;
    }

    public void setTodoId(int todoId) {
        this.todoId = todoId;
    }

  
    
}
