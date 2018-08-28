import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Dom4jParsingXml_3 {
	public static void main(String[] args) throws Exception {
		//(1)创建解析器 SAXReader
		SAXReader saxReader = new SAXReader();
		//(2)得到document
		Document document = saxReader.read("dom4jParsingXml.xml");
		//(3)得到根节点
		Element root = document.getRootElement();
		//(4)得到所有p1
		List<Element> p1s = root.elements("p1");
		//(5)得到第二个p1
		Element p1 = p1s.get(1);
		//(6)得到第二个p1里面的name
		Element name = p1.element("name");
		//(7)得到name的文本内容
		System.out.println(name.getText());
	}
}
