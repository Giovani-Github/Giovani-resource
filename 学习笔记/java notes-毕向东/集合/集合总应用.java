import java.util.*;

public class Test {
	public static void main(String[] arge) {
		ListDemo ld = new ListDemo();
		ld.arrayList();
		ld.linkedList();
		ld.vector();
		
		SetDemo st = new SetDemo();
		st.hashSet();
		st.treeSet();
	}
}


class Print {
	public void print(Collection ct) {
		for(Iterator it = ct.iterator(); it.hasNext(); ) {
			Person p = (Person)it.next();

			sop(p.getName() + "......." + p.getAge());
		}
	}

	public void sop(Object obj) {
		System.out.println(obj);
	}
}


class ListDemo {
	private Print p = new Print();

	public void arrayList() {
		ArrayList al = new ArrayList();

		al.add(new Person("lisi01", 14));
		al.add(new Person("lisi02", 13));
		al.add(new Person("lisi02", 13));
		al.add(new Person("lisi04", 11));
		
		p.print(al);

		p.sop("\n");
	}

	public void linkedList() {
		LinkedList al = new LinkedList();

		al.add(new Person("lisi01", 14));
		al.add(new Person("lisi02", 13));
		al.add(new Person("lisi02", 13));
		al.add(new Person("lisi04", 11));
		
		p.print(al);

		p.sop("\n");
	
	}

	public void vector() {
		Vector al = new Vector();

		al.add(new Person("lisi01", 14));
		al.add(new Person("lisi02", 13));
		al.add(new Person("lisi02", 13));
		al.add(new Person("lisi04", 11));
		
		p.print(al);

		p.sop("\n");
		
	}

	public ArrayList aNoB(ArrayList l) {
		ArrayList lt = new ArrayList();

		for(Iterator it = l.iterator(); it.hasNext(); ) {
			Object obj = it.next();

			if(!lt.contains(obj)) {
				lt.add(obj);
			}
		}

		return lt;
	}
}


class SetDemo {
	private Print p = new Print();

	public void hashSet() {
		HashSet al = new HashSet();

		al.add(new Person("lisi01", 14));
		al.add(new Person("lisi02", 13));
		al.add(new Person("lisi02", 13));
		al.add(new Person("lisi04", 11));
		
		p.print(al);

		p.sop("\n");
	}

	public void treeSet() {
		TreeSet al = new TreeSet();

		al.add(new Person("lisi01", 14));
		al.add(new Person("lisi02", 13));
		al.add(new Person("lisi02", 13));
		al.add(new Person("lisi04", 11));
		
		p.print(al);

		p.sop("\n");
	}
}


class Person implements Comparable {
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

	public int compareTo(Object obj) {
		if(!(obj instanceof Person)) {
			try	{
				throw new Exception("没有可比性！");
			} catch (Exception e) {

			}
		}
		
		Person p = (Person)obj;
		if(this.age > p.age) {
			return 1;
		} else if(this.age < p.age) {
			return -1;
		} else {
			return this.name.compareTo(p.name);
		}
	}

	public int hashCode() {
		return name.hashCode() + age*13;
	}
}