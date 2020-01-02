/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOController;

import Connection.DataBaseConnection;
import DAOs.BaseDAO;
import Entities.BaseEntity;
import Entities.RequestEntity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Abd-Elmalek
 */
public class RequestController <RequestDAO> implements BaseDAO<RequestEntity>{
    private Connection connection = DataBaseConnection.getInstance();
    private PreparedStatement preparedStatement;
    
    @Override
    public ArrayList<RequestEntity> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RequestEntity findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean insert(RequestEntity entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(RequestEntity entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(RequestEntity entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<RequestEntity> findByReceiverId(int receiverId){
        ArrayList<RequestEntity> requests = new ArrayList<RequestEntity>();
        
        try{
            String query = "SELECT *\n" +
                           "FROM [todoDB].[dbo].[request]\n" +
                           "WHERE receiverUserId = ?;";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, receiverId);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
                requests.add(new RequestEntity(resultSet.getInt("id"), resultSet.getDate("time"), resultSet.getInt("type"), resultSet.getInt("receiverUserId"), resultSet.getInt("senderUserId")));
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return requests;
    }

   
   
    
}
  
    

