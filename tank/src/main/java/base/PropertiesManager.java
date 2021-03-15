package base;

import java.io.IOException;
import java.util.Properties;

/**
 * <strong>Description : 配置文件管理</strong><br>
 * <strong>Create on : 2021/3/15 15:51<br>
 * </strong>
 * <p>
 *
 * @author LiXuyang <br>
 * @version <strong> </strong><br>
 * <br>
 * <strong>修改历史:</strong><br>
 * 修改人 | 修改日期 | 修改描述<br>
 * -------------------------------------------<br>
 * <br>
 * <br>
 * @email arnoldlee9527@163.com<br>
 */
public class PropertiesManager {
    static Properties properties = new Properties();

    static {
        try {
            properties.load(PropertiesManager.class.getClassLoader().getResourceAsStream("systemConfig.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object getProperty(String key) {
        if (properties == null) return null;
        
        return properties.getProperty(key);
    }

}
