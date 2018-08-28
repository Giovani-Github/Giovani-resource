package saxjaxp;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxJaxp2 {
	public static void main(String[] args) throws Exception {
		//(1)创建解析器工程
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance(); 
		//(2)创建解析器类
		SAXParser saxParser = saxParserFactory.newSAXParser();
		//(9)调用SAXParser的parse方法，传入xml文件路径，换入事件驱动，解析xml
		saxParser.parse("SaxJaxp.xml", new MyDefaultHandler3());
	}
}

//(3)创建一个类，继承DefaultHandler事件驱动类
class MyDefaultHandler3 extends DefaultHandler {
	//(5)在自己创建的事件驱动类中，定义一个成员变量，boolean flag=false
	boolean flag = false;
	
	//(4)重写DefaultHandler类的三个方法
	//(6)在startElement方法中，判断pName参数值是否是name，如果是，就flag=true
	public void startElement(String uri, String localName, String qName,Attributes attributes) throws SAXException {
		if("name".equals(qName)) {
			flag = true;
		}
	}

	public void characters(char[] ch, int start, int length) throws SAXException {
		//(7)在character方法中，判断flag是否是true，是就表示解析到了name标签，通过这个方法的三个参数使用字符串的构造函数，创建一个字符串（就是name标签的文本内容）
		if(flag == true) {
			System.out.println(new String(ch, start, length));			
		}
	}
	
	public void endElement(String uri, String localName, String qName) throws SAXException {
		//(8)在endElement方法中，判断flag是否是true，是就改为false。表示这个name标签解析完毕
		if("name".equals(qName)) {
				flag = false;			
		}
	}	
}