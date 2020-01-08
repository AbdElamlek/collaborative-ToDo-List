/*
Eman Kamal
 */
package DAOController;

import Connection.DataBaseConnection;
import DAOs.BaseDAO;
import Entities.TaskEntity;
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
public class TaskController<TaskDAO> implements BaseDAO<TaskEntity> {

    private Connection con = DataBaseConnection.getInstance();
    private TaskEntity taskEntity;

    @Override
    public ArrayList<TaskEntity> findAll() {
        int id = 0;
        String decription = "";
        int status = 0;
        int itemId = 0;
        ArrayList<TaskEntity> tasks_list = new ArrayList<TaskEntity>();
        try {
            PreparedStatement pst = con.prepareStatement("select * from task");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
                decription = rs.getString(2);
                status = rs.getInt(3);
                itemId = rs.getInt(4);
                tasks_list.add(new TaskEntity(id, decription, status, itemId));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TaskController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tasks_list;
    }

    @Override
    public TaskEntity findById(int id) {
        String decription = "";
        int status = 0;
        int itemId = 0;
        try {
            PreparedStatement pst = con.prepareStatement("select * from task where id=?");
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
                decription = rs.getString(2);
                status = rs.getInt(3);
                itemId = rs.getInt(4);
                taskEntity = new TaskEntity(id, decription, status, itemId);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TaskController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return taskEntity;

    }

    @Override
    public boolean insert(TaskEntity entity) {
        int rows_affected = 0;
        try {
            PreparedStatement pst = con.prepareStatement("insert into task (description,status,itemId) values (?,?,?)");
            pst.setString(1, entity.getDecription());
            pst.setInt(2, entity.getStatus());
            pst.setInt(3, entity.getItemId());
            rows_affected = pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TaskController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (rows_affected > 0) {
            return true;
        } else {
            return false;
        }
        //System.out.println("desc "+entity.getDecription());
        
    }

    @Override
    public boolean update(TaskEntity entity) {
        int rows_affected = 0;
        try {
            PreparedStatement pst
                    = con.prepareStatement("update task set description = ?,status = ?,itemId = ? where id = ?");
            pst.setString(1, entity.getDecription());
            pst.setInt(2, entity.getStatus());
            pst.setInt(3, entity.getItemId());
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
    public boolean delete(TaskEntity entity) {
        int rows_affected = 0;
        try {
            PreparedStatement pst = con.prepareStatement("delete from task where id = ?");
            pst.setInt(1, entity.getId());
            rows_affected = pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TaskController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (rows_affected > 0) {
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<TaskEntity> findByItemId(int itemId){
        ArrayList<TaskEntity> tasks = new ArrayList<TaskEntity>();
        
        try{
            String query = "SELECT * FROM [todoDB].[dbo].[task] WHERE itemId = ?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            
            preparedStatement.setInt(1, itemId);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next())
                tasks.add(new TaskEntity(resultSet.getInt("id"), resultSet.getString("description"), resultSet.getInt("status"), itemId));
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return tasks;
    }
    
    public ArrayList<TaskEntity> findAllUserAssignedTasks(int userId){
        ArrayList<TaskEntity> tasks = new ArrayList<TaskEntity>();
        
        try{
            String query = "SELECT t.id, t.description, t.status, t.itemId\n" +
                           "FROM [todoDB].[dbo].[task] AS t, [todoDB].[dbo].[user_assigned_task] AS uat\n" +
                           "WHERE t.id = uat.taskId AND uat.userId = ?;";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next())
                tasks.add(new TaskEntity(resultSet.getInt("id"), resultSet.getString("description"), resultSet.getInt("status"), resultSet.getInt("itemId")));
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return tasks;
    }
    
    public boolean insertUserTaskAssignment(int userId, int taskId){
        try {
            String query = "INSERT INTO [todoDB].[dbo].[user_assigned_task] (userId, taskId) VALUES (?, ?)";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, taskId);
            
            if(preparedStatement.executeUpdate() > 0)
                return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public boolean deleteUserTaskAssignment(int userId, int taskId){
         int rows_affected = 0;
        try {
            PreparedStatement pst = con.prepareStatement("delete from [user_assigned_task] where userId = ? and taskId = ?");
            pst.setInt(1, userId);
            pst.setInt(2, taskId);
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

/*
Eman Kamal
 */
