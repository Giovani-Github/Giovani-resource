package cn.itcast.cstm.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.commons.CommonUtils;
import cn.itcast.cstm.domain.Customer;
import cn.itcast.cstm.service.CustomerService;
import cn.itcast.servlet.BaseServlet;

/*
 * servlet层
 * 继承我们写的一个BaseServlet，简化Servlet的量
 * */
public class CustomerServlet extends BaseServlet {
	private CustomerService customerService = new CustomerService();
	
	/***
	 * 添加用户方法
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 1.将表单数据封装到Customer对象中
		 * 2.补全cid，用uuid
		 * 3.把Customer对象传递给Service方法完成任务
		 * 4.保存成功信息到reuqest域中，msg
		 * 5.转发到msg.jsp页面
		 * */
		
		Customer c = CommonUtils.toBean(request.getParameterMap(), Customer.class);
		c.setCid(CommonUtils.uuid());
		customerService.add(c);
		request.setAttribute("msg", "添加客户成功");
		return "f:/msg.jsp";//转发到msg.jsp页面
	}
	
	
	/***
	 * 查询所有用户
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//调用service的findAll得到查询结果
		List<Customer> list = customerService.findAll();
		//查询结果保存到reuqest域中
		request.setAttribute("clist", list);		
		return "f:/list.jsp";//转发到list.jsp
	}
	
	/***
	 * 编辑之前
	 * 编辑之前要先根据cid查找用户，然后显示在edit.jsp页面上，即回显
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String preEdit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//得到cid，用来进行查询出Customer对象的
		String cid = request.getParameter("cid");
		//调用service的加载方法，通过cid进行查询加载，得到用户的对象。使用主键查询就是加载
		Customer c = customerService.load(cid);
		//把查询到的用户对象保存到request域中
		request.setAttribute("c", c);
		//转发到edit.jsp
		return "f:/edit.jsp";
	}
	
	/***
	 * 编辑客户信息
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String edit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 1.封装表单数据到Customer对象中
		 * 2.调用service的edit完成编辑
		 * 3.保存成功信息到request域中
		 * 4.转发到msg.jsp显示成功信息
		 * */
		
		Customer c = CommonUtils.toBean(request.getParameterMap(), Customer.class);
		customerService.edit(c);
		request.setAttribute("msg", "修改成功");		
		return "f:/msg.jsp";
	}
	
	/**
	 * 删除客户
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 1.获取cid
		 * 2.调用service的delete(cid)方法，传递cid过去根据cid删除
		 * 3.保存成功信息到request域中
		 * 4.转发到msg.jsp显示成功信息
		 * */
		
		String cid = request.getParameter("cid");
		customerService.delete(cid);
		request.setAttribute("msg", "删除成功");
		return "f:/msg.jsp";
	}
	
	
	/**
	 * 多条件组合查询
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String query(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 1.封装表单数据到Customer中，因为高级搜索中的数据只有四个，所以这个Customer作为条件对象
		 * 2.调用service的query(Customer)，传递条件对象，进行多条件组合查询，返回符合条件的结果，用list<Customer>存放
		 * 3.把结果保存到request域中
		 * 4.转发到list.jsp
		 * */
		
		Customer ct = CommonUtils.toBean(request.getParameterMap(), Customer.class);
		List<Customer> clist = customerService.query(ct);
		request.setAttribute("clist", clist);
		return "f:/list.jsp";
	}
}
