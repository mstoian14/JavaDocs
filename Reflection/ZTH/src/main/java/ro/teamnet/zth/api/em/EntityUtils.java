package ro.teamnet.zth.api.em;

import javafx.scene.control.Tab;
import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.annotations.Id;
import ro.teamnet.zth.api.annotations.Table;
import ro.teamnet.zth.appl.domain.Department;
import ro.teamnet.zth.appl.domain.Jobs;
import sun.management.Util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Mihaela.Stoian on 7/12/2017.
 */
public class EntityUtils {
    private EntityUtils() {
        throw new UnsupportedOperationException("Mesaj.");
    }
    public static String getTableName(Class entity) {
        return ((Table)entity.getAnnotation(Table.class)).name();
    }

    public static List<ColumnInfo> getColumns(Class entity){
        List<ColumnInfo> l = new LinkedList<>();
        Field fields[] = entity.getDeclaredFields();
        for(Field f : fields) {
            if(f.isAnnotationPresent(Column.class) || f.isAnnotationPresent(Id.class)) {
               ColumnInfo ci = new ColumnInfo();
               ci.setColumnType(f.getType());
               ci.setColumnName(f.getName());
               if(f.isAnnotationPresent(Column.class) == true) {
                   ci.setDbColumnName((f.getAnnotation(Column.class)).name());
                   ci.setId(false);
               } else {
                   ci.setId(true);
                   ci.setDbColumnName((f.getAnnotation(Id.class)).name());
               }
               l.add(ci);
            }
        }

        return l;
    }

    public static Object castFromSqlType(Object value, Class wantedType) {
        Object obj;
        if(value instanceof BigDecimal) {
            System.out.println(wantedType.getName());
        }
        return value;
    }

    public static void main(String[] args) {
        Object o = castFromSqlType(new BigDecimal(2.2), Integer.class);
    }
}
