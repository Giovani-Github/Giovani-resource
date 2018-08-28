import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Dom4jParsingXml_1 {
	public static void main(String[] args) throws Exception {
		//(1)���������� SAXReader
		SAXReader reader = new SAXReader();
		//(2)�õ�document
		Document document = reader.read("dom4jParsingXml.xml");
		//(3)�õ����ڵ�
		Element root = document.getRootElement();
		//(4)�õ����е�p1��ǩ
		List<Element> p1s = root.elements("p1");
		//(5)����p1s���õ�name
		for (Element p1 : p1s) {
			//�����elment����p1s������ӱ�ǩ
			//�õ�p1�����name��ǩ
			Element name = p1.element("name");
			//(6)�õ�name���ı�����
			System.out.println(name.getText());
		}
	}
}
