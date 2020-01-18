/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOController;

import Connection.DataBaseConnection;
import DAOs.BaseDAO;
import Entities.Accept_RejectTaskEntity;
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
public class Accept_RejectTaskController implements BaseDAO<Accept_RejectTaskEntity>{
    
    private Connection connection = DataBaseConnection.getInstance();
    private PreparedStatement preparedStatement;
    private Accept_RejectTaskEntity accept_RecjectTaskEntity;

    @Override
    public ArrayList<Accept_RejectTaskEntity> findAll() {
        int id = 0;
        Date time = null;
        int receiverUserId = 0;
        int senderUserId = 0;
        int taskId;
        String message;
        ArrayList<Accept_RejectTaskEntity> request_list = new ArrayList<Accept_RejectTaskEntity>();
        try {
            PreparedStatement pst = connection.prepareStatement("SELECT * FROM [todoDB].[dbo].[task_assignment_request] ");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
                time = rs.getDate(2);
                receiverUserId = rs.getInt(3);
                senderUserId = rs.getInt(4);
                taskId = rs.getInt(5);
                message = rs.getString("message");
                request_list.add(new Accept_RejectTaskEntity(id, time,receiverUserId, senderUserId, taskId, message));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return request_list;
    }

    @Override
    public Accept_RejectTaskEntity findById(int id) {
        
        Date time = null;
        int receiverUserId = 0;
        int senderUserId = 0;
        int taskId;
        String message;
        try {
            PreparedStatement pst = connection.prepareStatement("SELECT * FROM [todoDB].[dbo].[task_assignment_request] WHERE id = ?");
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
                time = rs.getDate(2);
                receiverUserId = rs.getInt(3);
                senderUserId = rs.getInt(4);
                taskId = rs.getInt(5);
                message = rs.getString("message");
                accept_RecjectTaskEntity = new Accept_RejectTaskEntity(id, time,receiverUserId, senderUserId,taskId, message);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return accept_RecjectTaskEntity;
    }

    @Override
    public boolean insert(Accept_RejectTaskEntity entity) {
       int rows_affected = 0;
        PreparedStatement pst=null;
        try {
            pst = connection.prepareStatement("INSERT INTO [todoDB].[dbo].[task_assignment_request] (time,receiverUserId,senderUserId,taskId,message) VALUES (?,?,?,?,?)");
            pst.setDate(1, entity.getTime());
            pst.setInt(2, entity.getReceivedUserId());
            pst.setInt(3, entity.getSentUserId());
            pst.setInt(4, entity.getTaskId());
            pst.setString(5, entity.getMessage());
            rows_affected = pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
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
                ex.printStackTrace();
            }
             return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean update(Accept_RejectTaskEntity entity) {
        int rows_affected = 0;
        try {
            PreparedStatement pst
                    = connection.prepareStatement("UPDATE [todoDB].[dbo].[task_assignment_request] SET time = ?, receiverUserId = ?,senderUserId=?, taskId=?, message=? WHERE id = ?");
            pst.setDate(1, entity.getTime());
            pst.setInt(2, entity.getReceivedUserId());
            pst.setInt(3, entity.getSentUserId());
            pst.setInt(4, entity.getTaskId());
            pst.setString(5, entity.getMessage());
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
    public boolean delete(Accept_RejectTaskEntity entity) {
         int rows_affected = 0;
        try {
            PreparedStatement pst = connection.prepareStatement("DELETE FROM [todoDB].[dbo].[task_assignment_request] WHERE id = ?");
            pst.setInt(1, entity.getId());
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

   
    
}
