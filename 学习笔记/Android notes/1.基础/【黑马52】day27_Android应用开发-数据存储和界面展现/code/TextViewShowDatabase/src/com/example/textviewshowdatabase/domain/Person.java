package com.example.textviewshowdatabase.domain;

/**
 * 对应数据库的person表
 * @author Administrator
 */
/**
 * @author Administrator
 *
 */
public class Person {
	//因为SQLite数据库里面的数据类型都是String，所以都定义为String
	private String _id;
	private String name;
	private String phone;
	private String sal;
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getSal() {
		return sal;
	}
	public void setSal(String sal) {
		this.sal = sal;
	}
	@Override
	public String toString() {
		return name + "," + phone + "," + sal;
	}
	public Person(String _id, String name, String phone, String sal) {
		super();
		this._id = _id;
		this.name = name;
		this.phone = phone;
		this.sal = sal;
	}
	
	
}
