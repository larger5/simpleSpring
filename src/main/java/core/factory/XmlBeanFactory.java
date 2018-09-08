package core.factory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dom4j.Document;
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
        Element singleNode = (Element) document.selectSingleNode("//bean[@id='"+bean+"']");
        // 获取类路径
        String className = (String) singleNode.attribute("class").getData();
        // 反射得类实例
        return Class.forName(className).newInstance();

    }


}
