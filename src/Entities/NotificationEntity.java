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
    private String msg;
    private int receivedUserId;
    private int sentUserId;
    

    public NotificationEntity() {
    }


    public NotificationEntity(int id, Date time, String msg, int receivedUserId, int sentUserId) {
        this.id = id;
        this.time = time;
        this.msg = msg;
        this.receivedUserId = receivedUserId;
        this.sentUserId = sentUserId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public void setMsg(String msg) {
        this.msg= msg;
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
    public String getMsg() {
        return msg;
    }
    public int getReceivedUserId() {
        return receivedUserId;
    }

    public int getSentUserId() {
        return sentUserId;
    }    
    
}
