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
<<<<<<< HEAD
    private String msg;
=======
>>>>>>> a72eb6ac249bf04fce5d2e7e8149569ee0944a33
    private int receivedUserId;
    private int sentUserId;
    private String message;

    public NotificationEntity() {
    }

<<<<<<< HEAD
    public NotificationEntity(int id, Date time, String msg, int receivedUserId, int sentUserId) {
        this.id = id;
        this.time = time;
        this.msg = msg;
=======
    public NotificationEntity(int id, Date time, int receivedUserId, int sentUserId, String message) {
        this.id = id;
        this.time = time;
>>>>>>> a72eb6ac249bf04fce5d2e7e8149569ee0944a33
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

<<<<<<< HEAD
    public void setMsg(String msg) {
        this.msg= msg;
    }
=======

>>>>>>> a72eb6ac249bf04fce5d2e7e8149569ee0944a33

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

<<<<<<< HEAD
    public String getMsg() {
        return msg;
    }
=======

>>>>>>> a72eb6ac249bf04fce5d2e7e8149569ee0944a33

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
