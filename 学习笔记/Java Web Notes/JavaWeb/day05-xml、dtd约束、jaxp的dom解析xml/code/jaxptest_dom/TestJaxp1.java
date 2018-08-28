import javax.xml.parsers.*;
import org.w3c.dom.*;
//��ѯ����name�ڵ��ֵ
public class TestJaxp1 {
	public static void main(String[] args) throws Exception{
		//ͨ��DocumentBuilderFactory��ȡDocumentBuilder����
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder buider = factory.newDocumentBuilder();
		//����xml,��ȡDocument����
		Document document = buider.parse("TestJaxp1.xml");
		//��ȡ�ڵ㼯��
		NodeList list = document.getElementsByTagName("name");
		//�����ڵ㼯�ϣ���ȡ�ڵ�
		for(int i=0; i<list.getLength(); i++) {
			//��ýڵ�
			Node node = list.item(i);
			//��ýڵ�����
			String nodeText = node.getTextContent();
			//����ڵ�����
			System.out.println(nodeText);
		}
	}
}
