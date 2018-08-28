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
		//(1)创建解析器 SAXReader
		SAXReader saxReader = new SAXReader();
		//(2)得到document
		Document document = saxReader.read("dom4jParsingXml.xml");
		//(3)得到根节点
		Element root = document.getRootElement();
		//(4)得到第一个p1
		Element p1 = root.element("p1");
		//(5)获得p1下面的全部子节点
		List<Element> elementList = p1.elements();
		//(6)创建元素：DocumentHelper.createElement("school");
		Element school = DocumentHelper.createElement("school");
		//(7)设置元素文本内容
		school.setText("schod");
		//(8)使用list的add方法，子指定的位置添加标签
		elementList.add(1,school);
		//(9)回写
		OutputFormat format = OutputFormat.createPrettyPrint();
		XMLWriter xmlWriter = new XMLWriter(new FileOutputStream("dom4jParsingXml.xml"),format);
		xmlWriter.write(document);
		//(10)关闭流
		xmlWriter.close();
	}
}
