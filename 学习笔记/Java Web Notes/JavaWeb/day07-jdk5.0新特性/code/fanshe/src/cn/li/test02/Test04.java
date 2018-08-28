package cn.li.test02;

import java.lang.reflect.Field;

import cn.li.test01.Person;

public class Test04 {
	public static void main(String[] args) throws Exception {
		//(1)使用Class.forName("类名，带上包名")，获得Person的Calss对象]
		Class personClass = Class.forName("cn.li.test01.Person");
		//(2)通过Calss对象的newInstance()方法，获得Person对象实例。
		Person person = (Person) personClass.newInstance();
		/*(3)通过Calss对象的getDeclaredField(String name)获取age属性的对象，Field。
			如果age属性是私有的，可以使用AccessibleObject的setAccessible(boolean flag)方法，把属性设置为允许访问
		*/
		Field age = personClass.getDeclaredField("age");
		age.setAccessible(true);
		
		/*(4)通过Field对象的set(Object obj, Object value)，设置age属性的值。传递Person的实例和age属性的新值。
			相当于:person实例.age=value;*/
		age.set(person, "34");
		
		System.out.println(person.getName() + "" + person.getAge());
	}
}
