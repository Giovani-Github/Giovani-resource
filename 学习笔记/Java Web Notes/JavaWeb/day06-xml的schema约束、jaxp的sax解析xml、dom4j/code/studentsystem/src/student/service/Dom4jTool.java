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
	//xml文件的路径
	public static final String URL = "src/StudentData.xml";
	//获取document的方法
	public static Document getDocument() {
		try {
			//(1)创建解析器 SAXReader
			SAXReader saxReader = new SAXReader();
			//获得docment
			Document document = saxReader.read(URL);
			//返回document
			return document;
		} catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	//把内存中xml回写到文件中的方法
	public static void back(Document document) {
		//创建格式化类
		OutputFormat format = OutputFormat.createPrettyPrint();
		//创建回写类
		XMLWriter xmlWriter = null;
		try {
			xmlWriter = new XMLWriter(new FileOutputStream(URL), format);
			//回写
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
