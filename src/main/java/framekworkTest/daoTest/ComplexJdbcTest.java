package framekworkTest.daoTest;

import core.Dao.ComplexCrud.ComplexJdbc;
import core.factory.XmlBeanFactory;
import framekworkTest.daoTest.entity.User;
import framekworkTest.daoTest.vo.UserDepartment;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public class ComplexJdbcTest {

    private ComplexJdbc complexJdbc = new XmlBeanFactory("src/main/resources/spring.xml").getComplexJdbc();

    /**
     * 借助 DBUtils 实现
     */
    @Test
    public void queryTest() throws InvocationTargetException, NoSuchMethodException, ClassNotFoundException, SQLException, IllegalAccessException {
        User user = new User(1, null, null, null);
        List<User> users = complexJdbc.queryByEntity(user, User.class);
        System.out.println(users);
    }

    /**
     * 多表 + 自定义(传参 + 分页) + ：Vo + sql
     *
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @Test
    public void querySqlVoTest() throws SQLException, ClassNotFoundException {
        String sql = "SELECT t_user.*,t_department.department_name FROM t_user,t_department WHERE t_user.department_id=t_department.id AND t_user.`password`=? AND t_department.department_name=?";
        List<UserDepartment> userDepartments = complexJdbc.queryBySqlVo(UserDepartment.class, sql, 123, 404);
        System.out.println(userDepartments);
    }

    /**
     * 自定义update、delete sql
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @Test
    public void querySqlUpdateTest() throws SQLException, ClassNotFoundException {
        String sql = "update t_user SET `password`=? WHERE id=?";
        Integer integer = complexJdbc.queryBySqlVo(sql, 456, 2);
        System.out.println(integer);
    }

}
