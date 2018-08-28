package cn.itcast.user.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/*
 * UserDao的工厂类
 * 用来获取UserDao的实现类的实例
 * 1.加载配置文件
 * 2.通过配置文件获取UserDao实现类的名字
 * 3.通过反射创建UserDao实现类的实例
 * 
 * 其中第1步只需在本类加载时执行一次即可，无需重复执行，因为运行后的结果都是一样的
 * */
public class DaoFactory {
	private static Properties pro = new Properties();//配置文件类
	
	static {
		try {
			//1.获取配置文件的流
			InputStream in = DaoFactory.class.getClassLoader()
					.getResourceAsStream("dao.properties");
			//2.加载配置文件
			pro.load(in);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static UserDao getUserDao() {
		//3.通过配置文件获取UserDao的实现类的名称
		String userDaoName = pro.getProperty("cn.itcast.user.dao.UserDao");
		
		try {
			//4.通过UserDao实现类的名称，获取该实现类的Class对象
			Class clazz = Class.forName(userDaoName);
			//5.通过calss对象，创建该实现类的实例
			UserDao userDao = (UserDao)clazz.newInstance();
			
			//5.返回实现类实例
			return userDao;
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
