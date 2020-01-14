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
public class NotificationEntity implements BaseEntity {
    
    private int id;
    private Date time;
    private int receivedUserId;
    private int sentUserId;
    private String message;

    public NotificationEntity() {
    }

    public NotificationEntity(int id, Date time, int receivedUserId, int sentUserId, String message) {
        this.id = id;
        this.time = time;
        this.receivedUserId = receivedUserId;
        this.sentUserId = sentUserId;
        this.message = message;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTime(Date time) {
        this.time = time;
    }



    public void setReceivedUserId(int receivedUserId) {
        this.receivedUserId = receivedUserId;
    }

    public void setSentUserId(int sentUserId) {
        this.sentUserId = sentUserId;
    }

    public int getId() {
        return id;
    }

    public Date getTime() {
        return time;
    }



    public int getReceivedUserId() {
        return receivedUserId;
    }

    public int getSentUserId() {
        return sentUserId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
    
}
