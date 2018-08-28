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
		//��������������
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		//���ݽ�������������������
		DocumentBuilder builder = builderFactory.newDocumentBuilder();
		//����xml������document 
		Document document = builder.parse("TestJaxp1.xml");
		//�õ�sex
		NodeList list = document.getElementsByTagName("sex");
		Node sex = list.item(0);
		//�޸�sex��������� 
		sex.setTextContent("nan");
		//��д
		//����TransformerFactory
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		//����transform
		Transformer transformer = transformerFactory.newTransformer();
		//���ڴ��е�xmlд���ļ�
		transformer.transform(new DOMSource(document), new StreamResult("TestJaxp1.xml"));
	}
}
