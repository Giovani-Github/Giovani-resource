import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class TestJaxp6 {
	public static void main(String[] args) throws Exception {
		//1、创建解析器工厂
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		//2、根据解析器工厂创建解析器
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		//3、解析xml，返回document
		Document document = documentBuilder.parse("TestJaxp1.xml");
		//====使用递归实现=====
		//用另一个方法
		list(document);
	}

	private static void list(Node document) {
		//只打印element类型的节点，因为element才是标签
		if(document.getNodeType() == Node.ELEMENT_NODE) {
			System.out.println(document.getNodeName());
		}
		
		//4、得到根节点集合
		NodeList nodelist = document.getChildNodes();
		//5、遍历nodelist，得到根节点
		for(int i=0; i<nodelist.getLength(); i++) {
			//根节点
			Node node = nodelist.item(i);
			//使用递归，得到根节点的子节点，子节点的子节点
			list(node);
		}
	}	
}
