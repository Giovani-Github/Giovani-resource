import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Dom4jParsingXml_2 {
	public static void main(String[] args) throws Exception {
		//(1)���������� SAXReader
		SAXReader saxReader = new SAXReader();
		//(2)�õ�document
		Document document = saxReader.read("dom4jParsingXml.xml");
		//(3)�õ����ڵ�
		Element root = document.getRootElement();
		//(4)�õ���һ��p1
		Element p1 = root.element("p1");
		//(5)�õ�p1����ĵ�һ��name
		Element name = p1.element("name");
		//(6)�õ�name���ı�����
		System.out.println(name.getText());
	}
}
