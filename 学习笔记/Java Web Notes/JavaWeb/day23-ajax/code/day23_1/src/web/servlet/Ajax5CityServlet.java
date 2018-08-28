package web.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Ajax5CityServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//要发送给ajax5.jsp的是一个xml
		response.setContentType("text/xml;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		/*
		 * 要返回的是:
		 * <province name="北京">
				<city>西城区</city>
				...
			</province>
		 * */
		//xml解析器
		SAXReader sax = new SAXReader();
		InputStream input = this.getClass().getResourceAsStream("/china.xml");//china.xml所在路径的流
		try {
			//得到xml的document对象
			Document document = sax.read(input);
			
			//获取省份名称
			String pname = request.getParameter("pname");
			//得到name=pname的<province>元素
			Element pEle = (Element) document.selectSingleNode("//province[@name='" + pname +"']");
			//把元素及其子元素转换为字符串形式的xml
			String xmlStr = pEle.asXML();
			//发送给ajax5.jsp
			response.getWriter().print(xmlStr);
			
		} catch (DocumentException e) {
			throw new RuntimeException(e);
		}
	}
}
