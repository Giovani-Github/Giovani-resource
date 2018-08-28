package cn.itcast.cstm.web.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.commons.CommonUtils;
import cn.itcast.cstm.domain.Customer;
import cn.itcast.cstm.domain.PageBean;
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
//	public String findAll(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		
//		//调用service的findAll得到查询结果
//		List<Customer> list = customerService.findAll();
//		//查询结果保存到reuqest域中
//		request.setAttribute("clist", list);		
//		return "f:/list.jsp";//转发到list.jsp
//	}
	
	/***
	 * 分页查询
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		/*
		 * 1.获取当前页码pc，在这里理解为：当前要查询第几页
		 * 2.给定ps，即每页记录数
		 * 3.调用service的findAll方法，查询出PageBean，我们当前要查询页面数据是Customer
		 * 4.保存PageBean到request域
		 * 5.转发到list.jsp
		 * */
		int pc = getPc(request);
		int ps = 10;
		PageBean<Customer> pb = customerService.findAll(pc, ps);
		request.setAttribute("pb",pb);
		return "f:/list.jsp";
	}
	
	/***
	 * 获取当前页码，即获取当前要查询第几页
	 * @param request
	 * @return
	 */
	private int getPc(HttpServletRequest request) {
		/*
		 * 1.获取pc
		 * 2.如果pc等于null，表示当前是第一次 查询，设置pc为第一页
		 * 3.如果pc不等于null，表示不是第一次查询，是按下一页，或点击页码来查询。
		 * 	所以，转换成int类型，如实返回即可
		 * */
		String pc = request.getParameter("pc");
		if(pc == null || pc.trim().isEmpty()) {
			return 1;
		}
		return Integer.parseInt(pc);
		
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
//	public String query(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		/*
//		 * 1.封装表单数据到Customer中，因为高级搜索中的数据只有四个，所以这个Customer作为条件对象
//		 * 2.调用service的query(Customer)，传递条件对象，进行多条件组合查询，返回符合条件的结果，用list<Customer>存放
//		 * 3.把结果保存到request域中
//		 * 4.转发到list.jsp
//		 * */
//		
//		Customer ct = CommonUtils.toBean(request.getParameterMap(), Customer.class);
//		List<Customer> clist = customerService.query(ct);
//		request.setAttribute("clist", clist);
//		return "f:/list.jsp";
//	}
	
	/***
	 * 分页形式的多条件组合查询
	 * 	
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String query(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 1.获取当前页码pc
		 * 2.给定ps，每页记录数
		 * 3.获取多条件组合查询的条件
		 * 4.调用Serviet的query方法，传递pc，传递ps，传递条件。获得PageBean
		 * 5.保存PageBena到request域中
		 * 6.转发到list.jsp
		 * */
		int pc = getPc(request);
		int ps = 10;		
		Customer ct = CommonUtils.toBean(request.getParameterMap(), Customer.class);
		//这里要处理get请求的编码问题
		ct = encoding(ct);
		PageBean<Customer> pb = customerService.query(pc, ps, ct);
		request.setAttribute("pb", pb);
		return "f:/list.jsp";
	}


	//处理编码问题
	private Customer encoding(Customer ct) throws UnsupportedEncodingException {
		/*
		 * post的编码问题，已经在父类BaseServlet中进行了处理
		 * 所以我们这里处理get请求的编码问题
		 * 重要一点：
		 * 	把query.jsp的表单请求方式改为get
		 * 	因为，如果是post，父类已经处理了编码问题。
		 * 	但是，后面带的四个条件。又会被拿到这里来进行解码成字节，而这里是用ISO-8859-1解码的，
		 * 	所以当放到页码表的超链接上时，会导致乱码
		 * */
		String cname = ct.getCname();
		String gender = ct.getGender();
		String cellphone = ct.getCellphone();
		String email = ct.getEmail();
		
		if(cname != null && !cname.trim().isEmpty()) {
			cname = new String(cname.getBytes("ISO-8859-1"), "UTF-8");
			ct.setCname(cname);
		}
		if(gender != null && !gender.trim().isEmpty()) {
			gender = new String(gender.getBytes("ISO-8859-1"), "UTF-8");
			ct.setGender(gender);
		}
		if(cellphone != null && !cellphone.trim().isEmpty()) {
			cellphone = new String(cellphone.getBytes("ISO-8859-1"), "UTF-8");
			ct.setCellphone(cellphone);
		}
		if(email != null && !email.trim().isEmpty()) {
			email = new String(email.getBytes("ISO-8859-1"), "UTF-8");
			ct.setEmail(email);
		}
		return ct;
	}
}
