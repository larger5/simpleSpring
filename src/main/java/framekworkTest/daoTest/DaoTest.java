package framekworkTest.daoTest;

import core.Dao.BaseCrud.BaseJdbc;
import framekworkTest.daoTest.entity.User;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class DaoTest {

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, SQLException, ClassNotFoundException {

        // 方案一
        BaseJdbc baseJdbc = new BaseJdbc("com.mysql.jdbc.Driver",
                "jdbc:mysql://120.79.197.130:3307/testspring?useUnicode=true&characterEncoding=utf-8", "root", "123");

        /* 方案二
        BaseJdbc baseJdbc = new BaseJdbc();
        baseJdbc.setDriver("com.mysql.jdbc.Driver");
        baseJdbc.setUrl("jdbc:mysql://120.79.197.130:3307/testspring?useUnicode=true&characterEncoding=utf-8");
        baseJdbc.setUser("root");
        baseJdbc.setPassword("123");
        */

        /**查
         * 实体中有多少个属性，就有多少个查询的条件
         * 返回类型：list
         */
        User user1 = new User(null, null, "123");
        List<Map<String, Object>> maps = baseJdbc.queryByEntity(user1);
        System.out.println(maps);

        /**删
         * 实体中有多少个属性，就有多少个删除的条件
         * 返回类型：成功 true、失败 false
         */
        User user2 = new User(6, null, null);
        Boolean aBoolean2 = baseJdbc.deleteByEntity(user1);
        System.out.println(aBoolean2);

        /**增
         * 允许实体中有任意个null属性
         * 允许自定义id，但是别和原有的记录冲突
         * 返回类型：成功 true、失败 false
         */
        User user3 = new User(null, "55", "998");
        Boolean aBoolean3 = baseJdbc.insertByEntity(user2);
        System.out.println(aBoolean3);

        /**改
         * 以第一个有 @Id 的为条件，其他的为改，实体中属性值为null的不改
         * 返回类型：成功 true、失败 false
         */
        User user4 = new User(13, "larger", "5555");
        Boolean aBoolean4 = baseJdbc.updateByEntity(user2);
        System.out.println(aBoolean4);
    }

}
