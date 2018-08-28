import javax.xml.parsers.*;
import org.w3c.dom.*;

class TestJaxp2 {
	public static void main(String[] args) throws Exception {
		//通过DocumentBuilderFactory获取DocumentBuilder对像
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		//解析xml，获取document对象
		Document document = builder.parse("TestJaxp1.xml");
		//获取name节点集合
		NodeList list = document.getElementsByTagName("name");
		//获取第一个name节点
		Node node = list.item(0);
		//输出第一个name节点内容
		System.out.println(node.getTextContent());
	}
}
