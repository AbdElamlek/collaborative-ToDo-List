/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOController;

import Connection.DataBaseConnection;
import DAOs.BaseDAO;
import Entities.CommentEntity;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                comments.add(new CommentEntity(resultSet.getInt("id"), resultSet.getString("messageContent"), resultSet.getDate("time")));
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
                return new CommentEntity(resultSet.getInt("id"), resultSet.getString("messageContent"), resultSet.getDate("time"));
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        } 
       return null;
    }

    @Override
    public boolean insert(CommentEntity entity) {
        try {
            String query = "INSERT INTO [todoDB].[dbo].[comment] (messageContent, time) VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(query);
            
            preparedStatement.setString(1, entity.getMessageContent());
            preparedStatement.setDate(2, (Date) entity.getTime());
            
            return (preparedStatement.executeUpdate() > 0);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } 
        return false;
    }

    @Override
    public boolean update(CommentEntity entity) {
        try {
            String query = "UPDATE [todoDB].[dbo].[comment] SET messageContent = ?, time = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, entity.getMessageContent());
            preparedStatement.setDate(2, (Date) entity.getTime());
            preparedStatement.setInt(3, entity.getId());
            
            return (preparedStatement.executeUpdate() > 0);          
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
}
