import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Dom4jParsingXml_1 {
	public static void main(String[] args) throws Exception {
		//(1)创建解析器 SAXReader
		SAXReader reader = new SAXReader();
		//(2)得到document
		Document document = reader.read("dom4jParsingXml.xml");
		//(3)得到根节点
		Element root = document.getRootElement();
		//(4)得到所有的p1标签
		List<Element> p1s = root.elements("p1");
		//(5)遍历p1s，得到name
		for (Element p1 : p1s) {
			//这里的elment就是p1s里面的子标签
			//得到p1下面的name标签
			Element name = p1.element("name");
			//(6)得到name的文本内容
			System.out.println(name.getText());
		}
	}
}
