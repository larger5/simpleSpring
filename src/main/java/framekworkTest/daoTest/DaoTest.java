package framekworkTest.daoTest;

import core.Dao.BaseCrud.BaseJdbc;
import core.Dao.BaseCrud.BaseSql;
import framekworkTest.daoTest.entity.User;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class DaoTest {

    private BaseJdbc baseJdbc = new BaseJdbc("com.mysql.jdbc.Driver",
            "jdbc:mysql://120.79.197.130:3307/testspring?useUnicode=true&characterEncoding=utf-8", "root", "123");

    /** 1、查
     * 实体中有多少个属性，就有多少个查询的条件
     * 返回类型：list
     */
    @Test
    public void queryByEntity() throws SQLException, NoSuchMethodException, ClassNotFoundException, IllegalAccessException, InvocationTargetException {
        User user1 = new User(null, null, "123");
        List<Map<String, Object>> maps = baseJdbc.queryByEntity(user1);
        System.out.println(maps);
    }

    /** 2、删
     * 实体中有多少个属性，就有多少个删除的条件
     * 返回类型：成功 true、失败 false
     */
    @Test
    public void deleteByEntity() throws InvocationTargetException, NoSuchMethodException, ClassNotFoundException, SQLException, IllegalAccessException {
        User user2 = new User(6, null, null);
        Boolean aBoolean2 = baseJdbc.deleteByEntity(user2);
        System.out.println(aBoolean2);
    }

    /** 3、增
     * 允许实体中有任意个null属性
     * 允许自定义id，但是别和原有的记录冲突
     * 返回类型：成功 true、失败 false
     */
    @Test
    public void insertByEntity() throws InvocationTargetException, NoSuchMethodException, ClassNotFoundException, SQLException, IllegalAccessException {
        User user3 = new User(null, "55", "998");
        Boolean aBoolean3 = baseJdbc.insertByEntity(user3);
        System.out.println(aBoolean3);
    }

    /** 4、改
     * 以第一个有 @Id 的为条件，其他的为改，实体中属性值为null的不改
     * 返回类型：成功 true、失败 false
     */
    @Test
    public void updateByEntity() throws InvocationTargetException, NoSuchMethodException, ClassNotFoundException, SQLException, IllegalAccessException {
        User user4 = new User(13, "larger", "5555");
        Boolean aBoolean4 = baseJdbc.updateByEntity(user4);
        System.out.println(aBoolean4);
    }

    /** 5、改
     * 用于置空记录中的一些字段
     * update t_user set user_name=null,password='123' where id='12'
     */
    @Test
    public void updateByEntityWithAllColumnTest() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, SQLException, ClassNotFoundException {
        User user5 = new User(12, null, "123");
        Boolean aBoolean = baseJdbc.updateByEntityWithAllColumn(user5);
        System.out.println(aBoolean);
    }

}
