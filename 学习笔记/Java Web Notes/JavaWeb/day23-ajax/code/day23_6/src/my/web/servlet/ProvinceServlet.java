package my.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import my.domain.Province;
import my.service.Service;

public class ProvinceServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		Service service = new Service();
		/*
		 * 1.使用service的findAllProvince方法，获取到所有省份List<Province>
		 * 2.使用json-lib.jar的JSONArray对象，转换成json串
		 * 3.发送给客户端
		 * */
		
		List<Province> provinceList = service.findAllProvince();
		String json = JSONArray.fromObject(provinceList).toString();
		response.getWriter().print(json);
	}
}
