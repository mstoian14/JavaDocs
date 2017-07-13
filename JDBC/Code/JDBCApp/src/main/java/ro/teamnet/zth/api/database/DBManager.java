package ro.teamnet.zth.api.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Mihaela.Stoian on 7/13/2017.
 */
public class DBManager {
    public static final String CONNECTION_STRING = "jdbc:oracle:thin:@" +
            DBProperties.IP + ":" + DBProperties.PORT + ":xe";
    private DBManager() {
        throw new UnsupportedOperationException();
    }

    private static void registerDriver(){
        try {
            Class.forName(DBProperties.DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            System.out.println("Error: unable to load driver class!");
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(CONNECTION_STRING, DBProperties.USER, DBProperties.PASS);
        } catch (SQLException e) {
            System.out.println("Cannot connect!");
            e.printStackTrace();
        }

        return connection;
    }

    public static boolean checkConnection(Connection connection) {
        boolean isReachable = false;
        try {
            Statement stmt=connection.createStatement();
            isReachable= stmt.execute("SELECT 1 FROM DUAL");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isReachable;
    }
}
