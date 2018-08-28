package cn.li.test02;

import java.lang.reflect.Constructor;

import cn.li.test01.Person;

public class Test03 {

	public static void main(String[] args) throws Exception {
		//(1)使用Class.forName("类名，带上包名")，获得Person的Calss对象
		Class personClass = Class.forName("cn.li.test01.Person");
		//(2)使用Calss对象的getConstructor(Class<?>... parameterTypes)方法，获取有参数构造方法的对象。传递构造函数的参数列表里面的参数类型，参数类型用.calss的方式传递
		Constructor cs = personClass.getConstructor(String.class, String.class);
		/*(3)使用Constructor对象的newInstance(Object... initargs)方法，获取Person的实例，传递Person的构造函数的参数。
			相当于：new Person("aa","12");*/
		Person person = (Person) cs.newInstance("zhangsan","20");
		
		System.out.println(person.getName() + " " + person.getAge());
		

	}
}
