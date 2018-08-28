package cn.li.test02;

import java.lang.reflect.Method;

import cn.li.test01.Person;

public class Test05 {
	public static void main(String[] args) throws Exception {
		//(1)ʹ��Class.forName("���������ϰ���")�����Person��Calss����
		Class personClass = Class.forName("cn.li.test01.Person");
		//(2)ͨ��Calss�����newInstance()���������Person����ʵ����
		Person person = (Person) personClass.newInstance();
		/*(3)ͨ��Calss�����getDeclaredMethod(String name, Class<?>... parameterTypes)���������setName()�����Ķ���ʵ����Method
			- getDeclaredMethod("setName",String.class)
			- ���setName()��˽�еģ�����ʹ��AccessibleObject��setAccessible(boolean flag)�������ѷ�������Ϊ�������
		*/
		Method setName = personClass.getDeclaredMethod("setName", String.class);
		/*(4)ͨ��Method��invoke(Object obj, Object... args)��������setName����ִ�С�����Person��ʵ����setName�����Ĳ���
			�൱�ڣ�Personʵ��.setName("lisi");
		*/
		setName.invoke(person, "lisi");
		
		System.out.println(person.getName() + " " + person.getAge());
	}
}
