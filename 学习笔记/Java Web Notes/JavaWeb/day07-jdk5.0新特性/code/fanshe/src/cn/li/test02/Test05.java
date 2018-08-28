package cn.li.test02;

import java.lang.reflect.Method;

import cn.li.test01.Person;

public class Test05 {
	public static void main(String[] args) throws Exception {
		//(1)使用Class.forName("类名，带上包名")，获得Person的Calss对象
		Class personClass = Class.forName("cn.li.test01.Person");
		//(2)通过Calss对象的newInstance()方法，获得Person对象实例。
		Person person = (Person) personClass.newInstance();
		/*(3)通过Calss对象的getDeclaredMethod(String name, Class<?>... parameterTypes)方法，获得setName()方法的对象实例，Method
			- getDeclaredMethod("setName",String.class)
			- 如果setName()是私有的，可以使用AccessibleObject的setAccessible(boolean flag)方法，把方法设置为允许访问
		*/
		Method setName = personClass.getDeclaredMethod("setName", String.class);
		/*(4)通过Method的invoke(Object obj, Object... args)方法，让setName方法执行。传递Person的实例和setName方法的参数
			相当于：Person实例.setName("lisi");
		*/
		setName.invoke(person, "lisi");
		
		System.out.println(person.getName() + " " + person.getAge());
	}
}
