/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOController;

import Connection.DataBaseConnection;
import DAOs.BaseDAO;
import Entities.BaseEntity;
import Entities.NotificationEntity;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


/**
 *
 * @author Abd-Elmalek
 */
public class NotificationController<NotificationDAO> implements BaseDAO<NotificationEntity>{
    private Connection connection = DataBaseConnection.getInstance();
    private PreparedStatement preparedStatement;

    @Override
    public ArrayList<NotificationEntity> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public NotificationEntity findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean insert(NotificationEntity entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(NotificationEntity entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(NotificationEntity entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public ArrayList<NotificationEntity> findByReceiverId(int receiverId){
        ArrayList<NotificationEntity> notifications = new ArrayList<NotificationEntity>();
        
        try{
            String query = "SELECT *\n" +
                           "FROM [todoDB].[dbo].[notification]\n" +
                           "WHERE receiverUserId = ?;";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, receiverId);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
                notifications.add(new NotificationEntity(resultSet.getInt("id"), resultSet.getDate("time"), resultSet.getInt("type"), resultSet.getInt("receiverUserId"), resultSet.getInt("senderUserId")));
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return notifications;
    }

   
   
    
}


    

