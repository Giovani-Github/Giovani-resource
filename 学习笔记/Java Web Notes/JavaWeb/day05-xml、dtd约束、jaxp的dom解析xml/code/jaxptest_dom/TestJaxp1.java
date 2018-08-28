import javax.xml.parsers.*;
import org.w3c.dom.*;
//查询所有name节点的值
public class TestJaxp1 {
	public static void main(String[] args) throws Exception{
		//通过DocumentBuilderFactory获取DocumentBuilder对像
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder buider = factory.newDocumentBuilder();
		//解析xml,获取Document对象
		Document document = buider.parse("TestJaxp1.xml");
		//获取节点集合
		NodeList list = document.getElementsByTagName("name");
		//遍历节点集合，获取节点
		for(int i=0; i<list.getLength(); i++) {
			//获得节点
			Node node = list.item(i);
			//获得节点内容
			String nodeText = node.getTextContent();
			//输出节点内容
			System.out.println(nodeText);
		}
	}
}
