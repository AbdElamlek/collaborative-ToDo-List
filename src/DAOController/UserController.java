/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOController;

import Connection.DataBaseConnection;
import DAOs.BaseDAO;
import Entities.BaseEntity;
import Entities.UserEntity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Abd-Elmalek
 */
public class UserController <UserDAO> implements BaseDAO<UserEntity>{
     private Connection con = DataBaseConnection.getInstance();
    @Override
    public ArrayList<UserEntity> findAll() {
        System.out.println("user");
        return new ArrayList<>();
    }

    @Override
    public UserEntity findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean insert(UserEntity entity) {
        /* EMAN KAMAL */
        int rows_affected = 0;
        try {
            PreparedStatement pst = con.prepareStatement("insert into user values (?,?,?,?,?)");
            pst.setString(1, entity.getFirstName());
            pst.setString(2, entity.getLastName());
            pst.setString(3, entity.getUserName());
            pst.setString(4, entity.getEmail());
            pst.setString(5, entity.getPassword());
            rows_affected = pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TaskController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (rows_affected > 0) {
            return true;
        } else {
            return false;
        }
      /* EMAN KAMAL */
    }

    @Override
    public boolean update(UserEntity entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(UserEntity entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<UserEntity> findAllListCollaborators(int todoId){
        ArrayList<UserEntity> collaborators = new ArrayList<UserEntity>();
        try{
            String query = "SELECT id, firstName, lastName, username, email, password\n" +
                           "FROM [todoDB].[dbo].[user] As u, [todoDB].[dbo].[user_collaborate_todo] AS uct\n" +
                           "WHERE u.id = uct.collaboratorUserId AND uct.todoId = ?;";
                    
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, todoId);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next())
                collaborators.add(new UserEntity(resultSet.getInt("id"), resultSet.getString("firstName"), resultSet.getString("lastName"), resultSet.getString("username"), resultSet.getString("email"), resultSet.getString("password")));
            
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return collaborators;
    }
   
    public ArrayList<UserEntity> findAllUserFriends(int userId){
         ArrayList<UserEntity> friends = new ArrayList<UserEntity>();
         
         try{
             String query = "SELECT u.id, u.firstName, u.lastName, u.username, u.email, u.password\n" +
                            "FROM [todoDB].[dbo].[user] AS u, [todoDB].[dbo].[user_friend] As uf\n" +
                            "WHERE u.id = uf.friendId AND uf.userId = ?;";
             
             PreparedStatement preparedStatement = con.prepareStatement(query);
             preparedStatement.setInt(1, userId);
             
             ResultSet resultSet = preparedStatement.executeQuery();
             while(resultSet.next())
                 friends.add(new UserEntity(resultSet.getInt("id"), resultSet.getString("firstName"), resultSet.getString("lastName"), resultSet.getString("username"), resultSet.getString("email"), resultSet.getString("password")));
         }catch(SQLException ex){
             ex.printStackTrace();
         }
         return friends;
    }
}