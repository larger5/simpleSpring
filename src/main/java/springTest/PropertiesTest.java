package springTest;

import Factory.PropertiesBeanFactory;
import bean.Person;

/**
 * 比 spring 胜一筹：支持 properties 配置，但功能有限，不支持其他初始化
 */
public class PropertiesTest {

    public static void main(String[] args) throws Exception {

        // 方案一
        PropertiesBeanFactory personFactory = new PropertiesBeanFactory("spring.properties");

        /* 方案二
        PropertiesBeanFactory personFactory = new PropertiesBeanFactory();
        personFactory.setPropertiesPath("spring.properties");
        */

        Person student = (Person) personFactory.getBean("student");
        student.introduce("college");
        /**输出：
         * I am a college student
         */

        Person teacher = (Person) personFactory.getBean("teacher");
        teacher.introduce("Math");
        /**输出：
         * I am a Math teacher
         */

    }

}
