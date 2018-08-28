package my.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import my.domain.City;
import my.service.Service;

public class CityServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Service service = new Service();
		/*
		 * 1.获取客户端传过来的参数pid，省份的id
		 * 2.使用pid调用service的findByPid()方法，得到id指定的省份下所有的城市Lsit<City>
		 * 3.使用json-lib的JSONArray对象，把List<City>转换成json串
		 * 4.给客户端发送过去
		 * */
		int pid = Integer.parseInt(request.getParameter("pid"));
		List<City> cityList = service.findByPid(pid);
		String json = JSONArray.fromObject(cityList).toString();
		response.getWriter().print(json);
	}
}
