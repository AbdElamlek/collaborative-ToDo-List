/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;

/**
 *
 * @author Abd-Elmalek
 */
public class CommentEntity implements BaseEntity {
    
    private int id;
    private String messageContent;
    private Date time;
    private UserEntity commentOwner;
    private int commentedTaskId;

    public CommentEntity() {
    }

    public CommentEntity(int id, String messageContent, Date time, UserEntity commentOwner, int commentedTaskId) {
        this.id = id;
        this.messageContent = messageContent;
        this.time = time;
        this.commentOwner = commentOwner;
        this.commentedTaskId = commentedTaskId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public void setCommentOwner(UserEntity commentOwner) {
        this.commentOwner = commentOwner;
    }
    
    public int getId() {
        return id;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public Date getTime() {
        return time;
    }
    
    public UserEntity getCommentOwner() {
        return commentOwner;
    }

    public int getCommentedTaskId() {
        return commentedTaskId;
    }

    public void setCommentedTaskId(int commentedTaskId) {
        this.commentedTaskId = commentedTaskId;
    }

}
