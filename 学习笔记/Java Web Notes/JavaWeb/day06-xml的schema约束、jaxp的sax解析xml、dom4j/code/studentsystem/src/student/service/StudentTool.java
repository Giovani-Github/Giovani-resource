package student.service;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;

import student.vo.Student;

public class StudentTool {
	//add方法，添加学生信息到StudentData.xml
	public static void add(Student student) {
		//获得document
		Document document = Dom4jTool.getDocument();
		//得到根节点
		Element root = document.getRootElement();
		//在根节点下面添加stu元素
		Element stu = root.addElement("stu");
		//在stu元素下面依次添加id、name、age元素
		Element id = stu.addElement("id");
		Element name = stu.addElement("name");
		Element age = stu.addElement("age");
		//依次在id，name，age元素下面添加文本内容
		id.setText(student.getId());
		name.setText(student.getName());
		age.setText(student.getAge());
		//回写xml
		Dom4jTool.back(document);
	}
	
	//remove，删除学生信息，根据id删除
	public static void remove(String id) {
		//是否删除成功的变量
		boolean falg = false;
		//得到document
		Document document = Dom4jTool.getDocument();
		//得到根节点
		Element root = document.getRootElement();
		//获取所有id元素
		List<Node> ids = document.selectNodes("//id");
		//遍历要ids
		for(Node stuId : ids) {
			//判断要删除的学生id是否存在，且与ids里面的id值是否相同
			if(stuId.getText().equals(id)) {
				//如果存在，获取这个id的父节点，即包含学生信息的元素
				Element idFu = stuId.getParent();
				//通过根节点删除这个学生的信息
				root.remove(idFu);
			}
		} 
		//回写
		Dom4jTool.back(document);
	}
	
	//select,根据id查询学生信息
	public static Student select(String id) {
		//得到document
		Document document = Dom4jTool.getDocument();
		//获取所有id元素
		List<Node> ids = document.selectNodes("//id");
		//遍历要ids
		for(Node stuId : ids) {
			//判断要查询的学生id是否存在
			if(stuId.getText().equals(id)) {
				//如果存在，获取这个id的父节点，即包含学生信息的元素
				Element idFu = stuId.getParent();
				//通过父节点查询这个学生的信息
				List<Element> stu = idFu.elements();
				//创建学生类
				Student stuData = new Student();
				//依次设置学生信息
				stuData.setId(stu.get(0).getText());
				stuData.setName(stu.get(1).getText());
				stuData.setAge(stu.get(2).getText());
				
				//把学生的类返回
				return stuData;
			}
		}
				
		return null;
	}
}
