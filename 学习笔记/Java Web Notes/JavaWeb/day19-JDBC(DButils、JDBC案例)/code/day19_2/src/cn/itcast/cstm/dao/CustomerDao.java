package cn.itcast.cstm.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.itcast.cstm.domain.Customer;
import cn.itcast.jdbc.TxQueryRunner;

/*
 * 数据层，对数据库表进行操作
 * */
public class CustomerDao {
	//简化jdbc操作的类
	private QueryRunner qr = new TxQueryRunner();
	
	/***
	 * 添加用户方法
	 * @param c
	 */
	public void add(Customer c) {
		//准备sql模板
		String sql = "INSERT INTO t_customer VALUES(?,?,?,?,?,?,?)";
		//给出模板参数
		Object[] params = {c.getCid(), c.getCname(), c.getGender(), 
							c.getBirthday(), c.getCellphone(), c.getEmail(), 
							c.getDescription()};
		
		//执行sql语句
		try {
			qr.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/***
	 * 查询所有用户
	 * @return List，里面是所有的用户对象
	 */
	public List<Customer> findAll() {
		//给出sql模板
		String sql = "SELECT * FROM t_customer";
		try {
			//执行sql语句，使用BeanListHandler对结果集进行处理，变成list集合
			List<Customer> list = qr.query(sql, new BeanListHandler<Customer>(Customer.class));
			//返回查询出来的结果
			return list;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/***
	 * 通过主键进行查询，得到Customer
	 * @param cid
	 * @return
	 */
	public Customer findByCid(String cid) {
		//准备sql模板
		String sql = "SELECT * FROM t_customer WHERE cid=?";
		try {
			//执行sql语句，并返回执行后的结果
			return qr.query(sql, new BeanHandler<Customer>(Customer.class), cid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 编辑方法，更改用户的信息
	 * @param c
	 */
	public void edit(Customer c) {
		//准备sql模板
		String sql = "UPDATE t_customer SET cname=?, gender=?, birthday=?," +
				"cellphone=?, email=?, description=? WHERE cid=?";
			
		//设置sql模板的参数
		Object[] params = {c.getCname(), c.getGender(), 
		c.getBirthday(), c.getCellphone(), c.getEmail(), 
		c.getDescription(), c.getCid()};
		
		try {
			//执行sql语句
			qr.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 根据cid主键删除客户
	 * @param cid
	 */
	public void delete(String cid) {
		//准备sql模板
		String sql = "DELETE FROM t_customer WHERE cid=?";
		try {
			//执行sql
			qr.update(sql, cid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 多条件组合查询
	 * @param ct
	 * @return
	 */
	public List<Customer> query(Customer ct) {
		/*
		 * 1.准备sql模板前缀
		 * 	因为用户填写的条件不是固定的，有几种可能：没填条件，填了一个条件，多个条件
		 * 	所以，先给出一个sql模板前缀，后缀使用if判断，如果ct中的四个属性有值，就把这个条件追加
		 * 	if判断：
		 * 		获取ct其中四个值之一，判断其是否为null。
		 * 			为nul：表示用户没有填写这个条件，就不在sql模板中追加条件
		 * 			不为null：表示用户填写了这个条件，在sql模板中追加条件
		 * 	SELECT * FROM t_customer WHERE 1=1:这样写的目的是，后面追加的条件就不用考虑到底用and还是WHERE。而是全部使用and追加
		 * 2.追加条件：
		 * 	因为用户填写的条件可能是参数值得一部分，例如：名字，用户填的只是名字中的一个字
		 * 	所以追加条件的时候可以使用模糊查询LIKE 
		 * 3.设置sql模板参数
		 * 	使用ArrayList存
		 * 	if条件判断的时候，如果这个属性有值，就把这个参数添加进ArrayList
		 * 4.执行sql语句，获得查询到的结果，使用结果集处理器;BeanListHandler，把结果转换为List<Customer>，传递参数ArrayLi
		 * 5.返回结果List<Customer>
		 * */
		
		try {
			//sql模板前缀
			StringBuilder sql = new StringBuilder("SELECT * FROM t_customer WHERE 1=1 ");
			//添加到sql模板的参数
			ArrayList<Object> al = new ArrayList<Object>();
			
			
			//得到条件中的一个
			String cname = ct.getCname();
			//如果得到的条件不为null，且不为""，证明用户填写了这个条件，进行追加
			if(cname != null && !cname.trim().isEmpty()) {
				sql.append("and cname LIKE ?");//使用模糊查询
				al.add("%" + cname + "%");//添加到sql模板的参数			
			}
			
			//得到条件中的一个
			String gender = ct.getGender();
			//如果得到的条件不为null，且不为""，证明用户填写了这个条件，进行追加
			if(gender != null && !gender.trim().isEmpty()) {
				sql.append("and gender=?");//性别不用模糊查询
				al.add(gender);//添加到sql模板的参数			
			}
			
			//得到条件中的一个
			String cellphone = ct.getCellphone();
			//如果得到的条件不为null，且不为""，证明用户填写了这个条件，进行追加
			if(cellphone != null && !cellphone.trim().isEmpty()) {
				sql.append("and cellphone LIKE ?");//使用模糊查询
				al.add("%" + cellphone + "%");//添加到sql模板的参数			
			}
			
			//得到条件中的一个
			String email = ct.getEmail();
			//如果得到的条件不为null，且不为""，证明用户填写了这个条件，进行追加
			if(email != null && !email.trim().isEmpty()) {
				sql.append("and email LIKE ?");//使用模糊查询
				al.add("%" + email + "%");//添加到sql模板的参数			
			}
			
			//执行sql语句，BeanListHandler处理结果集
			List<Customer> list = qr.query(sql.toString(), new BeanListHandler<Customer>(Customer.class), al.toArray());
			
			return list;
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
}
