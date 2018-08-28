import java.util.*;

public class Test {
	public static void main(String[] arge) {
		ArrayList al = new ArrayList();

		al.add(new Person("lisi01", 35));	
		al.add(new Person("lisi02", 32));
		al.add(new Person("lisi03", 31));	
		al.add(new Person("lisi03", 31));	
		al.add(new Person("lisi05", 38));	

		//alFor(al);


		al = singleNo(al);

		for(Iterator it = al.iterator(); it.hasNext(); ) {
			Person p = (Person)it.next();
			
			sop(p.getName() + "..." + p.getAge());
		}
	}

	public static void sop(Object obj) {
		System.out.println(obj);
	}
	

	//除去重复元素的方法
	public static ArrayList singleNo(ArrayList al) {
		ArrayList newal = new ArrayList();

		for(Iterator it = al.iterator(); it.hasNext(); ) {
			Object obj = it.next();

			if(!newal.contains(obj)) {
				newal.add(obj);
			}
		}

		return newal;
	}

	public static void alFor(ArrayList al) {
		
		for(Iterator it = al.iterator(); it.hasNext(); ) {
			Person p = (Person)it.next();
			
			sop(p.getName() + "..." + p.getAge());
		}
	}
}


class Person {
	private String name;
	private int age;

	Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public boolean equals(Object obj) {
		if(!(obj instanceof Person)) {
			return false;
		}

		Person p = (Person)obj;

		return this.name.equals(p.name) && this.age == p.age;
	}
}