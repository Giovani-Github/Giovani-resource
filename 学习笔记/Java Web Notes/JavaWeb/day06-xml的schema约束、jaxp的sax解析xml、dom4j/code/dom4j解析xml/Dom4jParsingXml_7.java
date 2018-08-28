import java.io.FileOutputStream;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class Dom4jParsingXml_7 {
	public static void main(String[] args) throws Exception {
		//(1)���������� SAXReader
		SAXReader reader = new SAXReader();
		//(2)�õ�document
		Document document = reader.read("dom4jParsingXml.xml");
		//(3)�õ����ڵ�
		Element root = document.getRootElement();
		//(4)��õ�һ��p1
		Element p1 = root.element("p1");
		//(5)���p1�����school
		Element school = p1.element("school");
		//(6)ͨ�����ڵ�ɾ��school
		p1.remove(school);
		//(7)��д
		OutputFormat format = OutputFormat.createPrettyPrint();
		XMLWriter xmlWriter = new XMLWriter(new FileOutputStream("dom4jParsingXml.xml"),format);
		xmlWriter.write(document);	
		//(8)�ر���
		xmlWriter.close();
	}
}
