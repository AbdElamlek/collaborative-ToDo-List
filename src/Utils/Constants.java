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
      public static final String commetTable = "commentTable";
      public static final String itemTable = "itemTable";
      public static final String notificationTable = "notificationTable";
      public static final String requestTable = "requestTable";
      public static final String taskTable = "taskTable";
      public static final String toDoTable = "toDoTable";
      public static final String userTable = "[user]";
      public static final String user_assignedto_task_Table = "user_assignedto_task_Table";
      public static final String user_collaborate_todo_Table = "user_collaborate_todo_Table";
      public static final String user_do_comment_task_Table = "user_do_comment_task_Table";
    }
    
}
