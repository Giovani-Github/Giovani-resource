package saxjaxp;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxJaxp1 {
	public static void main(String[] args) throws Exception {
		//(1)创建解析器工程
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance(); 
		//(2)创建解析器类
		SAXParser saxParser = saxParserFactory.newSAXParser();
		//(5)调用SAXParser的parse方法，传入xml文件路径，换入事件驱动，解析xml
		saxParser.parse("SaxJaxp.xml", new MyDefaultHandler());
		
		System.out.println();
		System.out.println("xml文件内容原样打印出来:");
		
		//xml文件内容原样打印出来
		saxParser.parse("src/saxjaxp/SaxJaxp.xml", new MyDefaultHandler2());
	}
}

//(3)创建一个类，继承DefaultHandler事件驱动类
class MyDefaultHandler extends DefaultHandler {
	
	//(4)重写DefaultHandler类的三个方法（重要操作，在三个方法里面查询xml的元素）
	//startElement方法，通过参数qName获取标签名称
	public void startElement(String uri, String localName, String qName,Attributes attributes) throws SAXException {
		System.out.println("start:" + qName);
	}
	
	//character方法，使用三个参数通过字符串的构造方法String(char[] ch, int start, int length)获取内容
	public void characters(char[] ch, int start, int length) throws SAXException {
		System.out.println("char:" + new String(ch, start, length));
	}
	
	//endElement方法，通过参数qName获取标签名称
	public void endElement(String uri, String localName, String qName) throws SAXException {
		System.out.println("end:" + qName);
	}		
}

//xml文件内容原样打印出来
class MyDefaultHandler2 extends DefaultHandler {
	
	//(4)重写DefaultHandler类的三个方法（重要操作，在三个方法里面查询xml的元素）
	//startElement方法，通过参数qName获取标签名称
	public void startElement(String uri, String localName, String qName,Attributes attributes) throws SAXException {
		System.out.print("<" + qName + ">");
	}
	
	//character方法，使用三个参数通过字符串的构造方法String(char[] ch, int start, int length)获取内容
	public void characters(char[] ch, int start, int length) throws SAXException {
		System.out.print(new String(ch, start, length));
	}
	
	//endElement方法，通过参数qName获取标签名称
	public void endElement(String uri, String localName, String qName) throws SAXException {
		System.out.print("</" + qName + ">");
	}		
}
