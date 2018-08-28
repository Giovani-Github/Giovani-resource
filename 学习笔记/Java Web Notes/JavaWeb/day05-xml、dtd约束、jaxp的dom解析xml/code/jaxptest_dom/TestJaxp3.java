import javax.xml.parsers.*;
import org.w3c.dom.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

class TestJaxp3 {
	public static void main(String[] args) throws Exception{
		//创建解析器工厂
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		//根据解析器工厂创建解析器
		DocumentBuilder builder = factory.newDocumentBuilder();
		//解析xml，返回document 
		Document document = builder.parse("TestJaxp1.xml");
		//得到第一个p1
		NodeList list = document.getElementsByTagName("p1");
		Node p1 = list.item(0);
		//创建sex标签 createElement
		Element sex = document.createElement("sex");
		//创建文本 createTextNode
		Text sexText = document.createTextNode("dffdf");
		//把文本添加到sex下面 appendChild
		sex.appendChild(sexText);
		//把sex添加到第一个p1下面
		p1.appendChild(sex);
		
		/*回写xml,通过TransformerFactory类获取Transformer类，使用Transformer的transform()方法，回写xml*/
		//创建TransformerFactory
		TransformerFactory transFactory = TransformerFactory.newInstance();
		//通过TransformerFactory获取Transformer
		Transformer trans = transFactory.newTransformer();
		//把内存里的xml写入文件
		trans.transform(new DOMSource(document),new StreamResult("TestJaxp1.xml"));
	}
}
