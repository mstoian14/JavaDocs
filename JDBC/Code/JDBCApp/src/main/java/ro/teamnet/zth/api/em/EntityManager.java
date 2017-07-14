package ro.teamnet.zth.api.em;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by Mihaela.Stoian on 7/13/2017.
 */
public interface EntityManager {
    <T> T findById(Class<T> entityClass, Long id);
    Long getNextIdVal(String tableName, String columnIdName);
    <T> Object insert(T entity) throws NoSuchFieldException, IllegalAccessException, SQLException;
    <T>List<T> findAll(Class<T> entityClass) throws SQLException, IllegalAccessException, InstantiationException, NoSuchFieldException;
    <T> T update(T entity) throws NoSuchFieldException, IllegalAccessException, SQLException;
    void delete(Object entity) throws NoSuchFieldException, IllegalAccessException, SQLException;
    <T> List<T>findByParams(Class<T> entityClass, Map<String, Object> params) throws NoSuchFieldException, IllegalAccessException;
}
