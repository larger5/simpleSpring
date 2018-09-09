package framekworkTest.iocTest;

import core.factory.PropertiesBeanFactory;
import framekworkTest.iocTest.bean.Person;
import org.junit.jupiter.api.Test;

/**
 * ioc 容器测试——properties配置版
 */
public class PropertiesTest {

    private PropertiesBeanFactory propertiesFactory = new PropertiesBeanFactory("spring.properties");

    @Test
    public void propertiesFactoryTest() throws Exception {
        Person student = (Person) propertiesFactory.getBean("student");
        student.introduce("college");
        /**输出：
         * I am a college student

         */
        Person teacher = (Person) propertiesFactory.getBean("teacher");
        teacher.introduce("Math");
        /**输出：
         * I am a Math teacher
         */
    }

}
