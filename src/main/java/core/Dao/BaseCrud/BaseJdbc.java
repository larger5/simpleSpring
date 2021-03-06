package core.Dao.BaseCrud;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 优点：全是原生代码，出错时一切尽在掌握之内
 * 缺点：
 * ① 即使是 VO 对象也要 @Column、@Table、@Id，显然是不符合现实的，特别是 @Table 是不存在的
 * ② 只能单表 CRUD
 */
@Data // getter、setter、toString
@NoArgsConstructor // 无参构造函数
public class BaseJdbc {

    // 数据库驱动
    private String driver;
    // 数据库 url
    private String url;
    // 数据库库 用户名
    private String user;
    // 数据库 密码
    private String password;
    // 拼接 sql
    private BaseSql baseSql=new BaseSql();

    public List<Map<String, Object>> queryByEntity(Object entity) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, SQLException, ClassNotFoundException {
        String sql = baseSql.queryByEntity(entity);
        System.out.println(sql);
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        ResultSetMetaData rsmd = ps.getMetaData();

        // 取得结果集列数
        int columnCount = rsmd.getColumnCount();

        // 构造泛型结果集
        List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
        Map<String, Object> data = null;
        // 循环结果集
        while (rs.next()) {
            data = new HashMap<String, Object>();
            // 每循环一条将列名和列值存入Map
            for (int i = 1; i <= columnCount; i++) {
                data.put(rsmd.getColumnLabel(i), rs.getObject(rsmd
                        .getColumnLabel(i)));
            }
            // 将整条数据的Map存入到List中
            datas.add(data);
        }
        close(conn);
        return datas;
    }

    public List<Map<String, Object>> queryByEntityAndPage(Object entity,Integer page,Integer size) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, SQLException, ClassNotFoundException {
        String sql = baseSql.queryByEntityAndPage(entity,page,size);
        System.out.println(sql);
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        ResultSetMetaData rsmd = ps.getMetaData();

        // 取得结果集列数
        int columnCount = rsmd.getColumnCount();

        // 构造泛型结果集
        List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
        Map<String, Object> data = null;
        // 循环结果集
        while (rs.next()) {
            data = new HashMap<String, Object>();
            // 每循环一条将列名和列值存入Map
            for (int i = 1; i <= columnCount; i++) {
                data.put(rsmd.getColumnLabel(i), rs.getObject(rsmd
                        .getColumnLabel(i)));
            }
            // 将整条数据的Map存入到List中
            datas.add(data);
        }
        close(conn);
        return datas;
    }

    public Integer countByEntity(Object entity) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, SQLException, ClassNotFoundException {
        String sql = baseSql.countByEntity(entity);
        System.out.println(sql);
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        Integer count=0;
        if(rs.next()) {
            count=rs.getInt(1);
        }
        close(conn);
        return count;
    }

    public Boolean deleteByEntity(Object entity) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, SQLException, ClassNotFoundException {
        String sql = baseSql.deleteByEntity(entity);
        System.out.println(sql);
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        int rs = ps.executeUpdate();
        close(conn);
        if (rs < 0) {
            return false;
        }
        return true;
    }

    public Boolean insertByEntity(Object entity) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, SQLException, ClassNotFoundException {
        String sql = baseSql.insertByEntity(entity);
        System.out.println(sql);
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        int columnCount = ps.executeUpdate();
        if (columnCount < 1) {
            return false;
        }
        return true;
    }

    public Boolean updateByEntity(Object entity) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, SQLException, ClassNotFoundException {
        String sql = baseSql.updateByEntity(entity);
        System.out.println(sql);
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        int columnCount = ps.executeUpdate();
        close(conn);
        if (columnCount < 1) {
            return false;
        }
        return true;
    }

    public Boolean updateByEntityWithAllColumn(Object entity) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, SQLException, ClassNotFoundException {
        String sql = baseSql.updateByEntityWithAllColumn(entity);
        System.out.println(sql);
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        int columnCount = ps.executeUpdate();
        close(conn);
        if (columnCount < 1) {
            return false;
        }
        return true;
    }

    /**
     * 连接
     *
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(url, user, password);
        return conn;
    }

    /**
     * 断开
     *
     * @param conn
     * @throws SQLException
     */
    public void close(Connection conn) throws SQLException {
        if (conn!=null){
            conn.close();
        }
    }

}
