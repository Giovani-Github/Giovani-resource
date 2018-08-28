package web.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

public class Ajax5ProvinceServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		/*
		 * 从china.xml中，获取所有省份名称，变成字符串发送给ajax5.jsp 格式：北京,天津....
		 * 使用dom4j解析xml:
		 * 	(1)创建解析器 SAXReader
			(2)得到document
		 */
		
		//xml解析器
		SAXReader sax = new SAXReader();
		InputStream input = this.getClass().getResourceAsStream("/china.xml");//china.xml所在路径的流
		try {
			//得到xml的document对象
			Document document = sax.read(input);
			//得到所有province元素的name属性
			List<Attribute> pnames = document.selectNodes("//province/@name");
			//存放所有省份的字符串
			StringBuilder provinces = new StringBuilder();
			//遍历pnames,得到每一个province的name属性，在得到name属性的值，存放到provinces中，用","隔开
			for(int i = 0; i < pnames.size(); i++) {
				Attribute a = pnames.get(i);
				String province = pnames.get(i).getValue();
				provinces.append(province);
				if(i != pnames.size() - 1) {//不是最后一个
					provinces.append(",");//以逗号隔开
				}
			}
			response.getWriter().print(provinces);
			
		} catch (DocumentException e) {
			throw new RuntimeException(e);
		}
		
		
	}
}
