/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import Utils.Constants;
import com.microsoft.sqlserver.jdbc.SQLServerDriver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Abd-Elmalek
 */
public class DataBaseConnection {

    private static DriverManager driverManager;
    private static Connection connection;
    private static SQLServerDriver sQLServerDriver = new SQLServerDriver();

    private static DataBaseConnection dataBaseConnection;

    public static Connection getInstance() {
      
        if (dataBaseConnection == null) 
            synchronized(DataBaseConnection.class){
                if(dataBaseConnection == null)
                    dataBaseConnection = new DataBaseConnection();
            }
        return connection;    
    }

    private DataBaseConnection() {

        try {
            driverManager.registerDriver(sQLServerDriver);
            connection = driverManager.getConnection(Constants.DBUrl);
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
