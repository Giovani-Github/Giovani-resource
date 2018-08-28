package cn.li.test02;

import java.lang.reflect.Field;

import cn.li.test01.Person;

public class Test04 {
	public static void main(String[] args) throws Exception {
		//(1)ʹ��Class.forName("���������ϰ���")�����Person��Calss����]
		Class personClass = Class.forName("cn.li.test01.Person");
		//(2)ͨ��Calss�����newInstance()���������Person����ʵ����
		Person person = (Person) personClass.newInstance();
		/*(3)ͨ��Calss�����getDeclaredField(String name)��ȡage���ԵĶ���Field��
			���age������˽�еģ�����ʹ��AccessibleObject��setAccessible(boolean flag)����������������Ϊ�������
		*/
		Field age = personClass.getDeclaredField("age");
		age.setAccessible(true);
		
		/*(4)ͨ��Field�����set(Object obj, Object value)������age���Ե�ֵ������Person��ʵ����age���Ե���ֵ��
			�൱��:personʵ��.age=value;*/
		age.set(person, "34");
		
		System.out.println(person.getName() + "" + person.getAge());
	}
}
