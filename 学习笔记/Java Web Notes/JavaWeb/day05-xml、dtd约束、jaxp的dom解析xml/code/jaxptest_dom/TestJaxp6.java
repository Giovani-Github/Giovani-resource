import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class TestJaxp6 {
	public static void main(String[] args) throws Exception {
		//1����������������
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		//2�����ݽ�������������������
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		//3������xml������document
		Document document = documentBuilder.parse("TestJaxp1.xml");
		//====ʹ�õݹ�ʵ��=====
		//����һ������
		list(document);
	}

	private static void list(Node document) {
		//ֻ��ӡelement���͵Ľڵ㣬��Ϊelement���Ǳ�ǩ
		if(document.getNodeType() == Node.ELEMENT_NODE) {
			System.out.println(document.getNodeName());
		}
		
		//4���õ����ڵ㼯��
		NodeList nodelist = document.getChildNodes();
		//5������nodelist���õ����ڵ�
		for(int i=0; i<nodelist.getLength(); i++) {
			//���ڵ�
			Node node = nodelist.item(i);
			//ʹ�õݹ飬�õ����ڵ���ӽڵ㣬�ӽڵ���ӽڵ�
			list(node);
		}
	}	
}
