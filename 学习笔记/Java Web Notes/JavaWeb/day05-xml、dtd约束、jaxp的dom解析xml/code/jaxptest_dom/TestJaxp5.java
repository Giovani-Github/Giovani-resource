import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class TestJaxp5 {
	public static void main(String[] args) throws Exception {
		//1、创建解析器工厂
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		//2、根据解析器工厂创建解析器
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		//3、解析xml，返回document
		Document document = documentBuilder.parse("TestJaxp1.xml");
		//4、获取sex元素
		NodeList list = document.getElementsByTagName("sex");
		Node sex = list.item(0);
		//5、获取sex的父节点 
		Node p1 = sex.getParentNode();
		//6、删除使用父节点删除 removeChild方法
		p1.removeChild(sex);
		//7、回写xml
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.transform(new DOMSource(document), new StreamResult("TestJaxp1.xml"));
	}
}
