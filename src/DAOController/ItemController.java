/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOController;

import Connection.DataBaseConnection;
import DAOs.BaseDAO;
import Entities.ItemEntity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Abd-Elmalek
 */
public class ItemController<ItemDAO> implements BaseDAO<ItemEntity>{
    Connection connection = DataBaseConnection.getInstance();
    PreparedStatement preparedStatement;
    
    @Override
    public ArrayList<ItemEntity> findAll() {
        ArrayList<ItemEntity> items = new ArrayList<ItemEntity>();
        try {
            String query = "SELECT * FROM [todoDB].[dbo].[item]";
            preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()){
                items.add(new ItemEntity(resultSet.getInt("id"), resultSet.getString("title"), resultSet.getString("description"), resultSet.getInt("todoId")));
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return items;
    }

    @Override
    public ItemEntity findById(int id) {
        try {
            String query = "SELECT * FROM [todoDB].[dbo].[item] WHERE id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if(resultSet.next())
                return new ItemEntity(resultSet.getInt("id"), resultSet.getString("title"), resultSet.getString("description"), resultSet.getInt("todoId"));
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        } 
       return null;
    }

    @Override
    public boolean insert(ItemEntity entity) {
        try {
            String query = "INSERT INTO [todoDB].[dbo].[item] (title, description, todoId) VALUES (?, ?, ?)";
            preparedStatement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            
            preparedStatement.setString(1, entity.getTitle());
            preparedStatement.setString(2, entity.getDecription());
            preparedStatement.setInt(3, entity.getTodoId());
            
            int effectedRows = preparedStatement.executeUpdate();
            
            if(effectedRows>0){
                 ResultSet rs2;
            
                rs2 = preparedStatement.getGeneratedKeys();
                if(rs2.next()){
                    int itemId = rs2.getInt(1);
                    entity.setId(itemId);
                }
                return true;
            }
           
                
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        } 
        return false;
    }

    @Override
    public boolean update(ItemEntity entity) {
        try {
            String query = "UPDATE [todoDB].[dbo].[item] SET title = ?, description = ?, todoId = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(query);
            
            preparedStatement.setString(1, entity.getTitle());
            preparedStatement.setString(2, entity.getDecription());
            preparedStatement.setInt(3, entity.getTodoId());
            preparedStatement.setInt(4, entity.getId());
            
            return (preparedStatement.executeUpdate() > 0);          
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return false;
    }

    @Override
    public boolean delete(ItemEntity entity) {
        try {
            String query = "DELETE FROM [todoDB].[dbo].[item] WHERE id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, entity.getId());
            
            return (preparedStatement.executeUpdate() > 0);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public ArrayList<ItemEntity> findByTodoId(int todoId){
        ArrayList<ItemEntity> items = new ArrayList<ItemEntity>();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM [todoDB].[dbo].[item] WHERE todoId = ?");
            preparedStatement.setInt(1, todoId);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
                items.add(new ItemEntity(resultSet.getInt("id"), resultSet.getString("title"), resultSet.getString("description"), todoId));
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return items;
    }
   
   
    
}
