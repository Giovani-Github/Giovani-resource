package cn.li.test02;

import java.lang.reflect.Constructor;

import cn.li.test01.Person;

public class Test03 {

	public static void main(String[] args) throws Exception {
		//(1)ʹ��Class.forName("���������ϰ���")�����Person��Calss����
		Class personClass = Class.forName("cn.li.test01.Person");
		//(2)ʹ��Calss�����getConstructor(Class<?>... parameterTypes)��������ȡ�в������췽���Ķ��󡣴��ݹ��캯���Ĳ����б�����Ĳ������ͣ�����������.calss�ķ�ʽ����
		Constructor cs = personClass.getConstructor(String.class, String.class);
		/*(3)ʹ��Constructor�����newInstance(Object... initargs)��������ȡPerson��ʵ��������Person�Ĺ��캯���Ĳ�����
			�൱�ڣ�new Person("aa","12");*/
		Person person = (Person) cs.newInstance("zhangsan","20");
		
		System.out.println(person.getName() + " " + person.getAge());
		

	}
}
