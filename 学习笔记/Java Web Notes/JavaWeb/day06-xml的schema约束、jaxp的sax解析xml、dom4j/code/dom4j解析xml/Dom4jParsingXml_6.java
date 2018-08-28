import java.io.FileOutputStream;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class Dom4jParsingXml_6 {
	public static void main(String[] args) throws Exception {
		//(1)���������� SAXReader
		SAXReader reader = new SAXReader();
		//(2)�õ�document
		Document document = reader.read("dom4jParsingXml.xml");
		//(3)�õ����ڵ�
		Element root = document.getRootElement();
		//(4)��õ�һ��p1
		Element p1 = root.element("p1");
		//(5)���p1�����age
		Element age = p1.element("age");
		//(6)�޸�age������
		age.setText("2655");
		//(7)��д
		OutputFormat format = OutputFormat.createPrettyPrint();
		XMLWriter xmlWriter = new XMLWriter(new FileOutputStream("dom4jParsingXml.xml"),format);
		xmlWriter.write(document);	
		//(8)�ر���
		xmlWriter.close();
	}
}
