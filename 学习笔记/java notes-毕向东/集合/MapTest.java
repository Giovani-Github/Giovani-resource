/*
每一个学生都有对应的归属地。
学生Student，地址String。
学生属性：姓名，年龄。
注意：姓名和年龄相同的视为同一个学生。
保证学生的唯一性。



1，描述学生。

2，定义map容器。将学生作为键，地址作为值。存入。

3，获取map集合中的元素。

*/


import java.util.*;
public class MapTest {
	public static void main(String[] arge) {
		HashMap<Student, String> hs = new HashMap<Student, String>();

		hs.put(new Student("li01", 12), "广东");		
		hs.put(new Student("li01", 2), "广东");	
		hs.put(new Student("li02", 11), "广南");
		hs.put(new Student("li03", 10), "广西");
		
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