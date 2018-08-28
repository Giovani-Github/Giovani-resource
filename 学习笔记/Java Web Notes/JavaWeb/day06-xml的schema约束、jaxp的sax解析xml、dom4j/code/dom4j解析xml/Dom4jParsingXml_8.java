import java.io.FileOutputStream;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class Dom4jParsingXml_8 {
	public static void main(String[] args) throws Exception {
		//(1)���������� SAXReader
		SAXReader reader = new SAXReader();
		//(2)�õ�document
		Document document = reader.read("dom4jParsingXml.xml");
		//(3)�õ����ڵ�
		Element root = document.getRootElement();
		//(4)��õ�һ��p1
		Element p1 = root.element("p1");
		//(5)��ȡid1����ֵ��attributeValue()
		System.out.println(p1.attributeValue("id1"));
	}
}
