/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.ArrayList;

/**
 *
 * @author Abd-Elmalek
 */
public class ItemEntity {
    
    private String id;
    private String title;
    private String decription;
    private String todoId;
    private ArrayList<TaskEntity> tasksList;

    public ItemEntity() {
    }

    public ItemEntity(String id, String title, String decription, String todoId) {
        this.id = id;
        this.title = title;
        this.decription = decription;
        this.todoId = todoId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public void setTodoId(String todoId) {
        this.todoId = todoId;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDecription() {
        return decription;
    }

    public String getTodoId() {
        return todoId;
    }

    public void setTasksList(ArrayList<TaskEntity> tasksList) {
        this.tasksList = tasksList;
    }

    public ArrayList<TaskEntity> getTasksList() {
        return tasksList;
    }
    
    
    
}
