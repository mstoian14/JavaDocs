package ro.teamnet.zth.api.em;

import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.annotations.Id;
import ro.teamnet.zth.api.database.DBManager;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
        condition.setColumnName(fieldsIds.get(0).getName());
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
            if(resultSet.next() == true) {
                //create an instance of type T
                t = entityClass.newInstance();
                //iterate through ColumnInfo list and obtain the field of the instance using getColumnName()
                for(ColumnInfo ci : columns) {
                    String columnName = ci.getColumnName();
                    Field field = t.getClass().getDeclaredField(columnName);
                    //make the field accessible
                    field.setAccessible(true);
                    //set the value of the field with the value obtained from resultSet object
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
            String query = QueryType.SELECT + "max(" + columnIdName + ") FROM " + tableName;
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.beforeFirst();
            //+1
            nextIdVal = resultSet.getLong(columnIdName) + 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nextIdVal;
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
}
