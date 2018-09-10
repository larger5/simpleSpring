package core.Dao.ComplexCrud;

import core.Dao.BaseCrud.BaseSql;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Executor;

/**
 * 使用了 DBUtils
 */
@Data // getter、setter、toString
@NoArgsConstructor // 无参构造函数
public class ComplexJdbc {

    // 数据库驱动
    private String driver;
    // 数据库 url
    private String url;
    // 数据库库 用户名
    private String user;
    // 数据库 密码
    private String password;

    // 暂时使用测试接口，后面剔除
    private BaseSql baseSql = new BaseSql();

    private static final QueryRunner QUERY_RUNNER = new QueryRunner();

    public <T> List<T> queryByEntity(Object entity, Class<T> entityClass) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, SQLException, ClassNotFoundException {
        String sql = baseSql.queryByEntity(entity);
        List<T> entityList = QUERY_RUNNER.query(getConnection(), sql, new BeanListHandler<T>(entityClass));
        return entityList;
    }

    public <T> List<T> queryBySqlVo(Class<T> entityClass, String sql,Object ... params) throws SQLException, ClassNotFoundException {
        List<T> list = QUERY_RUNNER.query(getConnection(), sql, new BeanListHandler<T>(entityClass),params);
        return list;
    }

    public Integer queryBySqlVo(String sql,Object ... params) throws SQLException, ClassNotFoundException {
        int execute = QUERY_RUNNER.execute(getConnection(),sql, params);
        return execute;
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
        if (conn != null) {
            conn.close();
        }
    }


}
