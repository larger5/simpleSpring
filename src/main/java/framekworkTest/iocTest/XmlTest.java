package framekworkTest.iocTest;

import core.factory.XmlBeanFactory;
import framekworkTest.iocTest.bean.impl.Student;
import framekworkTest.iocTest.bean.impl.Teacher;

/**
 * �ɹ�����һ��ɽկ�� spring ���
 */
public class XmlTest {

    public static void main(String[] args) throws Exception {

        // ����һ
        XmlBeanFactory xmlBeanFactory = new XmlBeanFactory("src/main/resources/spring.xml");

        /* ������
        XmlBeanFactory xmlBeanFactory = new XmlBeanFactory();
        xmlBeanFactory.setXmlPath("src/main/resources/spring.xml");
        */

        Student student = (Student) xmlBeanFactory.getBean("student");
        student.introduce("college");
        /** ���
         * I am a college student
         */

        Teacher teacher = (Teacher) xmlBeanFactory.getBean("teacher");
        teacher.introduce("Math");
        /** ���
         * I am a Math teacher
         */
    }
}
