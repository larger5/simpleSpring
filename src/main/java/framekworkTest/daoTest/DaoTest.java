package framekworkTest.daoTest;

import core.Dao.BaseCrud.BaseJdbc;
import core.Dao.BaseCrud.BaseSql;
import framekworkTest.daoTest.entity.User;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class DaoTest {

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, SQLException, ClassNotFoundException {

        BaseJdbc baseJdbc = new BaseJdbc("com.mysql.jdbc.Driver",
                "jdbc:mysql://120.79.197.120:3307/testspring?useUnicode=true&characterEncoding=utf-8", "root", "123");


        // 查
        User user1 = new User(null, null, "123");
        List<Map<String, Object>> maps = baseJdbc.queryByEntity(user1);
        System.out.println(maps);

        // 删
        User user2 = new User(6, null, null);
        Boolean aBoolean2 = baseJdbc.deleteByEntity(user1);
        System.out.println(aBoolean2);

        // 增
        User user3 = new User(null, "55", "998");
        Boolean aBoolean3 = baseJdbc.insertByEntity(user2);
        System.out.println(aBoolean3);

        // 改
        User user4 = new User(13, "larger", "5555");
        Boolean aBoolean4 = baseJdbc.updateByEntity(user2);
        System.out.println(aBoolean4);

    }

}
