import java.io.FileOutputStream;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class Dom4jParsingXml_4 {
	public static void main(String[] args) throws Exception {
		//(1)创建解析器 SAXReader
		SAXReader saxReader = new SAXReader();
		//(2)得到document
		Document document = saxReader.read("dom4jParsingXml.xml");
		//(3)得到根节点
		Element root = document.getRootElement();
		//(4)获得第一个p1
		Element p1 = root.element("p1");
		//(5)在p1下面直接添加sex
		Element sex = p1.addElement("sex");
		//(6)在sex下面添加文本
		sex.setText("nan");
		//(7)回写xml XMLWriter类
		OutputFormat format = OutputFormat.createPrettyPrint();
		XMLWriter xmlWriter = new XMLWriter(new FileOutputStream("dom4jParsingXml.xml"), format);
		xmlWriter.write(document);
		//(8)关闭流
		xmlWriter.close();
	}
}
