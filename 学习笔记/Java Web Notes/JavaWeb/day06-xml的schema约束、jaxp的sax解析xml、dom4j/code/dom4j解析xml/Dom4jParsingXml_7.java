import java.io.FileOutputStream;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class Dom4jParsingXml_7 {
	public static void main(String[] args) throws Exception {
		//(1)创建解析器 SAXReader
		SAXReader reader = new SAXReader();
		//(2)得到document
		Document document = reader.read("dom4jParsingXml.xml");
		//(3)得到根节点
		Element root = document.getRootElement();
		//(4)获得第一个p1
		Element p1 = root.element("p1");
		//(5)获得p1下面的school
		Element school = p1.element("school");
		//(6)通过父节点删除school
		p1.remove(school);
		//(7)回写
		OutputFormat format = OutputFormat.createPrettyPrint();
		XMLWriter xmlWriter = new XMLWriter(new FileOutputStream("dom4jParsingXml.xml"),format);
		xmlWriter.write(document);	
		//(8)关闭流
		xmlWriter.close();
	}
}
