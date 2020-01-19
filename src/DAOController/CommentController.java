/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOController;

import Connection.DataBaseConnection;
import DAOs.BaseDAO;
import Entities.CommentEntity;
import Entities.UserEntity;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Abd-Elmalek
 */
public class CommentController<CommentDAO> implements BaseDAO<CommentEntity>{
    Connection connection = DataBaseConnection.getInstance();
    PreparedStatement preparedStatement;
    
    @Override
    public ArrayList<CommentEntity> findAll() {
        ArrayList<CommentEntity> comments = new ArrayList<CommentEntity>();
        try {
            String query = "SELECT * FROM [todoDB].[dbo].[comment]";
            preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()){
                comments.add(new CommentEntity(resultSet.getInt("id"), resultSet.getString("messageContent"),
                        resultSet.getDate("time"),resultSet.getInt("commentOwnerId"),resultSet.getInt("commentedTaskId"),
                resultSet.getInt("commentTodoId") , resultSet.getInt("commentItemId"),resultSet.getString("commentUserName")));
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return comments;
    }

    @Override
    public CommentEntity findById(int id) {
        try {
            String query = "SELECT * FROM [todoDB].[dbo].[comment] WHERE id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if(resultSet.next())
                return new CommentEntity(resultSet.getInt("id"), resultSet.getString("messageContent"),
                        resultSet.getDate("time"),resultSet.getInt("commentOwnerId"),resultSet.getInt("commentedTaskId"),
                resultSet.getInt("commentTodoId") , resultSet.getInt("commentItemId"),resultSet.getString("commentUserName"));
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        } 
       return null;
    }

    @Override
    public boolean insert(CommentEntity entity) {
        try {
            String query = "INSERT INTO [todoDB].[dbo].[comment] (messageContent, time, commentOwnerId, commentedTaskId, commentTodoId, commentItemId, commentUserName) VALUES (?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            
            System.out.println(entity.getItemId()+"  item id");
            System.out.println(entity.getTodoId()+"  todo id");
            preparedStatement.setString(1, entity.getMessageContent());
            preparedStatement.setDate(2, (Date) entity.getTime());
            preparedStatement.setInt(3, entity.getCommentOwnerId());
            preparedStatement.setInt(4, entity.getCommentedTaskId());
            preparedStatement.setInt(5, entity.getTodoId());
            preparedStatement.setInt(6, entity.getItemId());
            preparedStatement.setString(7, entity.getUserName());
            int effectedRows = preparedStatement.executeUpdate();
           
            if(effectedRows>0){
                 ResultSet rs = preparedStatement.getGeneratedKeys();
            
               
                
                if (rs.next()){
                
                entity.setId(rs.getInt(1));
                
                query = "INSERT INTO [todoDB].[dbo].[user_do_comment_task] VALUES (?, ?, ?)";
                preparedStatement = connection.prepareStatement(query);
                
                preparedStatement.setInt(1, entity.getCommentOwnerId());
                preparedStatement.setInt(2, entity.getCommentedTaskId());
                preparedStatement.setInt(3, rs.getInt(1));
                preparedStatement.executeUpdate();
                   
                return true;
            }
            }
            
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        } 
        return false;
    }

    @Override
    public boolean update(CommentEntity entity) {
        try {
            String query = "UPDATE [todoDB].[dbo].[comment] SET messageContent = ?, time = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, entity.getMessageContent());
            preparedStatement.setDate(2, (Date) entity.getTime());
            preparedStatement.setInt(3, entity.getCommentOwnerId());
            preparedStatement.setInt(4, entity.getCommentedTaskId());
            preparedStatement.setInt(5, entity.getTodoId());
            preparedStatement.setInt(6, entity.getItemId());
            preparedStatement.setString(7, entity.getUserName());
            int effectRows = preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            entity.setId(rs.getInt("id"));
            return (effectRows > 0);          
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return false;
    }

    @Override
    public boolean delete(CommentEntity entity) {
        try {
            String query = "DELETE FROM [todoDB].[dbo].[comment] WHERE id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, entity.getId());
            
            return (preparedStatement.executeUpdate() > 0);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }  
    
    public ArrayList<CommentEntity> findByTaskId(int taskId){
        ArrayList<CommentEntity> comments = new ArrayList<CommentEntity>();
        UserEntity commentOwnerTemp;
        try{
            String query = "SELECT userId, firstName, lastName, username, email, password, commentId, messageContent, time ,commentOwnerId,commentedTaskId,commentTodoId,commentItemId,commentUserName\n" +
                           "FROM [todoDB].[dbo].[user_do_comment_task] AS udct, [todoDB].[dbo].[comment] AS c, [todoDB].[dbo].[user] AS u\n" +
                           "WHERE u.id = udct.userId AND c.id = udct.commentId AND udct.taskId = ?;";
            
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, taskId);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()){
              //  commentOwnerTemp = new UserEntity(resultSet.getInt("userId"), resultSet.getString("firstName"),
                //                   resultSet.getString("lastName"), resultSet.getString("username"),
                  //                 resultSet.getString("email"), resultSet.getString("password"));
                
                comments.add(new CommentEntity(resultSet.getInt(7), resultSet.getString("messageContent"),
                        resultSet.getDate("time"),resultSet.getInt("commentOwnerId"),resultSet.getInt("commentedTaskId"),
                resultSet.getInt("commentTodoId") , resultSet.getInt("commentItemId"),resultSet.getString("commentUserName")));
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return comments;
    }
}
