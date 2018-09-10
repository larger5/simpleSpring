package framekworkTest.daoTest;

import core.Dao.BaseCrud.BaseJdbc;
import core.factory.XmlBeanFactory;
import framekworkTest.daoTest.entity.User;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class BaseJdbcTest {

    private BaseJdbc baseJdbc = new XmlBeanFactory("src/main/resources/spring.xml").getBaseJdbc();

    /**
     * 1.1、查
     * 实体中有多少个属性，就有多少个查询的条件
     * select * from t_user w
     * 返回类型：listhere 1=1 and password='123'
     */
    @Test
    public void queryByEntity() throws SQLException, NoSuchMethodException, ClassNotFoundException, IllegalAccessException, InvocationTargetException {
        User user1 = new User(null, "larger5", "123",null);
        List<Map<String, Object>> maps = baseJdbc.queryByEntity(user1);
        System.out.println(maps);
    }

    /**
     * 1.2、查 + 分页
     * 实体中有多少个属性，就有多少个查询的条件
     * select * from t_user where 1=1 and password='123' limit 0,2
     * 返回类型：list
     */
    @Test
    public void queryByEntityAndPage() throws SQLException, NoSuchMethodException, ClassNotFoundException, IllegalAccessException, InvocationTargetException {
        User user1 = new User(null, null, "123",null);
        List<Map<String, Object>> maps = baseJdbc.queryByEntityAndPage(user1, 1, 2);
        System.out.println(maps);
    }

    /**
     * 1.3、查数量
     * 配合分页使用，返回给前端
     * 实体中有多少个属性，就有多少个查询的条件
     * select count(*) from t_user where 1=1 and user_name='larger5' and password='123'
     * 返回类型：list
     */
    @Test
    public void countByEntity() throws SQLException, NoSuchMethodException, ClassNotFoundException, IllegalAccessException, InvocationTargetException {
        User user1 = new User(null, "larger5", "123",null);
        Integer count = baseJdbc.countByEntity(user1);
        System.out.println(count);
    }

    /**
     * 2、删
     * 实体中有多少个属性，就有多少个删除的条件
     * delete from t_user where 1=1 and id='6'
     * 返回类型：成功 true、失败 false
     */
    @Test
    public void deleteByEntity() throws InvocationTargetException, NoSuchMethodException, ClassNotFoundException, SQLException, IllegalAccessException {
        User user2 = new User(6, null, null,null);
        Boolean aBoolean2 = baseJdbc.deleteByEntity(user2);
        System.out.println(aBoolean2);
    }

    /**
     * 3、增
     * 允许实体中有任意个null属性
     * 允许自定义id，但是别和原有的记录冲突
     * insert into t_user(user_name,password) values('55','998')
     * 返回类型：成功 true、失败 false
     */
    @Test
    public void insertByEntity() throws InvocationTargetException, NoSuchMethodException, ClassNotFoundException, SQLException, IllegalAccessException {
        User user3 = new User(null, "55", "998",null);
        Boolean aBoolean3 = baseJdbc.insertByEntity(user3);
        System.out.println(aBoolean3);
    }

    /**
     * 4.1、改
     * 以第一个有 @Id 的为条件，其他的为改，实体中属性值为null的不改
     * update t_user set user_name='larger',password='5555' where id='13'
     * 返回类型：成功 true、失败 false
     */
    @Test
    public void updateByEntity() throws InvocationTargetException, NoSuchMethodException, ClassNotFoundException, SQLException, IllegalAccessException {
        User user4 = new User(13, "larger", "5555",null);
        Boolean aBoolean4 = baseJdbc.updateByEntity(user4);
        System.out.println(aBoolean4);
    }

    /**
     * 4.2、改
     * 用于置空记录中的一些字段
     * update t_user set user_name=null,password='123' where id='12'
     */
    @Test
    public void updateByEntityWithAllColumnTest() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, SQLException, ClassNotFoundException {
        User user5 = new User(12, null, "123",null);
        Boolean aBoolean = baseJdbc.updateByEntityWithAllColumn(user5);
        System.out.println(aBoolean);
    }

}
