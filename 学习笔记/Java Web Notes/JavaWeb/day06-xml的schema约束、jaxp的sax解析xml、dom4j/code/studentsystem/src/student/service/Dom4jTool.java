package student.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class Dom4jTool {
	//xml�ļ���·��
	public static final String URL = "src/StudentData.xml";
	//��ȡdocument�ķ���
	public static Document getDocument() {
		try {
			//(1)���������� SAXReader
			SAXReader saxReader = new SAXReader();
			//���docment
			Document document = saxReader.read(URL);
			//����document
			return document;
		} catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	//���ڴ���xml��д���ļ��еķ���
	public static void back(Document document) {
		//������ʽ����
		OutputFormat format = OutputFormat.createPrettyPrint();
		//������д��
		XMLWriter xmlWriter = null;
		try {
			xmlWriter = new XMLWriter(new FileOutputStream(URL), format);
			//��д
			xmlWriter.write(document);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				xmlWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
