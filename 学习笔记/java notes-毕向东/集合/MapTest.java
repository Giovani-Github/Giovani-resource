/*
ÿһ��ѧ�����ж�Ӧ�Ĺ����ء�
ѧ��Student����ַString��
ѧ�����ԣ����������䡣
ע�⣺������������ͬ����Ϊͬһ��ѧ����
��֤ѧ����Ψһ�ԡ�



1������ѧ����

2������map��������ѧ����Ϊ������ַ��Ϊֵ�����롣

3����ȡmap�����е�Ԫ�ء�

*/


import java.util.*;
public class MapTest {
	public static void main(String[] arge) {
		HashMap<Student, String> hs = new HashMap<Student, String>();

		hs.put(new Student("li01", 12), "�㶫");		
		hs.put(new Student("li01", 2), "�㶫");	
		hs.put(new Student("li02", 11), "����");
		hs.put(new Student("li03", 10), "����");
		
		Set<Map.Entry<Student, String>> st = hs.entrySet();

		for(Iterator<Map.Entry<Student, String>> it = st.iterator(); it.hasNext(); ) {
			Map.Entry<Student, String> me = it.next();

			Student key = me.getKey();
			String value = me.getValue();

			System.out.println("key: " + key + "  " + "value:" + value);
		}
	}
}


class Student implements Comparable<Student> {
	private String name;
	private int age;
	
	Student(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public int hashCode() {
		return name.hashCode() + age*13;
	}

	public boolean equals(Object obj) {
		if(!(obj instanceof Student))
			return false;
		
		Student sd = (Student)obj;
		return this.name.equals(sd.name) && this.age == sd.age;
	}

	public int compareTo(Student st) {
		int num = new Integer(this.getAge()).compareTo(new Integer(st.getAge()));

		if(num == 0)
			return this.getName().compareTo(st.getName());

		return num;
	}

	public String toString() {
		return "name:" + name + "  age:" + age;
	}
}