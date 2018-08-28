package cn.itcast.bookstore.order.web.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

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
	
	//支付之银行回调方法
	public String back(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 1.获取11+1参数
		 * 2.校验访问者身份是否为易宝
		 * 	* 使用11个参数，和keyValue生成hmac，与易宝传递过来的hmac进行比较即可
		 * 3.支付
		 * 4.判断回调方式
		 * 	* 如果是点对点，回馈success
		 * 5.保存成功信息，转发到msg.jsp
		 * */
		
		//获取11+1参数
		String p1_MerId = request.getParameter("p1_MerId");
		String r0_Cmd = request.getParameter("r0_Cmd");
		String r1_Code = request.getParameter("r1_Code");
		String r2_TrxId = request.getParameter("r2_TrxId");
		String r3_Amt = request.getParameter("r3_Amt");
		String r4_Cur = request.getParameter("r4_Cur");
		String r5_Pid = request.getParameter("r5_Pid");
		String r6_Order = request.getParameter("r6_Order");
		String r7_Uid = request.getParameter("r7_Uid");
		String r8_MP = request.getParameter("r8_MP");
		String r9_BType = request.getParameter("r9_BType");
		
		String hmac = request.getParameter("hmac");
		
		//校验访问者身份是否为易宝
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("merchantInfo.properties");
		Properties props = new Properties();
		props.load(in);
		String keyValue = props.getProperty("keyValue");
		//校验
		boolean bool = PaymentUtil.verifyCallback(hmac, p1_MerId, r0_Cmd, r1_Code, 
				r2_TrxId, r3_Amt, r4_Cur, r5_Pid, r6_Order, r7_Uid,
				r8_MP, r9_BType, keyValue);
		if(!bool) {
			request.setAttribute("msg", "非法操作");
			return "f:/jsps/msg.jsp";
		}
		
		//3.支付
		orderService.zhiFu(r6_Order);//有可能不会执行
		
		//判断回调的方式
		if(r9_BType == "2") {//回调方式是点对点
			response.getWriter().print("success");
		}
		
		//保存成功信息到request
		request.setAttribute("msg", "支付成功，等待发货");
		//转发到msg.jsp		
		return "f:/jsps/msg.jsp";
	}
	
	//支付之去银行
	public String zhiFu(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 1.准备13+1参数
		 * 2.重定向到易付宝支付网关
		 * */
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("merchantInfo.properties");
		Properties props = new Properties();
		props.load(in);
		
		String p0_Cmd = "Buy";//业务类型，固定值Buy
		String p1_MerId = props.getProperty("p1_MerId");//把钱给哪个易付宝，这里我们给一个测试账号，在配置文件中
		String p2_Order = request.getParameter("oid");//订单编号
		String p3_Amt = "0.01";//支付金额
		String p4_Cur = "CNY";//货币类型，固定值人民币
		String p5_Pid = "";//商品名称，不给值
		String p6_Pcat = "";//商品种类，不给值
		String p7_Pdesc = "";//商品描述，不给值
		String p8_Url = props.getProperty("p8_Url");//支付成功后，易付宝发送成功通知到这个地址
		String p9_SAF = "";//送货地址
		String pa_MP = "";//商户扩展信息
		String pd_FrpId = request.getParameter("pd_FrpId");//支付通道：中国银行、建设银行......
		String pr_NeedResponse = "1";//应答机制，固定值为1
		
		//计算hmac，最后一个参数
		String keyValue = props.getProperty("keyValue");
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, 
				p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP, 
				pd_FrpId, pr_NeedResponse, keyValue);
		
		//把13+1个参数与易付宝支付网关网址连接
		StringBuilder url = new StringBuilder(props.getProperty("url"));
		url.append("?p0_Cmd=").append(p0_Cmd);
		url.append("&p1_MerId=").append(p1_MerId);
		url.append("&p2_Order=").append(p2_Order);
		url.append("&p3_Amt=").append(p3_Amt);
		url.append("&p4_Cur=").append(p4_Cur);
		url.append("&p5_Pid=").append(p5_Pid);
		url.append("&p6_Pcat=").append(p6_Pcat);
		url.append("&p7_Pdesc=").append(p7_Pdesc);
		url.append("&p8_Url=").append(p8_Url);
		url.append("&p9_SAF=").append(p9_SAF);
		url.append("&pa_MP=").append(pa_MP);
		url.append("&pd_FrpId=").append(pd_FrpId);
		url.append("&pr_NeedResponse=").append(pr_NeedResponse);
		url.append("&hmac=").append(hmac);
		
		System.out.println(url);
			
		//重定向到易付宝支付网关
		response.sendRedirect(url.toString());
		
		return null;
	}
	
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
