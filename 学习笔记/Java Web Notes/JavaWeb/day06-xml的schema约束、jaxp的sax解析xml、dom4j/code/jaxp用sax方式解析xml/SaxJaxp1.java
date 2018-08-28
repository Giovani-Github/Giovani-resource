package saxjaxp;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxJaxp1 {
	public static void main(String[] args) throws Exception {
		//(1)��������������
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance(); 
		//(2)������������
		SAXParser saxParser = saxParserFactory.newSAXParser();
		//(5)����SAXParser��parse����������xml�ļ�·���������¼�����������xml
		saxParser.parse("SaxJaxp.xml", new MyDefaultHandler());
		
		System.out.println();
		System.out.println("xml�ļ�����ԭ����ӡ����:");
		
		//xml�ļ�����ԭ����ӡ����
		saxParser.parse("src/saxjaxp/SaxJaxp.xml", new MyDefaultHandler2());
	}
}

//(3)����һ���࣬�̳�DefaultHandler�¼�������
class MyDefaultHandler extends DefaultHandler {
	
	//(4)��дDefaultHandler���������������Ҫ���������������������ѯxml��Ԫ�أ�
	//startElement������ͨ������qName��ȡ��ǩ����
	public void startElement(String uri, String localName, String qName,Attributes attributes) throws SAXException {
		System.out.println("start:" + qName);
	}
	
	//character������ʹ����������ͨ���ַ����Ĺ��췽��String(char[] ch, int start, int length)��ȡ����
	public void characters(char[] ch, int start, int length) throws SAXException {
		System.out.println("char:" + new String(ch, start, length));
	}
	
	//endElement������ͨ������qName��ȡ��ǩ����
	public void endElement(String uri, String localName, String qName) throws SAXException {
		System.out.println("end:" + qName);
	}		
}

//xml�ļ�����ԭ����ӡ����
class MyDefaultHandler2 extends DefaultHandler {
	
	//(4)��дDefaultHandler���������������Ҫ���������������������ѯxml��Ԫ�أ�
	//startElement������ͨ������qName��ȡ��ǩ����
	public void startElement(String uri, String localName, String qName,Attributes attributes) throws SAXException {
		System.out.print("<" + qName + ">");
	}
	
	//character������ʹ����������ͨ���ַ����Ĺ��췽��String(char[] ch, int start, int length)��ȡ����
	public void characters(char[] ch, int start, int length) throws SAXException {
		System.out.print(new String(ch, start, length));
	}
	
	//endElement������ͨ������qName��ȡ��ǩ����
	public void endElement(String uri, String localName, String qName) throws SAXException {
		System.out.print("</" + qName + ">");
	}		
}
