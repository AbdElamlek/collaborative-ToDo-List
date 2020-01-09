/*
    Eman Kamal
 */
package DAOController;

import Connection.DataBaseConnection;
import DAOs.BaseDAO;
import java.util.logging.Level;
import java.util.logging.Logger;
import Entities.ToDoEntity;
import Entities.UserEntity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.Statement;

/**
 *
 * @author Abd-Elmalek
 */
public class ToDoController<ToDoDAO> implements BaseDAO<ToDoEntity> {

    private ToDoEntity toDoEntity;
    private Connection con = DataBaseConnection.getInstance();

    @Override
    public ArrayList<ToDoEntity> findAll() {
        int id = 0;
        String title = "";
        Date assignDate = null;
        Date deadLineDate = null;
        int ownerId = 0;
        int status = 0;
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
                status = rs.getInt(6);
                todo_list.add(new ToDoEntity(id, title, assignDate, deadLineDate, ownerId, status));
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
        int status = 0;
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
                status = rs.getInt(6);
                toDoEntity = new ToDoEntity(id, title, assignDate, deadLineDate, ownerId, status);
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
            PreparedStatement pst = con.prepareStatement("insert into todo (title,assignDate,deadLineDate,ownerId,status) values (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, entity.getTitle());
            pst.setDate(2, entity.getAssignDate());
            pst.setDate(3, entity.getDeadLineDate());
            pst.setInt(4, entity.getOwnerId());
            pst.setInt(5, entity.getStatus());
            rows_affected = pst.executeUpdate();

            if (rows_affected > 0) {
                ResultSet resultSet = pst.getGeneratedKeys();
                if (resultSet.next()) {
                    entity.setId(resultSet.getInt(1));
                    return true;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(TaskController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean update(ToDoEntity entity) {
        int rows_affected = 0;
        try {
            PreparedStatement pst
                    = con.prepareStatement("update todo set title = ?,assignDate = ?,deadLineDate = ?,ownerId = ?,status = ? where id = ?");
            pst.setString(1, entity.getTitle());
            pst.setDate(2, entity.getAssignDate());
            pst.setDate(3, entity.getDeadLineDate());
            pst.setInt(4, entity.getOwnerId());
            pst.setInt(5, entity.getStatus());
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

    public ArrayList<ToDoEntity> findByOwnerId(int ownerId) {
        ArrayList<ToDoEntity> todos = new ArrayList<ToDoEntity>();

        try {
            String query = "SELECT * FROM [todoDB].[dbo].[todo] WHERE ownerId = ?";
            PreparedStatement preparedStatement = con.prepareStatement(query);

            preparedStatement.setInt(1, ownerId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                todos.add(new ToDoEntity(resultSet.getInt("id"), resultSet.getString("title"), resultSet.getDate("assignDate"), resultSet.getDate("deadLineDate"), ownerId, resultSet.getInt("status")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return todos;
    }

    public ArrayList<ToDoEntity> findAllUserCollaboratedInTodos(int userId) {
        ArrayList<ToDoEntity> todos = new ArrayList<ToDoEntity>();
        try {
            String query = "SELECT t.id, t.title, t.assignDate, t.deadLineDate, t.ownerId, t.status\n"
                    + "FROM [todoDB].[dbo].[todo] AS t, [todoDB].[dbo].[user_collaborate_todo] AS uct\n"
                    + "WHERE  t.id = uct.todoId AND uct.collaboratorUserId = ?;";

            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                todos.add(new ToDoEntity(resultSet.getInt("id"), resultSet.getString("title"), resultSet.getDate("assignDate"), resultSet.getDate("deadLineDate"), resultSet.getInt("ownerId"), resultSet.getInt("status")));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return todos;
    }

    public boolean insertUserTodoCollaboration(int userId, int todoId) {
        try {
            String query = "INSERT INTO [todoDB].[dbo].[user_collaborate_todo] (collaboratorUserId, todoId) VALUES (?, ?)";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, todoId);

            if (preparedStatement.executeUpdate() > 0) {
                return true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean deleteUserTodoCollaboration(int userId, int todoId) {
        try {
            String query = "DELETE FROM [todoDB].[dbo].[user_collaborate_todo] WHERE userId = ? AND todoId = ? ";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, todoId);

            if (preparedStatement.executeUpdate() > 0) {
                return true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

}
/*
    EmanKamal
 */
