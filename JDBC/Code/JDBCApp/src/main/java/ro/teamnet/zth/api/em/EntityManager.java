package ro.teamnet.zth.api.em;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Mihaela.Stoian on 7/13/2017.
 */
public interface EntityManager {
    <T> T findById(Class<T> entityClass, Long id);
    Long getNextIdVal(String tableName, String columnIdName);
    <T> Object insert(T entity) throws NoSuchFieldException, IllegalAccessException, SQLException;
    <T>List<T> findAll(Class<T> entityClass) throws SQLException, IllegalAccessException, InstantiationException, NoSuchFieldException;
}
