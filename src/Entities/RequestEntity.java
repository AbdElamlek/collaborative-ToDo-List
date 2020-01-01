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
public class RequestEntity implements BaseEntity {
    
    private int id;
    private Date time;
    private String type;
    private int receivedUserId;
    private int sentUserId;

    public RequestEntity() {
    }

    public RequestEntity(int id, Date time, String type, int receivedUserId, int sentUserId) {
        this.id = id;
        this.time = time;
        this.type = type;
        this.receivedUserId = receivedUserId;
        this.sentUserId = sentUserId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getType() {
        return type;
    }

    public int getReceivedUserId() {
        return receivedUserId;
    }

    public int getSentUserId() {
        return sentUserId;
    }
    
}
