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
import java.sql.Date;
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
public class FriendRequestController<RequestDAO> implements BaseDAO<RequestEntity> {

    private Connection connection = DataBaseConnection.getInstance();
    private PreparedStatement preparedStatement;
    private RequestEntity requestEntity;

    /*EMAN KAMAL*/
    @Override
    public ArrayList<RequestEntity> findAll() {
        int id = 0;
        Date time = null;
        int receiverUserId = 0;
        int senderUserId = 0;
        ArrayList<RequestEntity> request_list = new ArrayList<RequestEntity>();
        try {
            PreparedStatement pst = connection.prepareStatement("SELECT * FROM [todoDB].[dbo].[friend_request] ");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
                time = rs.getDate(2);
                receiverUserId = rs.getInt(3);
                senderUserId = rs.getInt(4);
                request_list.add(new RequestEntity(id, time,receiverUserId, senderUserId));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FriendRequestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return request_list;
    }

    @Override
    public RequestEntity findById(int id) {
        Date time = null;
        int receiverUserId = 0;
        int senderUserId = 0;
        try {
            PreparedStatement pst = connection.prepareStatement("SELECT * FROM [todoDB].[dbo].[friend_request] WHERE id = ?");
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
                time = rs.getDate(2);
                receiverUserId = rs.getInt(3);
                senderUserId = rs.getInt(4);
                requestEntity = new RequestEntity(id, time,receiverUserId, senderUserId);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FriendRequestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return requestEntity;
    }

    @Override
    public boolean insert(RequestEntity entity) {
        int rows_affected = 0;
        PreparedStatement pst=null;
        try {
            pst = connection.prepareStatement("INSERT INTO [todoDB].[dbo].[friend_request] (time,receiverUserId,senderUserId) VALUES (?,?,?)");
            pst.setDate(1, entity.getTime());
            pst.setInt(2, entity.getReceivedUserId());
            pst.setInt(3, entity.getSentUserId());
            rows_affected = pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FriendRequestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (rows_affected > 0) {
            try {
                ResultSet rs2 = pst.getGeneratedKeys();
                if (rs2.next()) {
                    int reqId = rs2.getInt(1);
                    entity.setId(reqId);
                    System.out.println(reqId);
                }
               
            } catch (SQLException ex) {
                Logger.getLogger(FriendRequestController.class.getName()).log(Level.SEVERE, null, ex);
            }
             return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean update(RequestEntity entity) {
        int rows_affected = 0;
        try {
            PreparedStatement pst
                    = connection.prepareStatement("UPDATE [todoDB].[dbo].[friend_request] SET time = ?, receiverUserId = ?,senderUserId=? WHERE id = ?");
            pst.setDate(1, entity.getTime());
            pst.setInt(2, entity.getReceivedUserId());
            pst.setInt(3, entity.getSentUserId());
            pst.setInt(4, entity.getId());
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
    public boolean delete(RequestEntity entity) {
        int rows_affected = 0;
        try {
            PreparedStatement pst = connection.prepareStatement("DELETE FROM [todoDB].[dbo].[friend_request] WHERE id = ?");
            pst.setInt(1, entity.getId());
            rows_affected = pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FriendRequestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (rows_affected > 0) {
            return true;
        } else {
            return false;
        }
    }

    /*EMAN KAMAL */
    public ArrayList<RequestEntity> findByReceiverId(int receiverId) {
        ArrayList<RequestEntity> requests = new ArrayList<RequestEntity>();

        try {
            String query = "SELECT *\n"
                    + "FROM [todoDB].[dbo].[request]\n"
                    + "WHERE receiverUserId = ?;";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, receiverId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                requests.add(new RequestEntity(resultSet.getInt("id"), resultSet.getDate("time"),resultSet.getInt("receiverUserId"), resultSet.getInt("senderUserId")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return requests;
    }

}
