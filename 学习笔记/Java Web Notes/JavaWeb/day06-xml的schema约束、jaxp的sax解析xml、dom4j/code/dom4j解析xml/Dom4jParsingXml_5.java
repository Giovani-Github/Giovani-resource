import java.io.FileOutputStream;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class Dom4jParsingXml_5 {
	public static void main(String[] args) throws Exception {
		//(1)���������� SAXReader
		SAXReader saxReader = new SAXReader();
		//(2)�õ�document
		Document document = saxReader.read("dom4jParsingXml.xml");
		//(3)�õ����ڵ�
		Element root = document.getRootElement();
		//(4)�õ���һ��p1
		Element p1 = root.element("p1");
		//(5)���p1�����ȫ���ӽڵ�
		List<Element> elementList = p1.elements();
		//(6)����Ԫ�أ�DocumentHelper.createElement("school");
		Element school = DocumentHelper.createElement("school");
		//(7)����Ԫ���ı�����
		school.setText("schod");
		//(8)ʹ��list��add��������ָ����λ����ӱ�ǩ
		elementList.add(1,school);
		//(9)��д
		OutputFormat format = OutputFormat.createPrettyPrint();
		XMLWriter xmlWriter = new XMLWriter(new FileOutputStream("dom4jParsingXml.xml"),format);
		xmlWriter.write(document);
		//(10)�ر���
		xmlWriter.close();
	}
}
