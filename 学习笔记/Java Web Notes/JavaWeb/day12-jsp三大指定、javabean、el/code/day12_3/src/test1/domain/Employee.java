package test1.domain;

/*
 * 员工类
 * */
public class Employee {
	private String name;
	private int salary;//工资
	private Address address;//地址
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "Employee [name=" + name + ", salary=" + salary + ", address="
				+ address + "]";
	}
	
	public String getHehe() {
		return "kkkk";
	}
}
