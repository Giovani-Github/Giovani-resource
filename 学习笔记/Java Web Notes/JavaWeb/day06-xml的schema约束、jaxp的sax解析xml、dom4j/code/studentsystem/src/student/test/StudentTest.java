package student.test;

import student.service.StudentTool;
import student.vo.Student;

public class StudentTest {
	public static void main(String[] args) {
		//add();
		//remove();
		select();
	}
	
	//add方法，添加学生信息到StudentData.xml
	public static void add() {
		//创建学生类
		Student student = new Student();
		//设置学生信息
		student.setId("03");
		student.setName("wanger");
		student.setAge("21");
		//添加到xml，学生信息文件中
		StudentTool.add(student);
	}
	
	//删除学生信息
	public static void remove() {
		StudentTool.remove("03");
	}
	
	//查询学生信息
	public static void select() {
		Student student = StudentTool.select("01");
		System.out.println("学生id：" + student.getId() + "\n学生姓名：" + student.getName() + "\n学生性别：" + student.getAge() );
	}
}
