/*
    Eman Kamal
 */
package DAOController;

import Connection.DataBaseConnection;
import DAOs.BaseDAO;
import java.util.logging.Level;
import java.util.logging.Logger;
import Entities.ToDoEntity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

/**
 *
 * @author Abd-Elmalek
 */
public class ToDoController<ToDoDAO> implements BaseDAO<ToDoEntity> {

    private ToDoEntity toDoEntity;
    private Connection con = DataBaseConnection.getInstance();

    @Override
    public ArrayList<ToDoEntity> findAll() {
        int id =0;
        String title = "";
        Date assignDate = null;
        Date deadLineDate = null;
        int ownerId = 0;
        ArrayList<ToDoEntity> todo_list = new ArrayList<ToDoEntity>();
        try {
            PreparedStatement pst = con.prepareStatement("select * from todo");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
                title = rs.getString(2);
                assignDate = rs.getDate(3);
                deadLineDate = rs.getDate(4);
                ownerId = rs.getInt(5);
                todo_list.add(new ToDoEntity(id, title, assignDate, deadLineDate, ownerId));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TaskController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return todo_list;
    }

    @Override
    public ToDoEntity findById(int id) {
        String title = "";
        Date assignDate = null;
        Date deadLineDate = null;
        int ownerId = 0;
        try {
            PreparedStatement pst = con.prepareStatement("select * from todo where id=?");
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
                title = rs.getString(2);
                assignDate = rs.getDate(3);
                deadLineDate = rs.getDate(4);
                ownerId = rs.getInt(5);
                toDoEntity = new ToDoEntity(id, title, assignDate, deadLineDate, ownerId);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TaskController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return toDoEntity;
    }

    @Override
    public boolean insert(ToDoEntity entity) {
        int rows_affected = 0;
        try {
            PreparedStatement pst = con.prepareStatement("insert into todo values (?,?,?,?)");
            pst.setString(1, entity.getTitle());
            pst.setDate(2, entity.getAssignDate());
            pst.setDate(3, entity.getDeadLineDate());
            pst.setInt(4, entity.getOwnerId());
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

    @Override
    public boolean update(ToDoEntity entity) {
             int rows_affected = 0;
        try {
            PreparedStatement pst
                    = con.prepareStatement("update todo set title = ?,assignDate = ?,deadLineDate = ?,ownerId = ? where id = ?");
            pst.setString(1, entity.getTitle());
            pst.setDate(2, entity.getAssignDate());
            pst.setDate(3, entity.getDeadLineDate());
            pst.setInt(4, entity.getOwnerId());
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
    public boolean delete(ToDoEntity entity) {
        int rows_affected = 0;
        try {
            PreparedStatement pst = con.prepareStatement("delete from todo where id = ?");
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


}
/*
    EmanKamal
 */
