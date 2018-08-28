package student.service;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;

import student.vo.Student;

public class StudentTool {
	//add���������ѧ����Ϣ��StudentData.xml
	public static void add(Student student) {
		//���document
		Document document = Dom4jTool.getDocument();
		//�õ����ڵ�
		Element root = document.getRootElement();
		//�ڸ��ڵ��������stuԪ��
		Element stu = root.addElement("stu");
		//��stuԪ�������������id��name��ageԪ��
		Element id = stu.addElement("id");
		Element name = stu.addElement("name");
		Element age = stu.addElement("age");
		//������id��name��ageԪ����������ı�����
		id.setText(student.getId());
		name.setText(student.getName());
		age.setText(student.getAge());
		//��дxml
		Dom4jTool.back(document);
	}
	
	//remove��ɾ��ѧ����Ϣ������idɾ��
	public static void remove(String id) {
		//�Ƿ�ɾ���ɹ��ı���
		boolean falg = false;
		//�õ�document
		Document document = Dom4jTool.getDocument();
		//�õ����ڵ�
		Element root = document.getRootElement();
		//��ȡ����idԪ��
		List<Node> ids = document.selectNodes("//id");
		//����Ҫids
		for(Node stuId : ids) {
			//�ж�Ҫɾ����ѧ��id�Ƿ���ڣ�����ids�����idֵ�Ƿ���ͬ
			if(stuId.getText().equals(id)) {
				//������ڣ���ȡ���id�ĸ��ڵ㣬������ѧ����Ϣ��Ԫ��
				Element idFu = stuId.getParent();
				//ͨ�����ڵ�ɾ�����ѧ������Ϣ
				root.remove(idFu);
			}
		} 
		//��д
		Dom4jTool.back(document);
	}
	
	//select,����id��ѯѧ����Ϣ
	public static Student select(String id) {
		//�õ�document
		Document document = Dom4jTool.getDocument();
		//��ȡ����idԪ��
		List<Node> ids = document.selectNodes("//id");
		//����Ҫids
		for(Node stuId : ids) {
			//�ж�Ҫ��ѯ��ѧ��id�Ƿ����
			if(stuId.getText().equals(id)) {
				//������ڣ���ȡ���id�ĸ��ڵ㣬������ѧ����Ϣ��Ԫ��
				Element idFu = stuId.getParent();
				//ͨ�����ڵ��ѯ���ѧ������Ϣ
				List<Element> stu = idFu.elements();
				//����ѧ����
				Student stuData = new Student();
				//��������ѧ����Ϣ
				stuData.setId(stu.get(0).getText());
				stuData.setName(stu.get(1).getText());
				stuData.setAge(stu.get(2).getText());
				
				//��ѧ�����෵��
				return stuData;
			}
		}
				
		return null;
	}
}
