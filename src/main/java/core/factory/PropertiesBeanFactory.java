package core.factory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.InputStream;
import java.util.Properties;

@Data // getter、setter、toString
@AllArgsConstructor // 有参构造函数
@NoArgsConstructor // 无参构造函数
//@Deprecated // Java原生：表明此类过时
public class PropertiesBeanFactory {

    private String propertiesPath;

    public Object getBean(String bean) throws Exception {

        Properties properties = new Properties();
        // 使用ClassLoader加载properties配置文件生成对应的输入流
        InputStream in = PropertiesBeanFactory.class.getClassLoader().getResourceAsStream(propertiesPath);
        // 使用properties对象加载输入流
        properties.load(in);
        //获取key对应的value值
        String property = properties.getProperty(bean);
        return Class.forName(property).newInstance();
    }

}
