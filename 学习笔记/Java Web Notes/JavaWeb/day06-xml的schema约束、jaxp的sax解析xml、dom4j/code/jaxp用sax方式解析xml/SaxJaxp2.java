package saxjaxp;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxJaxp2 {
	public static void main(String[] args) throws Exception {
		//(1)��������������
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance(); 
		//(2)������������
		SAXParser saxParser = saxParserFactory.newSAXParser();
		//(9)����SAXParser��parse����������xml�ļ�·���������¼�����������xml
		saxParser.parse("SaxJaxp.xml", new MyDefaultHandler3());
	}
}

//(3)����һ���࣬�̳�DefaultHandler�¼�������
class MyDefaultHandler3 extends DefaultHandler {
	//(5)���Լ��������¼��������У�����һ����Ա������boolean flag=false
	boolean flag = false;
	
	//(4)��дDefaultHandler�����������
	//(6)��startElement�����У��ж�pName����ֵ�Ƿ���name������ǣ���flag=true
	public void startElement(String uri, String localName, String qName,Attributes attributes) throws SAXException {
		if("name".equals(qName)) {
			flag = true;
		}
	}

	public void characters(char[] ch, int start, int length) throws SAXException {
		//(7)��character�����У��ж�flag�Ƿ���true���Ǿͱ�ʾ��������name��ǩ��ͨ�������������������ʹ���ַ����Ĺ��캯��������һ���ַ���������name��ǩ���ı����ݣ�
		if(flag == true) {
			System.out.println(new String(ch, start, length));			
		}
	}
	
	public void endElement(String uri, String localName, String qName) throws SAXException {
		//(8)��endElement�����У��ж�flag�Ƿ���true���Ǿ͸�Ϊfalse����ʾ���name��ǩ�������
		if("name".equals(qName)) {
				flag = false;			
		}
	}	
}