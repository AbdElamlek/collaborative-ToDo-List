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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Abd-Elmalek
 */
public class UserController<UserDAO> implements BaseDAO<UserEntity> {

    private Connection con = DataBaseConnection.getInstance();
    private UserEntity userEntity;

    /* Eman Kamal*/
    @Override
    public ArrayList<UserEntity> findAll() {
        int id = 0;
        String firstname = "";
        String lastname = "";
        String username = "";
        String email = "";
        String password = "";
        ArrayList<UserEntity> user_list = new ArrayList<UserEntity>();
        try {
            PreparedStatement pst = con.prepareStatement("SELECT * FROM [todoDB].[dbo].[user] ");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
                firstname = rs.getString(2);
                lastname = rs.getString(3);
                username = rs.getString(4);
                email = rs.getString(5);
                password = rs.getString(6);
                user_list.add(new UserEntity(id, firstname, lastname, username, email, password));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user_list;
    }

    @Override
    public UserEntity findById(int id) {
        String firstname = "";
        String lastname = "";
        String username = "";
        String email = "";
        String password = "";

        try {
            PreparedStatement pst = con.prepareStatement("SELECT * FROM [todoDB].[dbo].[user] WHERE id = ?");
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
                firstname = rs.getString(2);
                lastname = rs.getString(3);
                username = rs.getString(4);
                email = rs.getString(5);
                password = rs.getString(6);
                userEntity = new UserEntity(id, firstname, lastname, username, email, password);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userEntity;
    }

    @Override
    public boolean insert(UserEntity entity) {
        int rows_affected = 0;
        PreparedStatement pst = null;
        try {
            PreparedStatement pst_select = con.prepareStatement("SELECT * FROM [todoDB].[dbo].[user] WHERE username = ? ");
            pst_select.setString(1, entity.getUserName());
            ResultSet rs = pst_select.executeQuery();
            int counter = 0;
            while (rs.next()) {
                counter++;
            }
            if (counter > 0) {
                System.out.println("No Duplicates are allowed !!");
            } else {
                pst = con.prepareStatement("INSERT INTO [todoDB].[dbo].[user] (firstName, lastName,username,email,password) VALUES (?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
                pst.setString(1, entity.getFirstName());
                pst.setString(2, entity.getLastName());
                pst.setString(3, entity.getUserName());
                pst.setString(4, entity.getEmail());
                pst.setString(5, entity.getPassword());
                rows_affected = pst.executeUpdate();
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(TaskController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (rows_affected > 0) {
            try {
                ResultSet rs2 = pst.getGeneratedKeys();
                if(rs2.next()){
                    int userId=rs2.getInt(1);
                    entity.setId(userId);
                    System.out.println(userId);
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean update(UserEntity entity) {
        int rows_affected = 0;
        try {
            PreparedStatement pst
                    = con.prepareStatement("UPDATE [todoDB].[dbo].[user] SET firstName = ?, lastName = ?, username = ?,email=?,password=? WHERE id = ?");
            pst.setString(1, entity.getFirstName());
            pst.setString(2, entity.getLastName());
            pst.setString(3, entity.getUserName());
            pst.setString(4, entity.getEmail());
            pst.setString(5, entity.getPassword());
            pst.setInt(6, entity.getId());
            rows_affected = pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        if (rows_affected > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean delete(UserEntity entity) {
        int rows_affected = 0;
        try {
            PreparedStatement pst = con.prepareStatement("DELETE FROM [todoDB].[dbo].[user] WHERE id = ?");
            pst.setInt(1, entity.getId());
            rows_affected = pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (rows_affected > 0) {
            return true;
        } else {
            return false;
        }
    }
    /* EMAN KAMAL */
    
    public ArrayList<UserEntity> findAllListCollaborators(int todoId) {
        ArrayList<UserEntity> collaborators = new ArrayList<UserEntity>();
        try {
            String query = "SELECT id, firstName, lastName, username, email, password\n"
                    + "FROM [todoDB].[dbo].[user] As u, [todoDB].[dbo].[user_collaborate_todo] AS uct\n"
                    + "WHERE u.id = uct.collaboratorUserId AND uct.todoId = ?;";

            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, todoId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                collaborators.add(new UserEntity(resultSet.getInt("id"), resultSet.getString("firstName"), resultSet.getString("lastName"), resultSet.getString("username"), resultSet.getString("email"), resultSet.getString("password")));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return collaborators;
    }

    public ArrayList<UserEntity> findAllUserFriends(int userId) {
        ArrayList<UserEntity> friends = new ArrayList<UserEntity>();

        try {
            String query = "SELECT u.id, u.firstName, u.lastName, u.username, u.email, u.password\n" +
                            "FROM [todoDB].[dbo].[user] AS u, [todoDB].[dbo].[user_friend] As uf\n" +
                            "WHERE u.id = uf.friendId AND uf.userId = ? \n" +
                            "UNION\n" +
                            "SELECT u.id, u.firstName, u.lastName, u.username, u.email, u.password\n" +
                            "FROM [todoDB].[dbo].[user] AS u, [todoDB].[dbo].[user_friend] As uf\n" +
                            "WHERE u.id = uf.userId AND uf.friendId = ?";

            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, userId);


            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                friends.add(new UserEntity(resultSet.getInt("id"), resultSet.getString("firstName"), resultSet.getString("lastName"), resultSet.getString("username"), resultSet.getString("email"), resultSet.getString("password")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return friends;
    }
    
    public UserEntity findByUsernameAndPassword(String username, String password){
        try{
           String query = "SELECT * FROM [todoDB].[dbo].[user] WHERE username = ? AND password = ?";
           PreparedStatement preparedStatement = con.prepareStatement(query);
           preparedStatement.setString(1, username);
           preparedStatement.setString(2, password);
           
           ResultSet resultSet = preparedStatement.executeQuery();
           if(resultSet.next())
               return new UserEntity(resultSet.getInt("id"), resultSet.getString("firstName"), resultSet.getString("lastName"), resultSet.getString("username"), resultSet.getString("email"), resultSet.getString("password"));
        
           
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }
    
    public boolean insertFriend(int userId, int friendId){
        try {
            String query = "INSERT INTO [todoDB].[dbo].[user_friend] (userId, friendId) VALUES (?, ?)";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, friendId);
            
            if(preparedStatement.executeUpdate() > 0)
                return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public ArrayList<UserEntity> findAllListRequestedCollaborators(int todoId){
        ArrayList<UserEntity> collaborators = new ArrayList<UserEntity>();
        try {
            String query = "SELECT u.id, u.username\n" +
                           "FROM [todoDB].[dbo].[collaboration_request] AS cr, [todoDB].[dbo].[user] AS u\n" +
                           "WHERE cr.receiverUserId = u.id AND cr.todoId = ?;";

            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, todoId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                collaborators.add(new UserEntity(resultSet.getInt("id"), resultSet.getString("username")));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return collaborators;
    }
}
