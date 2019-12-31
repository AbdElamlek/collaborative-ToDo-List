/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

/**
 *
 * @author Abd-Elmalek
 */
public class Constants {
    
    public static final String DBUserName = "todolistUser";
    public static final String DBPassword = "root";
    public static final String DBUrl = "jdbc:sqlserver://localhost:1433;databaseName=todoDB;user="+DBUserName+";password="+DBPassword;
    public static final String schemaName ="[todoDB].[dbo]";    
    
    public class TableNames{
      public static final String commetTable = "comment";
      public static final String itemTable = "item";
      public static final String notificationTable = "notification";
      public static final String requestTable = "request";
      public static final String taskTable = "task";
      public static final String toDoTable = "todo";
      public static final String userTable = "[user]";
      public static final String user_assigned_task_Table = "user_assigned_task";
      public static final String user_collaborate_todo_Table = "user_collaborate_todo";
      public static final String user_do_comment_task_Table = "user_do_comment_task";
      public static final String user_friend_Table = "user_friend";
    }
    
}
