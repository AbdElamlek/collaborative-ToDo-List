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
public class CommentEntity implements BaseEntity {
    
    private int id;
    private String messageContent;
    private Date time;
    private int commentOwnerId;
    private int commentedTaskId;
    private int commentTodoId;
    private int commentItemId;
    private String commentUserName;

    public CommentEntity() {
    }

    public CommentEntity(int id, String messageContent, Date time, int commentOwnerId, int commentedTaskId, int todoId, int itemId, String userName) {
        this.id = id;
        this.messageContent = messageContent;
        this.time = time;
        this.commentOwnerId = commentOwnerId;
        this.commentedTaskId = commentedTaskId;
        this.commentTodoId = todoId;
        this.commentItemId = itemId;
        this.commentUserName = userName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getCommentOwnerId() {
        return commentOwnerId;
    }

    public void setCommentOwnerId(int commentOwnerId) {
        this.commentOwnerId = commentOwnerId;
    }

    public int getCommentedTaskId() {
        return commentedTaskId;
    }

    public void setCommentedTaskId(int commentedTaskId) {
        this.commentedTaskId = commentedTaskId;
    }

    public int getTodoId() {
        return commentTodoId;
    }

    public void setTodoId(int todoId) {
        this.commentTodoId = todoId;
    }

    public int getItemId() {
        return commentItemId;
    }

    public void setItemId(int itemId) {
        this.commentItemId = itemId;
    }

    public String getUserName() {
        return commentUserName;
    }

    public void setUserName(String userName) {
        this.commentUserName = userName;
    }



}
