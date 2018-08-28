package cn.itcast.bookstore.cart.domain;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

//购物车类
public class Cart {
	//包含n个条目，要有顺序:linkedHashMap
	private Map<String, CartItem> map = new LinkedHashMap<String, CartItem>();

	//合计，可能会出现二进制运算误差问题
	public double getTotal() {
/*		double sum = 0;
		for(CartItem ci : map.values()) {
			//合计 = 每个条目小计的和
			sum = sum + ci.getSubtotal();
		}
		return sum;
*/		
		//合计
		BigDecimal total = new BigDecimal("0");
		for(CartItem ci : map.values()) {
			//每个条目的小计
			BigDecimal subtotal = new BigDecimal("" + ci.getSubtotal());
			total = total.add(subtotal);
		}
		
		return total.doubleValue();
	}
	
	//添加条目到购物车
	public void add(CartItem ci) {
		//判断购物车是否存在该条目
		if(map.containsKey(ci.getBook().getBid())) { //存在
			//得到原来已经存在的条目
			CartItem _ci = map.get(ci.getBook().getBid());
			//设置老条目数为：原来的数量，加上新的条目数量
			_ci.setCount(_ci.getCount() + ci.getCount());
			map.put(ci.getBook().getBid(), _ci);
		} else {//不存在
			map.put(ci.getBook().getBid(), ci);
		}
	}
	
	//清空所有条目
	public void clear() {
		map.clear();
	}
	
	//删除指定条目，按商品id删除
	public void delete(String bid) {
		map.remove(bid);
	}
	
	//获取所有条目
	public Collection<CartItem> getCartItems() {
		return map.values();
	}
}
