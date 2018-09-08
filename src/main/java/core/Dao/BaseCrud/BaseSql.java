package core.Dao.BaseCrud;

import core.Dao.tableAnnotation.Column;
import core.Dao.tableAnnotation.Id;
import core.Dao.tableAnnotation.Table;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BaseSql {

    public static String queryByEntity(Object entity) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        StringBuilder sql = new StringBuilder();
        // 获取 class
        Class c = entity.getClass();
        // 该类是否为 @Table 旗下的
        boolean annotationPresent = c.isAnnotationPresent(Table.class);
        if (!annotationPresent) {
            return null;
        }
        // 获取 @Table 中的信息
        Table t = (Table) c.getAnnotation(Table.class);
        String tableName = t.value();
        // 1=1 防止下面的 sql 拼装 and 开头报错
        sql.append("select * from ").append(tableName).append(" where 1=1");
        // 遍历所有字段
        Field[] fields = c.getDeclaredFields();
        for (Field field : fields) {
            // 该属性是否为 @Column 旗下的
            boolean fExists = field.isAnnotationPresent(Column.class);
            if (!fExists) {
                continue;
            }
            // 获取 @Column 中的信息
            Column column = field.getAnnotation(Column.class);
            String columnName = column.value();
            // 获取方法名
            String fieldName = field.getName();
            // getXxx 格式，即属性第一个字母大写
            String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            // 获取 getter
            Method getMethod = c.getMethod(getMethodName); // sql 拼接错就报 NoSuchMethodException
            // 执行 getter，fieldValue 可能为 Integer、String 类型，还是 Object 稳点
            Object fieldValue = getMethod.invoke(entity);
            // 去掉 @Column 但为 null 的属性
            if (fieldValue == null) {
                continue;
            }
            // 拼装 sql 注意 ' 不能省略，否则 MySQLSyntaxErrorException：Unknown column
            sql.append(" and ").append(columnName).append("=").append("'").append(fieldValue).append("'");
        }
        return sql.toString();
    }

    public static String deleteByEntity(Object entity) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        StringBuilder sql = new StringBuilder();
        // 获取 class
        Class c = entity.getClass();
        // 该类是否为 @Table 旗下的
        boolean annotationPresent = c.isAnnotationPresent(Table.class);
        if (!annotationPresent) {
            return null;
        }
        // 获取 @Table 中的信息
        Table t = (Table) c.getAnnotation(Table.class);
        String tableName = t.value();
        // 1=1 防止下面的 sql 拼装 and 开头报错
        sql.append("delete from ").append(tableName).append(" where 1=1");
        // 遍历所有字段
        Field[] fields = c.getDeclaredFields();
        for (Field field : fields) {
            // 该属性是否为 @Column 旗下的
            boolean fExists = field.isAnnotationPresent(Column.class);
            if (!fExists) {
                continue;
            }
            // 获取 @Column 中的信息
            Column column = field.getAnnotation(Column.class);
            String columnName = column.value();
            // 获取方法名
            String fieldName = field.getName();
            // getXxx 格式，即属性第一个字母大写
            String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            // 获取 getter
            Method getMethod = c.getMethod(getMethodName); // sql 拼接错就报 NoSuchMethodException
            // 执行 getter，fieldValue 可能为 Integer、String 类型，还是 Object 稳点
            Object fieldValue = getMethod.invoke(entity);
            // 去掉 @Column 但为 null 的属性
            if (fieldValue == null) {
                continue;
            }
            // 拼装 sql 注意 ' 不能省略，否则 MySQLSyntaxErrorException：Unknown column
            sql.append(" and ").append(columnName).append("=").append("'").append(fieldValue).append("'");
        }
        return sql.toString();
    }

    public static String insertByEntity(Object entity) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        StringBuilder sql = new StringBuilder();
        StringBuilder insertField = new StringBuilder();
        StringBuilder insertValue = new StringBuilder();

        // 获取 class
        Class c = entity.getClass();
        // 该类是否为 @Table 旗下的
        boolean annotationPresent = c.isAnnotationPresent(Table.class);
        if (!annotationPresent) {
            return null;
        }
        // 获取 @Table 中的信息
        Table t = (Table) c.getAnnotation(Table.class);
        String tableName = t.value();
        // 1=1 防止下面的 sql 拼装 and 开头报错
        sql.append("insert into ").append(tableName);
        // 遍历所有字段
        Field[] fields = c.getDeclaredFields();
        for (Field field : fields) {
            // 该属性是否为 @Column 旗下的
            boolean fExists = field.isAnnotationPresent(Column.class);
            if (!fExists) {
                continue;
            }
            // 获取 @Column 中的信息
            Column column = field.getAnnotation(Column.class);
            String columnName = column.value();
            // 获取方法名
            String fieldName = field.getName();
            // getXxx 格式，即属性第一个字母大写
            String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            // 获取 getter
            Method getMethod = c.getMethod(getMethodName); // sql 拼接错就报 NoSuchMethodException
            // 执行 getter，fieldValue 可能为 Integer、String 类型，还是 Object 稳点
            Object fieldValue = getMethod.invoke(entity);
            // 去掉 @Column 但为 null 的属性
            if (fieldValue == null) {
                continue;
            }
            insertField.append(columnName).append(",");
            insertValue.append("'").append(fieldValue).append("'").append(",");
        }
        sql.append("(")
                .append(insertField.substring(0, insertField.length() - 1))
                .append(")")
                .append(" values(")
                .append(insertValue.substring(0, insertValue.length() - 1))
                .append(")");
        return sql.toString();
    }

    public static String updateByEntity(Object entity) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        StringBuilder sql = new StringBuilder();
        StringBuilder condition = new StringBuilder();

        // 获取 class
        Class c = entity.getClass();
        // 该类是否为 @Table 旗下的
        boolean annotationPresent = c.isAnnotationPresent(Table.class);
        if (!annotationPresent) {
            return null;
        }
        // 获取 @Table 中的信息
        Table t = (Table) c.getAnnotation(Table.class);
        String tableName = t.value();
        // 1=1 防止下面的 sql 拼装 and 开头报错
        sql.append("update ").append(tableName);
        // 遍历所有字段
        Field[] fields = c.getDeclaredFields();
        // sql 拼接中是否已经有 set 了
        Boolean hasSetString = false;
        for (Field field : fields) {
            // 该属性是否为 @Column 旗下的
            boolean fExists = field.isAnnotationPresent(Column.class);
            if (!fExists) {
                continue;
            }
            // 获取 @Column 中的信息
            Column column = field.getAnnotation(Column.class);
            String columnName = column.value();
            // 获取方法名
            String fieldName = field.getName();
            // getXxx 格式，即属性第一个字母大写
            String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            // 获取 getter
            Method getMethod = c.getMethod(getMethodName); // sql 拼接错就报 NoSuchMethodException
            // 执行 getter，fieldValue 可能为 Integer、String 类型，还是 Object 稳点
            Object fieldValue = getMethod.invoke(entity);
            // 去掉 @Column 但为 null 的属性
            if (fieldValue == null) {
                continue;
            }
            // id为条件（缺陷：目前只能有一个主键）
            if (field.isAnnotationPresent(Id.class)){
                condition.append(" where ").append(columnName).append("=").append("'").append(fieldValue).append("'");
                continue;
            }
            if (!hasSetString) {
                sql.append(" set ");
                hasSetString = true;
            }
            sql.append(columnName).append("=").append("'").append(fieldValue).append("'").append(",");
        }
        return sql.substring(0,sql.length()-1)+condition;
    }

}
