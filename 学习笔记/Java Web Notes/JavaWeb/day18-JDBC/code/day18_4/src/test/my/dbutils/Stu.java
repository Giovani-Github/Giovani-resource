package test.my.dbutils;

/**
 * @author Administrator
 *
 */
public class Stu {
	private String name;
	private int age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "Stu [name=" + name + ", age=" + age + "]";
	}
	public Stu() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Stu(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	
}
