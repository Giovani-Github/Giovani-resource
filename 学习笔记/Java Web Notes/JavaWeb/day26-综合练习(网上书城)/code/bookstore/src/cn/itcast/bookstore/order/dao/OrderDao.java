package cn.itcast.bookstore.order.dao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.itcast.bookstore.book.domain.Book;
import cn.itcast.bookstore.order.domain.Order;
import cn.itcast.bookstore.order.domain.OrderItem;
import cn.itcast.commons.CommonUtils;
import cn.itcast.jdbc.TxQueryRunner;

public class OrderDao {
	//简化jdbc操作的类
	private QueryRunner qr = new TxQueryRunner();
	
	//按id查询订单状态
	public int getStateByOid(String oid) {
		try {
			String sql = "SELECT state FROM orders WHERE oid=?";
			//因为state在设计表时就已经确定好类型了。而不是使用聚合函数查出来的，不确定类型的数字。所以直接强转为int即可
			Integer state = (Integer) qr.query(sql, new ScalarHandler(), oid);
			return state;
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	//修改订单状态
	public void updateState(String oid, int state) {
		try {
			String sql = "UPDATE orders SET state=? WHERE oid=?";
			qr.update(sql, state, oid);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	//添加订单，需要处理util.date 转换为sql的Timestamp
	public void addOrder(Order oder) {
		try {
			String sql = "INSERT INTO orders VALUE(?,?,?,?,?,?)";
			
			//需要处理util.date 转换为sql的Timesstamp（时分秒，年月日），构造器传递毫秒数即可
			Timestamp ordertime = new Timestamp(oder.getOrdertime().getTime());
			
			Object[] params = {oder.getOid(), ordertime, oder.getTotal(), 
					oder.getState(), oder.getOwner().getUid(), oder.getAddress()};
			qr.update(sql,params);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	//添加订单条目，需要批处理
	public void addOrderItemList(List<OrderItem> orderItemList) {
		/*
		 * queryRuunner类的batch(String sql, Object[][] params)
		 * 其中params是二维数组
		 * 没个头一位数组都与sql在一起制定一次，多个一位数组就执行多次
		 * */
		try {
			String sql = "INSERT INTO orderItem VALUE(?,?,?,?,?)";
			//把orderItemList转换成二维数组，把Oderitem对象转换成一个一位数组
			Object[][] params = new Object[orderItemList.size()][];//二维数组的长度是orderItemList的长度
			for(int i = 0; i < orderItemList.size(); i++) {//给二维数组中的每一个一维数组赋值
				//每个订单条目
				OrderItem item = orderItemList.get(i);
				//给每个二维数组中的一位数组赋值
				params[i] = new Object[]{item.getIid(), item.getCount(), 
						item.getSubtotal(), item.getOrder().getOid(), 
						item.getBook().getBid()};
			}
			
			qr.batch(sql, params);//执行批处理
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	//按uid查询订单
	public List<Order> findByUid(String uid) {
		/*
		 * 1.使用uid为条件查询所有订单
		 * 2.循环遍历每个订单，然后再为每个订单加载它自己的所有条目
		 * 3.返回orderList
		 * */
		try {
			String sql = "SELECT * FROM orders WHERE uid=?";
			List<Order> orderList = qr.query(sql, new BeanListHandler<Order>(Order.class), uid);
			//为每个订单加载它自己的所有OrderItem
			for(Order order : orderList) {
				loadOrderItems(order);
			}
			//返回订单列表
			return orderList;
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	//为每个订单加载他自己的所有orderIteam
	private void loadOrderItems(Order order) {
		/*
		 * 需要查询两张表：orderitem、book
		 * 因为一行结果集对应的不再是一个javabean，所以需要使用MapListHandler
		 * */
		try {
			String sql = "SELECT * FROM orderitem i, book b WHERE i.bid=b.bid AND oid=?";
			List<Map<String, Object>> mapList = qr.query(sql, new MapListHandler(), order.getOid());
			
			//把mapList变成一个orderIteamList，添加到order中
			List<OrderItem> orderItemList = toOrderItemList(mapList);
			order.setOrderItemList(orderItemList);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	//把mapList变成一个orderIteamList
	private List<OrderItem> toOrderItemList(List<Map<String, Object>> mapList) {
		/*
		 *  mapList是多个map，每个map对应一行结果集
		 * 一行：
		 * {iid=C7AD5492F27D492189105FB50E55CBB6, count=2, subtotal=60.0, oid=1AE8A70354C947F8B81B80ADA6783155, bid=7, bname=精通Hibernate,price=30.0, author=张卫琴, image=book_img/8991366-1_l.jpg, cid=2}
		 * ...
		 * 
		 * 需要循环遍历mapList
		 * 得到每一个map
		 * 使用每一个map生成一个OrderItem
		 * 添加到orderItemList中
		 * */
		List<OrderItem> orderItemList = new ArrayList<OrderItem>();
		for(Map<String, Object> map : mapList) {
			//使用每一个map生成一个orderItem，添加到orderItemList中
			OrderItem oi = toOrderItem(map);
			orderItemList.add(oi);
		}
		//把结果返回
		return orderItemList;
	}

	//使用每一个map生成一个orderItem
	private OrderItem toOrderItem(Map<String, Object> map) {
		//使用map生成两个对象：orderItem、book。并且建立关系(book添加到orderItem中)
		OrderItem oi = CommonUtils.toBean(map, OrderItem.class);
		Book book = CommonUtils.toBean(map, Book.class);
		oi.setBook(book);
		return oi;
	}

	public Order load(String oid) {
		try {
			String sql = "SELECT * FROM orders WHERE oid=?";
			Order order = qr.query(sql, new BeanHandler<Order>(Order.class), oid);
			
			//为订单加载订单条目OrderItemList
			loadOrderItems(order);
			
			//返回订单列表
			return order;
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	//查询所有订单
	public List<Order> findAll() {
		try {
			String sql = "SELECT * FROM orders";
			List<Order> orderList = qr.query(sql, new BeanListHandler<Order>(Order.class));
			for(Order order : orderList) {
				//为每个订单加载orderIteam
				loadOrderItems(order);
			}
			return orderList;
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	//按订单状态查询订单
	public List<Order> toOrderByState(int state) {
		try {
			String sql = "SELECT * FROM orders WHERE state=?";
			List<Order> orderList = qr.query(sql, new BeanListHandler<Order>(Order.class), state);
			for(Order order : orderList) {
				//为每个订单加载orderIteam
				loadOrderItems(order);
			}
			return orderList;
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
