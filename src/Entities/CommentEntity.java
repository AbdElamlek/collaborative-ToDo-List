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
public class CommentEntity {
    
    private String id;
    private String messageContent;
    private Date time;

    public CommentEntity() {
    }

    public CommentEntity(String id, String messageContent, Date time) {
        this.id = id;
        this.messageContent = messageContent;
        this.time = time;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public Date getTime() {
        return time;
    }
    
    
}
