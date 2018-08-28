import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class TestJaxp4 {
	public static void main(String[] args) throws Exception {
		//创建解析器工厂
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		//根据解析器工厂创建解析器
		DocumentBuilder builder = builderFactory.newDocumentBuilder();
		//解析xml，返回document 
		Document document = builder.parse("TestJaxp1.xml");
		//得到sex
		NodeList list = document.getElementsByTagName("sex");
		Node sex = list.item(0);
		//修改sex里面的内容 
		sex.setTextContent("nan");
		//回写
		//创建TransformerFactory
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		//创建transform
		Transformer transformer = transformerFactory.newTransformer();
		//把内存中的xml写入文件
		transformer.transform(new DOMSource(document), new StreamResult("TestJaxp1.xml"));
	}
}
