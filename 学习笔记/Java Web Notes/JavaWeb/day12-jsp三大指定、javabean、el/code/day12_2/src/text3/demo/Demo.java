package text3.demo;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import text2.domain.User;
import tools.CommonUtils;

public class Demo {
	/*
	 * 使用內省操作test1.Person类，修改它的属性值
	 * 使用封装好的工具包commons-beanutils-1.8.3.jar里的BeanUtils类来操作，这个类依赖内省
	 * */
	@Test
	public void fun1() throws Exception {
		/*
		 * 1.首先使用反射的到person类
		 * */
		String className = "test1.Person";
		Class clazz = Class.forName(className);
		Object bean = clazz.newInstance();
		
		/*
		 * 使用封装好的工具包commons-beanutils-1.8.3.jar里的BeanUtils类来操作,修改person类的成员变量值
		 * 这个BeanUtils内部使用的是内省
		 * */
		//意思是，修改指定bean的指定属性，属性值修改为指定值
		BeanUtils.setProperty(bean, "name", "张三");
		BeanUtils.setProperty(bean, "age", "23");//这里，他会自动把23转换为int类型
		
		//获取指定bean的指定属性，返回指定的属性值
		String age = BeanUtils.getProperty(bean, "age");
		System.out.println(age);
		
		System.out.println(bean);
		
	}
	
	/*
	 * 把map中的属性值封装到一个bean中，这个bean是User类
	 * 使用BeanUtils.populate(bean, map);方法 ，有个要求：就是map的key必须与bean的属性名相同
	 * */
	@Test
	public void fun2() throws Exception {
		/*
		 * 1.创建map
		 * 2.创建user
		 * 3.使用BeanUtils.populate(bean, map)
		 * */
		Map<String,String> map = new HashMap<String,String>();
		map.put("name", "zahngsan");
		
		User user  = new User();
		
		BeanUtils.populate(user, map);
		
		System.out.println(user);
	}
	
	/*
	 * 也可以通过我们自己写的一个工具类CommonUtils来实现把map中的属性值封装到一个bean中
	 * */
	@Test
	public void fun3() {
		/*
		 * 创建map对象
		 * */
		Map<String,String> map = new HashMap<String,String>();
		map.put("name", "lisi");
		map.put("age","13");
		
		User user = CommonUtils.toBean(map, User.class);
		System.out.println(user);
	}
}
