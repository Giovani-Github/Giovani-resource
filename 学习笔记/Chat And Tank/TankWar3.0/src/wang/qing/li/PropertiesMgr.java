package wang.qing.li;

import java.io.IOException;
import java.util.Properties;
/**
 * 配置文件操作类
 * @author 李庆旺
 *
 */
public class PropertiesMgr {
	static Properties prop = new Properties();
	
	//这个类第一次加载进内存时自动调用
	static {
		try {
			//拿到读取tank.properties的流
			prop.load(PropertiesMgr.class.getClassLoader().getResourceAsStream("config/tank.properties"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	//禁止new对象
	private PropertiesMgr() {};
	
	public static String getProperty(String key) {
		return prop.getProperty(key);
	}
}
