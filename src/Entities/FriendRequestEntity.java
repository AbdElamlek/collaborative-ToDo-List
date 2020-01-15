/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author ahmedpro
 */
public class FriendRequestEntity extends RequestEntity {
    
    private String friendUserName;
    
    public FriendRequestEntity() {
        
    }
    
    public FriendRequestEntity(int id, Date time, 
            int receivedUserId, int sentUserId, String friendUserName) {
        
        super(id, time, receivedUserId, sentUserId);
        this.friendUserName = friendUserName;
    }
    
    public String getFriendUserName() {
        return friendUserName;
    }
    
    public void setFriendUserName(String friendUserName) {
        this.friendUserName = friendUserName;
    }
    
}
