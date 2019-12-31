/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.ArrayList;
//import java.util.Date;
import java.sql.Date;

/**
 *
 * @author Abd-Elmalek
 */
public class ToDoEntity implements BaseEntity {
    
    private String id;
    private String title;
    private Date assignDate;
    private Date deadLineDate;
    private String ownerId;
    private ArrayList<UserEntity> clllaboratorList;
    private ArrayList<ItemEntity> itemsList;

    public ToDoEntity() {
    }

    public ToDoEntity(String id, String title, Date assignDate, Date deadLineDate, String ownerId) {
        this.id = id;
        this.title = title;
        this.assignDate = assignDate;
        this.deadLineDate = deadLineDate;
        this.ownerId = ownerId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAssignDate(Date assignDate) {
        this.assignDate = assignDate;
    }

    public void setDeadLineDate(Date deadLineDate) {
        this.deadLineDate = deadLineDate;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Date getAssignDate() {
        return assignDate;
    }

    public Date getDeadLineDate() {
        return deadLineDate;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setClllaboratorList(ArrayList<UserEntity> clllaboratorList) {
        this.clllaboratorList = clllaboratorList;
    }

    public ArrayList<UserEntity> getClllaboratorList() {
        return clllaboratorList;
    }

    public void setItemsList(ArrayList<ItemEntity> itemsList) {
        this.itemsList = itemsList;
    }

    public ArrayList<ItemEntity> getItemsList() {
        return itemsList;
    }
    
    
    
    
    
}
