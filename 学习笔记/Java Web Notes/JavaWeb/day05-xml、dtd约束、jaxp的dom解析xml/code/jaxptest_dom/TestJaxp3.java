import javax.xml.parsers.*;
import org.w3c.dom.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

class TestJaxp3 {
	public static void main(String[] args) throws Exception{
		//��������������
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		//���ݽ�������������������
		DocumentBuilder builder = factory.newDocumentBuilder();
		//����xml������document 
		Document document = builder.parse("TestJaxp1.xml");
		//�õ���һ��p1
		NodeList list = document.getElementsByTagName("p1");
		Node p1 = list.item(0);
		//����sex��ǩ createElement
		Element sex = document.createElement("sex");
		//�����ı� createTextNode
		Text sexText = document.createTextNode("dffdf");
		//���ı���ӵ�sex���� appendChild
		sex.appendChild(sexText);
		//��sex��ӵ���һ��p1����
		p1.appendChild(sex);
		
		/*��дxml,ͨ��TransformerFactory���ȡTransformer�࣬ʹ��Transformer��transform()��������дxml*/
		//����TransformerFactory
		TransformerFactory transFactory = TransformerFactory.newInstance();
		//ͨ��TransformerFactory��ȡTransformer
		Transformer trans = transFactory.newTransformer();
		//���ڴ����xmlд���ļ�
		trans.transform(new DOMSource(document),new StreamResult("TestJaxp1.xml"));
	}
}
