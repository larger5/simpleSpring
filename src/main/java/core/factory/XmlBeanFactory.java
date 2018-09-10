package core.factory;

import core.Dao.BaseCrud.BaseJdbc;
import core.Dao.ComplexCrud.ComplexJdbc;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

@Data // getter、setter、toString
@AllArgsConstructor // 有参构造函数
@NoArgsConstructor // 无参构造函数
public class XmlBeanFactory {

    private String xmlPath;

    public Object getBean(String bean) throws Exception {

        SAXReader saxReader = new SAXReader();
        // 在项目中相对的是以项目名为根路径
        Document document = saxReader.read(xmlPath);
        Element firstBean = (Element) document.selectSingleNode("//bean[@id='"+bean+"']");
        // 获取类路径
        String className = (String) firstBean.attribute("class").getData();
        // 反射得类实例
        return Class.forName(className).newInstance();
    }

    public BaseJdbc getBaseJdbc()  {

        SAXReader saxReader = new SAXReader();
        // 在项目中相对的是以项目名为根路径
        Document document = null;
        try {
            document = saxReader.read(xmlPath);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        Element driver = (Element) document.selectSingleNode("//jdbc/property[@name='driver']");
        Element url = (Element) document.selectSingleNode("//jdbc/property[@name='url']");
        Element username = (Element) document.selectSingleNode("//jdbc/property[@name='username']");
        Element password = (Element) document.selectSingleNode("//jdbc/property[@name='password']");

        String driverValue = (String) driver.attribute("value").getData();
        String urlValue = (String) url.attribute("value").getData();
        String usernameValue = (String) username.attribute("value").getData();
        String passwordValue = (String) password.attribute("value").getData();

        BaseJdbc baseJdbc = new BaseJdbc();
        baseJdbc.setDriver(driverValue);
        baseJdbc.setUrl(urlValue);
        baseJdbc.setUser(usernameValue);
        baseJdbc.setPassword(passwordValue);

        return baseJdbc;
    }

    public ComplexJdbc getComplexJdbc()  {

        SAXReader saxReader = new SAXReader();
        // 在项目中相对的是以项目名为根路径
        Document document = null;
        try {
            document = saxReader.read(xmlPath);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        Element driver = (Element) document.selectSingleNode("//jdbc/property[@name='driver']");
        Element url = (Element) document.selectSingleNode("//jdbc/property[@name='url']");
        Element username = (Element) document.selectSingleNode("//jdbc/property[@name='username']");
        Element password = (Element) document.selectSingleNode("//jdbc/property[@name='password']");

        String driverValue = (String) driver.attribute("value").getData();
        String urlValue = (String) url.attribute("value").getData();
        String usernameValue = (String) username.attribute("value").getData();
        String passwordValue = (String) password.attribute("value").getData();

        ComplexJdbc complexJdbc = new ComplexJdbc();
        complexJdbc.setDriver(driverValue);
        complexJdbc.setUrl(urlValue);
        complexJdbc.setUser(usernameValue);
        complexJdbc.setPassword(passwordValue);

        return complexJdbc;
    }

}
