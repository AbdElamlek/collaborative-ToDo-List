/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOController;

import Connection.DataBaseConnection;
import DAOs.BaseDAO;
import Entities.CollaborationRequestEntity;
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
 * @author pc
 */
public class CollaboratorRequestController implements BaseDAO<CollaborationRequestEntity> {

    private Connection connection = DataBaseConnection.getInstance();
    private PreparedStatement preparedStatement;
    private CollaborationRequestEntity collaborationRequestEntity;

    /*EMAN KAMAL*/
    @Override
    public ArrayList<CollaborationRequestEntity> findAll() {
        int id = 0;
        Date time = null;
        int receiverUserId = 0;
        int senderUserId = 0;
        int todoId = 0;
        ArrayList<CollaborationRequestEntity> request_list = new ArrayList<CollaborationRequestEntity>();
        try {
            PreparedStatement pst = connection.prepareStatement("SELECT * FROM [todoDB].[dbo].[collaboration_request] ");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
                time = rs.getDate(2);
                receiverUserId = rs.getInt(3);
                senderUserId = rs.getInt(4);
                todoId = rs.getInt(5);
                request_list.add(new CollaborationRequestEntity(todoId, id, time,receiverUserId, senderUserId));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FriendRequestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return request_list;
    }

    @Override
    public CollaborationRequestEntity findById(int id) {
        Date time = null;
        int receiverUserId = 0;
        int senderUserId = 0;
        int todoId = 0;
        try {
            PreparedStatement pst = connection.prepareStatement("SELECT * FROM [todoDB].[dbo].[collaboration_request] WHERE id = ?");
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
                time = rs.getDate(2);
                receiverUserId = rs.getInt(3);
                senderUserId = rs.getInt(4);
                todoId = rs.getInt(5);
                collaborationRequestEntity = new CollaborationRequestEntity(todoId, id, time, receiverUserId, senderUserId);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FriendRequestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return collaborationRequestEntity;
    }

    @Override
    public boolean insert(CollaborationRequestEntity entity) {
        int rows_affected = 0;
        PreparedStatement pst = null;
        try {
            pst = connection.prepareStatement("INSERT INTO [todoDB].[dbo].[collaboration_request] (time, receiverUserId,senderUserId,todoId) VALUES (?,?,?,?)");
            pst.setDate(1, entity.getTime());
            pst.setInt(2, entity.getReceivedUserId());
            pst.setInt(3, entity.getSentUserId());
            pst.setInt(4, entity.getTodoId());
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

    @Override
    public boolean update(CollaborationRequestEntity entity) {
        int rows_affected = 0;
        try {
            PreparedStatement pst
                    = connection.prepareStatement("UPDATE [todoDB].[dbo].[collaboration_request] SET time = ?, receiverUserId = ?,senderUserId=? ,todoId=? WHERE id = ?");
            pst.setDate(1, entity.getTime());
            pst.setInt(2, entity.getReceivedUserId());
            pst.setInt(3, entity.getSentUserId());
            pst.setInt(4, entity.getId());
            pst.setInt(5, entity.getTodoId());
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
    public boolean delete(CollaborationRequestEntity entity) {
        int rows_affected = 0;
        try {
            PreparedStatement pst = connection.prepareStatement("DELETE FROM [todoDB].[dbo].[collaboration_request] WHERE id = ?");
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
    public ArrayList<CollaborationRequestEntity> findByReceiverId(int receiverId) {
        ArrayList<CollaborationRequestEntity> requests = new ArrayList<CollaborationRequestEntity>();
        try {
            String query = "SELECT *\n"
                    + "FROM [todoDB].[dbo].[collaboration_request]\n"
                    + "WHERE receiverUserId = ?;";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, receiverId);

            ResultSet rs = preparedStatement.executeQuery();
            Date time = null;
            int id=0;
            int senderUserId = 0;
            int todoId = 0;
            while (rs.next()) {
                id = rs.getInt(1);
                time = rs.getDate(2);
                receiverId = rs.getInt(3);
                senderUserId = rs.getInt(4);
                todoId = rs.getInt(5);
                requests.add(new CollaborationRequestEntity(todoId, id, time, receiverId, senderUserId));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return requests;
    }
}
