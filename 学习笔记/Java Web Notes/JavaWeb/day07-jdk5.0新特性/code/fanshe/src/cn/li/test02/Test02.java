package cn.li.test02;

import cn.li.test01.Person;

public class Test02 {
	public static void main(String[] args) throws Exception {
		//(1)使用Class.forName("类名，带上包名")，获得Person的Calss对象
		Class personClass = Class.forName("cn.li.test01.Person");
		//(2)通过Calss对象的newInstance()方法，获得Person对象实例。这一步就是通过反射，使用Person中无参数的构造方法,相当于new Person();
		Person person = (Person) personClass.newInstance();
		
		person.setName("zhangsan");
		System.out.println(person.getName());
		
	}
}
