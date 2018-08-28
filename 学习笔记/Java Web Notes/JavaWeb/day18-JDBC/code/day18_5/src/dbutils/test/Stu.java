package dbutils.test;

/**
 * @author Administrator
 *
 */
public class Stu {
	private String sname;
	private int age;
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "Stu [sname=" + sname + ", age=" + age + "]";
	}
	public Stu() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Stu(String sname, int age) {
		super();
		this.sname = sname;
		this.age = age;
	}
	
}
