package ro.teamnet.zth.api.em;

import oracle.net.aso.n;
import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.annotations.Id;
import ro.teamnet.zth.api.database.DBManager;
import ro.teamnet.zth.appl.domain.Department;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Mihaela.Stoian on 7/13/2017.
 */
public class EntityManagerImpl implements EntityManager {


    @Override
    public <T> T findById(Class<T> entityClass, Long id) {
        Connection connection = DBManager.getConnection();
        String tableName = EntityUtils.getTableName(entityClass);
        List<ColumnInfo> columns = EntityUtils.getColumns(entityClass);
        List<Field> fieldsColumns = EntityUtils.getFieldsByAnnotations(entityClass, Column.class);
        List<Field> fieldsIds = EntityUtils.getFieldsByAnnotations(entityClass, Id.class);

        Condition condition = new Condition();
        condition.setColumnName(fieldsIds.get(0).getAnnotation(Id.class).name());
        condition.setValue(id);

        //create a QueryBuilder - set name of table, columns, query type and conditions
        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.setTableName(tableName);
        queryBuilder.addQueryColumns(columns);
        queryBuilder.addCondition(condition);
        queryBuilder.setQueryType(QueryType.SELECT);

        //call createQuery method from QueryBuilder.java
        String querySql = queryBuilder.createQuery();
        //create instance
        T t = null;
        //create a resultSet obj using Statement and execute the query obt. above
        try {
            Statement stm = connection.createStatement();
            ResultSet resultSet = stm.executeQuery(querySql);
            //if the resultSet has an value
            if(resultSet.next()) {
                //create an instance of type T
                t = entityClass.newInstance();
                //iterate through ColumnInfo list and obtain the field of the instance using getColumnName()
                // printRs(resultSet);
                //resultSet.beforeFirst();
                for (ColumnInfo ci : columns) {
                        //get the column name in the table
                        String columnDB = ci.getDbColumnName();
                        //get de value from the result set
                        Object value = resultSet.getObject(columnDB);
                        //set the value in column info
                        ci.setValue(EntityUtils.castFromSqlType(value, ci.getColumnType()));
                        String columnName = ci.getColumnName();
                        Field field = t.getClass().getDeclaredField(columnName);
                        field.setAccessible(true);
                        field.set(t, EntityUtils.castFromSqlType(ci.getValue(), ci.getColumnType()));

                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        //close connection
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //return the instance
        return t;
    }

    @Override
    public Long getNextIdVal(String tableName, String columnIdName) {
        //create a connection to db
        Connection connection = DBManager.getConnection();
        Long nextIdVal = null;
        //create a statement obj.
        try {
            Statement statement = connection.createStatement();
            //execute query that returns the maximum value of the id
            String query = "select max(" + columnIdName + ") as "+ columnIdName + " from " + tableName;
            ResultSet resultSet = statement.executeQuery(query);
            //resultSet.beforeFirst();
            //+1
            if(resultSet.next()) {
                nextIdVal = new Long(resultSet.getLong(columnIdName) + 1);
                return nextIdVal;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Long(0);
    }

    @Override
    public <T> Object insert(T entity) throws NoSuchFieldException, IllegalAccessException, SQLException {
        //create a connection to db
        Connection connection = DBManager.getConnection();
        //get table name
        String tableName = EntityUtils.getTableName(entity.getClass());
        //get colums
        List<ColumnInfo> columnInfos = EntityUtils.getColumns(entity.getClass());
        Long nextId = null;
        
        //iterate through ColumnInfo list
        for(ColumnInfo ci : columnInfos) {
            //if the column is an id
            if(ci.isId() == true) {
                //set its value using getNextIdVal
                nextId = getNextIdVal(tableName, ci.getDbColumnName());
                ci.setValue(nextId);
            } else {
                //else call getDeclaredField by column name on entity T
               
                    Field field = entity.getClass().getDeclaredField(ci.getColumnName());
                    //make the field accessible
                    field.setAccessible(true);
                    //set the value of the columnInfo with the value obtained from the field
                    ci.setValue(field.get(entity));
            }
        }

        //create a queryBuilder obj. in which you hate to set the name of the table, columns and query type
        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.setTableName(tableName);
        queryBuilder.setQueryType(QueryType.INSERT);
        queryBuilder.addQueryColumns(columnInfos);
        
        //call createQuery() method from QueryBuilder.java
        String query = queryBuilder.createQuery();
        ///create a statement obj. and execute the query
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        
        //return inserted entity using findByIdMethod
        
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  findById(entity.getClass(), nextId);
    }

    @Override
    public <T> List<T> findAll(Class<T> entityClass) throws SQLException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        //create a connection
        Connection connection = DBManager.getConnection();
        //get table name, columns
        List<ColumnInfo> columnInfos = EntityUtils.getColumns(entityClass);
        String tableName = EntityUtils.getTableName(entityClass);

        //create a queryBuilder obj.
        QueryBuilder queryBuilder = new QueryBuilder();
        //set the name of the table
        queryBuilder.setTableName(tableName);
        //set columns
        queryBuilder.addQueryColumns(columnInfos);
        //set type of query
        queryBuilder.setQueryType(QueryType.SELECT);
        //call createQuery method from QueryBuilder.java
        String query = queryBuilder.createQuery();
        //create a resultSet obj. using Statement and execute the query obtained above
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        //create an arraylist of type t
        ArrayList<T> arrayList = new ArrayList<>();

        //while the resultSet has any values
        while(resultSet.next() == true) {
            //you have to create an instance of type T
            T t = entityClass.newInstance();
            //iterate through ColumnInfo list and obtain the field of the instance using getColumnName()
            for(ColumnInfo ci : columnInfos) {
                String columnName = ci.getColumnName();
                Field field = t.getClass().getDeclaredField(columnName);
                //make the field accessible
                field.setAccessible(true);
                //set the value of the field with the value obtained from resulSet obj
                field.set(t, EntityUtils.castFromSqlType(ci.getValue(), ci.getColumnType()));
                arrayList.add(t);
            }
        }
        return arrayList;
    }

    @Override
    public <T> T update(T entity) throws NoSuchFieldException, IllegalAccessException, SQLException {
        //create a connection to DB
        Connection connection = DBManager.getConnection();
        //get table name using the methods from EntityUtils class
        String tableName = EntityUtils.getTableName(entity.getClass());
        //get columns
        List<ColumnInfo> columnInfos = EntityUtils.getColumns(entity.getClass());
        //iterate through columnInfo list
        for(ColumnInfo ci : columnInfos) {
            //get declared field by column name
            System.out.println(ci.getColumnName());
            Field field = entity.getClass().getDeclaredField(ci.getColumnName());
            //make the field accessible
            field.setAccessible(true);
            //set the value of the columnInfo with the value obtained from the field
            ci.setValue(field.get(entity));
        }

        List<Field> fieldList = EntityUtils.getFieldsByAnnotations(entity.getClass(), Id.class);
        //create a condition object where you need to set id value which will be updated
        Condition condition = new Condition();
        condition.setColumnName(fieldList.get(0).getAnnotation(Id.class).name());
        condition.setValue(columnInfos.get(0).getValue());

        //create a querybuilder obj.
        QueryBuilder queryBuilder = new QueryBuilder();
        //set the name of the table
        queryBuilder.setTableName(tableName);
        //set query type
        queryBuilder.setQueryType(QueryType.UPDATE);
        //set query colums
        queryBuilder.addQueryColumns(columnInfos);
        //set query conditions
        queryBuilder.addCondition(condition);

        String query = queryBuilder.createQuery();
        System.out.println(query);
        Long idTable = (Long) columnInfos.get(0).getValue();
        //create a statement
        Statement statement = connection.createStatement();
        //execute the query
        statement.executeUpdate(query);

        System.out.println("table is up.");
        T updatedObj = (T) findById(entity.getClass(), idTable);
        return updatedObj;
    }

    @Override
    public void delete(Object entity) throws NoSuchFieldException, IllegalAccessException, SQLException {
        //create a connection to db
        Connection connection = DBManager.getConnection();
        //get table name
        String tableName = EntityUtils.getTableName(entity.getClass());
        //get colums
        List<ColumnInfo> columnInfos = EntityUtils.getColumns(entity.getClass());
        //iterate through ColumnInfo list
        for(ColumnInfo ci : columnInfos) {
            //get declared field by column name
            Field field = entity.getClass().getDeclaredField(ci.getColumnName());
            //make the field accessible
            field.setAccessible(true);
            //set the value of the columnInfo with the value obtained from the field
            ci.setValue(field.get(entity));
        }

        List<Field> fieldList = EntityUtils.getFieldsByAnnotations(entity.getClass(), Id.class);
        //create a condition obj.
        Condition condition = new Condition();
        String columnName = fieldList.get(0).getAnnotation(Id.class).name();
        condition.setColumnName(columnName);
        condition.setValue(columnInfos.get(0).getValue());

        //create a queryBuilder obj.
        QueryBuilder queryBuilder = new QueryBuilder();
        //set name of table
        queryBuilder.setTableName(tableName);
        //set query type
        queryBuilder.setQueryType(QueryType.DELETE);
        //set conditions
        queryBuilder.addCondition(condition);
        //call createQuery()
        String query = queryBuilder.createQuery();
        System.out.println(query);
        //create a statement obj.
        Statement statement = connection.createStatement();
        //execute the statement
        statement.executeUpdate(query);
        System.out.println("table up");

    }

    @Override
    public <T> List<T> findByParams(Class<T> entityClass, Map<String, Object> params) throws NoSuchFieldException, IllegalAccessException {
        //create connection
        Connection connection = DBManager.getConnection();
        //get table name
        String tableName = EntityUtils.getTableName(entityClass);
        //get columns
        List<ColumnInfo> columnInfos = EntityUtils.getColumns(entityClass);
        //iterate through columninfo
        for(ColumnInfo ci : columnInfos) {
            //get declaredfield by columnName
            Field field = entityClass.getDeclaredField(ci.getColumnName());
            //make the field accessible
            field.setAccessible(true);
            //set the value of the columnInfo with the value obtained from the field
            ci.setValue(field.get(entityClass));
        }
        //create conditions
        Condition condition = new Condition();

        //create a queryBuilder obj.
        QueryBuilder queryBuilder = new QueryBuilder();
        //set the name of the tabl
        queryBuilder.setTableName(tableName);
        //set query type
        queryBuilder.setQueryType(QueryType.SELECT);
        //set conditions
        for(Map.Entry<String, Object> me : params.entrySet()) {
            System.out.println(me.getKey() + " : " + me.getValue());
        }

        return null;
    }
}
