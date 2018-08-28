import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Dom4jParsingXml_2 {
	public static void main(String[] args) throws Exception {
		//(1)创建解析器 SAXReader
		SAXReader saxReader = new SAXReader();
		//(2)得到document
		Document document = saxReader.read("dom4jParsingXml.xml");
		//(3)得到根节点
		Element root = document.getRootElement();
		//(4)得到第一个p1
		Element p1 = root.element("p1");
		//(5)得到p1下面的第一个name
		Element name = p1.element("name");
		//(6)得到name的文本内容
		System.out.println(name.getText());
	}
}
