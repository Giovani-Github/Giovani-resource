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
		//1����������������
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		//2�����ݽ�������������������
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		//3������xml������document
		Document document = documentBuilder.parse("TestJaxp1.xml");
		//4����ȡsexԪ��
		NodeList list = document.getElementsByTagName("sex");
		Node sex = list.item(0);
		//5����ȡsex�ĸ��ڵ� 
		Node p1 = sex.getParentNode();
		//6��ɾ��ʹ�ø��ڵ�ɾ�� removeChild����
		p1.removeChild(sex);
		//7����дxml
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.transform(new DOMSource(document), new StreamResult("TestJaxp1.xml"));
	}
}
