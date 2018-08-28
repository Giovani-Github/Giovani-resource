import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Dom4jParsingXml_3 {
	public static void main(String[] args) throws Exception {
		//(1)���������� SAXReader
		SAXReader saxReader = new SAXReader();
		//(2)�õ�document
		Document document = saxReader.read("dom4jParsingXml.xml");
		//(3)�õ����ڵ�
		Element root = document.getRootElement();
		//(4)�õ�����p1
		List<Element> p1s = root.elements("p1");
		//(5)�õ��ڶ���p1
		Element p1 = p1s.get(1);
		//(6)�õ��ڶ���p1�����name
		Element name = p1.element("name");
		//(7)�õ�name���ı�����
		System.out.println(name.getText());
	}
}
