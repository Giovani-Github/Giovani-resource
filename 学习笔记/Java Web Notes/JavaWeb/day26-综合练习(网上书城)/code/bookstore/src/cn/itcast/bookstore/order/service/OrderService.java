package cn.itcast.bookstore.order.service;

import java.sql.SQLException;
import java.util.List;

import cn.itcast.bookstore.order.dao.OrderDao;
import cn.itcast.bookstore.order.domain.Order;
import cn.itcast.jdbc.JdbcUtils;

public class OrderService {
	//依赖dao
	private OrderDao orderDao = new OrderDao();
	
	//确认收货
	public void confirm(String oid) throws OrderException {
		/*
		 * 1.使用oid查询它的订单状态
		 * 	如果为3：表示用的是正规手段确认收货的。更改状态为4
		 * 	如果不为3：表示用的不正当手段确认收货，抛出异常
		 * */
		int state = orderDao.getStateByOid(oid);
		if(state != 3) {
			throw new OrderException("确认收货失败");
		}
		
		orderDao.updateState(oid, 4);
	}
	
	//添加订单，需要开启事务（添加订单和添加订单条目要在一个事务中完成）
	public void add(Order order) {
		try {
			//开启事务
			JdbcUtils.beginTransaction();
			
			//插入订单
			orderDao.addOrder(order);
			//插入订单的所有条目
			orderDao.addOrderItemList(order.getOrderItemList());
			
			//提交事务
			JdbcUtils.commitTransaction();
		} catch(Exception e) {
			//回滚事务
			try {
				JdbcUtils.rollbackTransaction();
			} catch (SQLException e1) {
			}
			throw new RuntimeException(e);
		}
	}

	public List<Order> myOrders(String uid) {
		return orderDao.findByUid(uid);
	}

	public Order load(String oid) {
		return orderDao.load(oid);
	}

	//支付成功后，调用此方法修改订单状态
	public void zhiFu(String oid) {
		/*
		 * 1.查看订单状态是否为1
		 * 	如果为1：修改订单状态，加积分等操作
		 * 	如果不为1（证明已经修改过订单状态）：什么都不做
		 * */		
		int state = orderDao.getStateByOid(oid);
		
		if(state == 1) {
			orderDao.updateState(oid, 2);
		}
	}

	//查询所有订单
	public List<Order> findAll() {
		List<Order> orderList = orderDao.findAll();
		return orderList;
	}

	//按订单状态查询订单
	public List<Order> toOrderByState(int state) {
		return orderDao.toOrderByState(state);
	}

	//发货
	public void shipments(String oid) {
		orderDao.updateState(oid, 3);		
	}
}
