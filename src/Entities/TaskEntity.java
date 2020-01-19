/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.ArrayList;

/**
 *
 * @author Abd-Elmalek
 */
public class TaskEntity implements BaseEntity {
    
    private int id;
    private String decription;
    private int status;
    private int itemId;
    private ArrayList<CommentEntity> commentsList;
    private ArrayList<UserEntity> assignedUsersList;

    public TaskEntity() {
    }

    public TaskEntity(int id, String decription, int status, int itemId) {
        this.id = id;
        this.decription = decription;
        this.status = status;
        this.itemId = itemId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getId() {
        return id;
    }

    public String getDecription() {
        return decription;
    }

    public int getStatus() {
        return status;
    }

    public int getItemId() {
        return itemId;
    }

    public void setCommentsList(ArrayList<CommentEntity> commentsList) {
        this.commentsList = commentsList;
    }

    public ArrayList<CommentEntity> getCommentsList() {
        return commentsList;
    }

    public ArrayList<UserEntity> getAssignedUsersList() {
        return assignedUsersList;
    }

    public void setAssignedUsersList(ArrayList<UserEntity> assignedUsersList) {
        this.assignedUsersList = assignedUsersList;
    }
    
    
}
