package cn.itcast.bookstore.order.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.bookstore.cart.domain.Cart;
import cn.itcast.bookstore.cart.domain.CartItem;
import cn.itcast.bookstore.order.domain.Order;
import cn.itcast.bookstore.order.domain.OrderItem;
import cn.itcast.bookstore.order.service.OrderException;
import cn.itcast.bookstore.order.service.OrderService;
import cn.itcast.bookstore.user.domain.User;
import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

public class OrderServlet extends BaseServlet {
	//依赖service
	private OrderService orderService = new OrderService();
	//确认收货
	public String confirm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 1.获取oid
		 * 2.使用oid调用service的方法
		 * 	抛出异常，保存异常信息到request，转发到msg.jsp
		 * 
		 * 保存成功信息到request，转发到msg.jsp
		 * */
		String oid = request.getParameter("oid");
		try {
			orderService.confirm(oid);
			request.setAttribute("msg", "恭喜，交易成功!");
		} catch (OrderException e) {
			request.setAttribute("msg", e.getMessage());
		}
		
		return "f:/jsps/msg.jsp";
	}
	
	//加载订单
	public String load(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 1.得到oid
		 * 2.使用oid调用service的load方法，得到order
		 * 3.把order保存到request中
		 * 4.转发到jsps/order/desc.jsp
		 * */
		String oid = request.getParameter("oid");
		Order order = orderService.load(oid);
		request.setAttribute("order", order);
		return "f:/jsps/order/desc.jsp";
	}
	
	//我的订单
	public String myOrders(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 1.从session中获取User，在获取uid
		 * 2.使用uid调用service的方法得到List<Order>，保存到request
		 * 3.转发到/jsps/order/list.jsp
		 * */
		User user = (User) request.getSession().getAttribute("user");
		String uid = user.getUid();
		List<Order> orderList = orderService.myOrders(uid);
		request.setAttribute("orderList", orderList);
		return "f:/jsps/order/list.jsp";
	}
	
	//添加订单，需要从session中拿到车，转换为order对象
	public String add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 1.从session中获取cart
		 * 2.把cart转换成order
		 * 3.使用service完成订单插入
		 * 4.清空购物车
		 * 5.把订单保存到request，转发到/jsps/order/desc.jsp
		 * */
		Cart cart = (Cart)request.getSession().getAttribute("cart");
		//把cart转换成order
		Order order = cartShiftOrder(cart, request);
		//插入订单
		orderService.add(order);
		//清空购物车
		cart.clear();		
		request.setAttribute("order", order);
		
		return "f:/jsps/order/desc.jsp";
	}

	private Order cartShiftOrder(Cart cart, HttpServletRequest request) {
		//创建一个order
		Order order = new Order();
		order.setOid(CommonUtils.uuid());//设置订单编号
		order.setOrdertime(new Date());//设置下单时间
		order.setTotal(cart.getTotal());//设置订单合计
		order.setState(1);//设置订单状态为1，表示未付款
		User user = (User)request.getSession().getAttribute("user");//从session中获取当前用户
		order.setOwner(user);//设置订单所有者为当前用户
		//地址在这里不设置，而是付款之前设置
		
		//把cartItemList转换为orderItemList
		List<OrderItem> orderItemList = new ArrayList<OrderItem>();
		//循环遍历cartItemList，把每一个cartItem变成一个orderItem，并且添加到orderItemList中
		for(CartItem ci : cart.getCartItems()) {
			OrderItem oi = new OrderItem();
			oi.setIid(CommonUtils.uuid());//设置订单条目的id
			oi.setCount(ci.getCount());//设置订单条目中商品的数量
			oi.setSubtotal(ci.getSubtotal());//设置订单条目的小计
			oi.setOrder(order);//设置订单条目所属的订单
			oi.setBook(ci.getBook());//设置订单条目中的商品
			
			orderItemList.add(oi);
		}
		
		//把orderItemList添加到order中
		order.setOrderItemList(orderItemList);
		
		return order;
	}
}
