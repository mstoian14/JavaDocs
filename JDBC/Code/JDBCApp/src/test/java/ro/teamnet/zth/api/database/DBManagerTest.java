package ro.teamnet.zth.api.database;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Mihaela.Stoian on 7/13/2017.
 */
public class DBManagerTest {
    @Test
    public void testGetConnection() {
        //Constructor<DBManager> constructor = (Constructor<DBManager>) DBManager.class.getDeclaredConstructors()[0];
        Connection connection = DBManager.getConnection();
        boolean expected = true;
        assertEquals(expected, DBManager.checkConnection(connection));
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
