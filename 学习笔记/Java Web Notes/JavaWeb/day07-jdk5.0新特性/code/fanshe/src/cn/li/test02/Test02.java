package cn.li.test02;

import cn.li.test01.Person;

public class Test02 {
	public static void main(String[] args) throws Exception {
		//(1)ʹ��Class.forName("���������ϰ���")�����Person��Calss����
		Class personClass = Class.forName("cn.li.test01.Person");
		//(2)ͨ��Calss�����newInstance()���������Person����ʵ������һ������ͨ�����䣬ʹ��Person���޲����Ĺ��췽��,�൱��new Person();
		Person person = (Person) personClass.newInstance();
		
		person.setName("zhangsan");
		System.out.println(person.getName());
		
	}
}
