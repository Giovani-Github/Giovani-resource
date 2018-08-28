import javax.xml.parsers.*;
import org.w3c.dom.*;

class TestJaxp2 {
	public static void main(String[] args) throws Exception {
		//ͨ��DocumentBuilderFactory��ȡDocumentBuilder����
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		//����xml����ȡdocument����
		Document document = builder.parse("TestJaxp1.xml");
		//��ȡname�ڵ㼯��
		NodeList list = document.getElementsByTagName("name");
		//��ȡ��һ��name�ڵ�
		Node node = list.item(0);
		//�����һ��name�ڵ�����
		System.out.println(node.getTextContent());
	}
}
