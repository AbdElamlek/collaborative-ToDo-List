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

    private int id;
    private String title;
    private Date assignDate;
    private Date deadLineDate;
    private int status;
    private int ownerId;
    private String color;
    
    private ArrayList<UserEntity> collaboratorList;
    private ArrayList<ItemEntity> itemsList;

    public ToDoEntity() {
    }

    public ToDoEntity(int id, String title, Date assignDate, Date deadLineDate, int ownerId, int status, String color) {
        this.id = id;
        this.title = title;
        this.assignDate = assignDate;
        this.deadLineDate = deadLineDate;
        this.ownerId = ownerId;
        this.status = status;
        this.color = color;
    }

    public void setId(int id) {
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

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getId() {
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

    public int getOwnerId() {
        return ownerId;
    }
    
    public int getStatus() {
        return status;
    }

    public void setCollaboratorList(ArrayList<UserEntity> collaboratorList) {
        this.collaboratorList = collaboratorList;
    }

    public ArrayList<UserEntity> getCollaboratorList() {
        return collaboratorList;
    }

    public void setItemsList(ArrayList<ItemEntity> itemsList) {
        this.itemsList = itemsList;
    }

    public ArrayList<ItemEntity> getItemsList() {
        return itemsList;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    
}
