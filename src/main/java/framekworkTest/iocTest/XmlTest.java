package framekworkTest.iocTest;

import core.factory.XmlBeanFactory;
import framekworkTest.iocTest.bean.impl.Student;
import framekworkTest.iocTest.bean.impl.Teacher;
import org.junit.jupiter.api.Test;

/**
 * ioc ÈÝÆ÷²âÊÔ¡ª¡ªxmlÅäÖÃ°æ
 */
public class XmlTest {

    private XmlBeanFactory xmlBeanFactory = new XmlBeanFactory("src/main/resources/spring.xml");

    @Test
    public void xmlBeanFactoryTest() throws Exception {

        Student student = (Student) xmlBeanFactory.getBean("student");
        student.introduce("college");
        /** Êä³ö
         * I am a college student
         */

        Teacher teacher = (Teacher) xmlBeanFactory.getBean("teacher");
        teacher.introduce("Math");
        /** Êä³ö
         * I am a Math teacher
         */
    }

}
