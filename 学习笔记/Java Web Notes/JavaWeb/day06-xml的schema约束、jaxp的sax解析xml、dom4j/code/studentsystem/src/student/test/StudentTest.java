package student.test;

import student.service.StudentTool;
import student.vo.Student;

public class StudentTest {
	public static void main(String[] args) {
		//add();
		//remove();
		select();
	}
	
	//add���������ѧ����Ϣ��StudentData.xml
	public static void add() {
		//����ѧ����
		Student student = new Student();
		//����ѧ����Ϣ
		student.setId("03");
		student.setName("wanger");
		student.setAge("21");
		//��ӵ�xml��ѧ����Ϣ�ļ���
		StudentTool.add(student);
	}
	
	//ɾ��ѧ����Ϣ
	public static void remove() {
		StudentTool.remove("03");
	}
	
	//��ѯѧ����Ϣ
	public static void select() {
		Student student = StudentTool.select("01");
		System.out.println("ѧ��id��" + student.getId() + "\nѧ��������" + student.getName() + "\nѧ���Ա�" + student.getAge() );
	}
}
