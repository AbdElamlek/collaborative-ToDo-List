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
public class UserEntity implements BaseEntity {
    
    private int id;
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String password;
    private int userStatus;
    private ArrayList<UserEntity> friendList;
    private ArrayList<RequestEntity> friendRequestList;
    private ArrayList<CollaborationRequestEntity> collaborationRequestList;
    private ArrayList<RequestEntity> taskAssignmentRequestList;
    private ArrayList<NotificationEntity> notificationList;
    private ArrayList<TaskEntity> tasksList;
    private ArrayList<ToDoEntity> todoList;
    private ArrayList<ToDoEntity> collaboratorList;

    public UserEntity() {
    }

    public UserEntity(int id, String firstName, String lastName, String userName, String email, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }
    
    public UserEntity(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
    
    public UserEntity(int id, String username) {
        this.id = id;
        this.userName = username;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setUserStatus(int userStatus) {
        this.userStatus = userStatus;
    }

    public void setFriendList(ArrayList<UserEntity> friendList) {
        this.friendList = friendList;
    }

    public int getUserStatus() {
        return userStatus;
    }

    public ArrayList<UserEntity> getFriendList() {
        return friendList;
    }

    public void setNotificationList(ArrayList<NotificationEntity> notificationList) {
        this.notificationList = notificationList;
    }

    public ArrayList<NotificationEntity> getNotificationList() {
        return notificationList;
    }

    public ArrayList<TaskEntity> getTasksList() {
        return tasksList;
    }

    public void setTasksList(ArrayList<TaskEntity> tasksList) {
        this.tasksList = tasksList;
    }

    public ArrayList<ToDoEntity> getTodoList() {
        return todoList;
    }

    public void setTodoList(ArrayList<ToDoEntity> todoList) {
        this.todoList = todoList;
    }

    public ArrayList<RequestEntity> getFriendRequestList() {
        return friendRequestList;
    }

    public void setFriendRequestList(ArrayList<RequestEntity> friendRequestList) {
        this.friendRequestList = friendRequestList;
    }

    public ArrayList<CollaborationRequestEntity> getCollaborationRequestList() {
        return collaborationRequestList;
    }

    public void setCollaborationRequestList(ArrayList<CollaborationRequestEntity> collaborationRequestList) {
        this.collaborationRequestList = collaborationRequestList;
    }

    public ArrayList<RequestEntity> getTaskAssignmentRequestList() {
        return taskAssignmentRequestList;
    }

    public void setTaskAssignmentRequestList(ArrayList<RequestEntity> taskAssignmnetRequest) {
        this.taskAssignmentRequestList = taskAssignmnetRequest;
    }

    public ArrayList<ToDoEntity> getCollaboratorList() {
        return collaboratorList;
    }

    public void setCollaboratorList(ArrayList<ToDoEntity> collaboratorList) {
        this.collaboratorList = collaboratorList;
    }
      
}
