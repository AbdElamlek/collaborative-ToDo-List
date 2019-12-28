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
    
    private String id;
    private String decription;
    private int status;
    private String itemId;
    private ArrayList<CommentEntity> commentsList;

    public TaskEntity() {
    }

    public TaskEntity(String id, String decription, int status, String itemId) {
        this.id = id;
        this.decription = decription;
        this.status = status;
        this.itemId = itemId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getId() {
        return id;
    }

    public String getDecription() {
        return decription;
    }

    public int getStatus() {
        return status;
    }

    public String getItemId() {
        return itemId;
    }

    public void setCommentsList(ArrayList<CommentEntity> commentsList) {
        this.commentsList = commentsList;
    }

    public ArrayList<CommentEntity> getCommentsList() {
        return commentsList;
    }
    
    
}
