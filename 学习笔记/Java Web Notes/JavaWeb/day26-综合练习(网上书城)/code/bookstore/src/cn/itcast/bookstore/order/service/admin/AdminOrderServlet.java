package cn.itcast.bookstore.order.service.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.bookstore.order.domain.Order;
import cn.itcast.bookstore.order.service.OrderService;
import cn.itcast.servlet.BaseServlet;

/**
 * Servlet implementation class AdminOrderServlet
 */
public class AdminOrderServlet extends BaseServlet {
	private OrderService orderService = new OrderService();
	//查询所有订单
	public String findAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			/*
			 * 1.调用service的方法，得到List<Order>
			 * 2.保存到request中，转发到adminjsps/admin/order/list.jsp
			 * */
		List<Order> orderList = orderService.findAll();
		request.setAttribute("orderList", orderList);
		return "f:/adminjsps/admin/order/list.jsp";
	}
	
	//按状态查询订单
	public String toOrderByState(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 获取订单状态state
		 * 调用service的方法，得到List<Order>
		 * 保存到request中，转发到adminjsps/admin/order/list.jsp
		 * */
		int state = Integer.parseInt(request.getParameter("state"));
		List<Order> orderList = orderService.toOrderByState(state);
		request.setAttribute("orderList", orderList);
		return "f:/adminjsps/admin/order/list.jsp";
	}
	
	//发货
	public String shipments(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String oid = request.getParameter("oid");
		orderService.shipments(oid);
		request.setAttribute("msg", "发货成功!");
		return "f:/adminjsps/admin/msg.jsp";
	}
}
