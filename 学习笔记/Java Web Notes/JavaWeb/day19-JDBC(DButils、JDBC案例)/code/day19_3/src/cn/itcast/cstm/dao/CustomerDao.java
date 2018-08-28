package cn.itcast.cstm.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.itcast.cstm.domain.Customer;
import cn.itcast.cstm.domain.PageBean;
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
//	public List<Customer> findAll() {
//		//给出sql模板
//		String sql = "SELECT * FROM t_customer";
//		try {
//			//执行sql语句，使用BeanListHandler对结果集进行处理，变成list集合
//			List<Customer> list = qr.query(sql, new BeanListHandler<Customer>(Customer.class));
//			//返回查询出来的结果
//			return list;
//		} catch (SQLException e) {
//			throw new RuntimeException(e);
//		}
//	}
	
	/*
	 * 根据当前要查询的页码pc和每页记录数，获取分页数据PageBean
	 * */
	public PageBean<Customer> findAll(int pc, int ps) {
		try {
			/*
			 * 1.创建出PageBean
			 * 2.设置其pc，和ps
			 * 3.sql语句查询出总记录数tr，并设置进PageBean
			 * 	使用ScalarHandler结果集处理器处理
			 * 4.查询出BeanList，即当前页的数据
			 * 	SELECT * FROM t_customer LIMIT ?,?
			 * 	第一个问号：从第几行开始查询
			 * 		pc - 1 * ps
			 * 		即：要查询的页码，减一，乘以每页记录数
			 * 	第二个问号：查询多少行
			 * 		ps：即每页记录数
			 * 	使用BeanListHandler结果集处理器处理:List<Customer>
			 * 	设置BeanList进PageBean
			 * 5.返回PageBean
			 * */
			//1.创建出PageBean
			PageBean<Customer> pb = new PageBean<Customer>();
			//2.设置pc与ps
			pb.setPc(pc);
			pb.setPs(ps);
			
			//3.sql语句查询出总记录数tr，并设置进PageBean
			String sql = "SELECT COUNT(*) FROM t_customer";
			Number num = (Number) qr.query(sql, new ScalarHandler());
			int tr = num.intValue();
			pb.setTr(tr);
			
			//4.查询出BeanList，并设置到PageBean
			sql = "SELECT * FROM t_customer ORDER BY cname LIMIT ?, ?";
			List<Customer> beanList = qr.query(sql, new BeanListHandler<Customer>(Customer.class),
					(pc-1)*ps, ps);
			pb.setBeanList(beanList);
			
			return pb;
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
//	public List<Customer> query(Customer ct) {
//		/*
//		 * 1.准备sql模板前缀
//		 * 	因为用户填写的条件不是固定的，有几种可能：没填条件，填了一个条件，多个条件
//		 * 	所以，先给出一个sql模板前缀，后缀使用if判断，如果ct中的四个属性有值，就把这个条件追加
//		 * 	if判断：
//		 * 		获取ct其中四个值之一，判断其是否为null。
//		 * 			为nul：表示用户没有填写这个条件，就不在sql模板中追加条件
//		 * 			不为null：表示用户填写了这个条件，在sql模板中追加条件
//		 * 	SELECT * FROM t_customer WHERE 1=1:这样写的目的是，后面追加的条件就不用考虑到底用and还是WHERE。而是全部使用and追加
//		 * 2.追加条件：
//		 * 	因为用户填写的条件可能是参数值得一部分，例如：名字，用户填的只是名字中的一个字
//		 * 	所以追加条件的时候可以使用模糊查询LIKE 
//		 * 3.设置sql模板参数
//		 * 	使用ArrayList存
//		 * 	if条件判断的时候，如果这个属性有值，就把这个参数添加进ArrayList
//		 * 4.执行sql语句，获得查询到的结果，使用结果集处理器;BeanListHandler，把结果转换为List<Customer>，传递参数ArrayLi
//		 * 5.返回结果List<Customer>
//		 * */
//		
//		try {
//			//sql模板前缀
//			StringBuilder sql = new StringBuilder("SELECT * FROM t_customer WHERE 1=1 ");
//			//添加到sql模板的参数
//			ArrayList<Object> al = new ArrayList<Object>();
//			
//			
//			//得到条件中的一个
//			String cname = ct.getCname();
//			//如果得到的条件不为null，且不为""，证明用户填写了这个条件，进行追加
//			if(cname != null && !cname.trim().isEmpty()) {
//				sql.append("and cname LIKE ?");//使用模糊查询
//				al.add("%" + cname + "%");//添加到sql模板的参数			
//			}
//			
//			//得到条件中的一个
//			String gender = ct.getGender();
//			//如果得到的条件不为null，且不为""，证明用户填写了这个条件，进行追加
//			if(gender != null && !gender.trim().isEmpty()) {
//				sql.append("and gender=?");//性别不用模糊查询
//				al.add(gender);//添加到sql模板的参数			
//			}
//			
//			//得到条件中的一个
//			String cellphone = ct.getCellphone();
//			//如果得到的条件不为null，且不为""，证明用户填写了这个条件，进行追加
//			if(cellphone != null && !cellphone.trim().isEmpty()) {
//				sql.append("and cellphone LIKE ?");//使用模糊查询
//				al.add("%" + cellphone + "%");//添加到sql模板的参数			
//			}
//			
//			//得到条件中的一个
//			String email = ct.getEmail();
//			//如果得到的条件不为null，且不为""，证明用户填写了这个条件，进行追加
//			if(email != null && !email.trim().isEmpty()) {
//				sql.append("and email LIKE ?");//使用模糊查询
//				al.add("%" + email + "%");//添加到sql模板的参数			
//			}
//			
//			//执行sql语句，BeanListHandler处理结果集
//			List<Customer> list = qr.query(sql.toString(), new BeanListHandler<Customer>(Customer.class), al.toArray());
//			
//			return list;
//		} catch(Exception e) {
//			throw new RuntimeException(e);
//		}
	
	/***
	 * 分页模式的多条件组合查询
	 * @param pc 当前页码
	 * @param ps 每页记录数
	 * @param ct 条件
	 * @return
	 */
	public PageBean<Customer> query(int pc, int ps, Customer ct) {
		
		
		try {
			//sql模板前缀
			StringBuilder sql = new StringBuilder("SELECT * FROM t_customer WHERE 1=1 ");
			//添加到sql模板的参数
			ArrayList<Object> al = new ArrayList<Object>();
			
			//------------------设置好带那四个条件的sql模板-------------------
			PageBean<Customer> pb = new PageBean<Customer>();//分页数据对象
			
			String url = pb.getUrl();
			if(url == null) {
				pb.setUrl("");
			}
			
			//得到条件中的一个
			String cname = ct.getCname();
			//如果得到的条件不为null，且不为""，证明用户填写了这个条件，进行追加
			if(cname != null && !cname.trim().isEmpty()) {
				sql.append("and cname LIKE ?");//使用模糊查询
				al.add("%" + cname + "%");//添加到sql模板的参数
				//添加条件到pb的url，以防分页多条件查询时，丢失参数
				pb.setUrl(pb.getUrl() + "&cname=" + cname);
			}
			
			//得到条件中的一个
			String gender = ct.getGender();
			//如果得到的条件不为null，且不为""，证明用户填写了这个条件，进行追加
			if(gender != null && !gender.trim().isEmpty()) {
				sql.append("and gender=?");//性别不用模糊查询
				al.add(gender);//添加到sql模板的参数
				pb.setUrl(pb.getUrl() + "&gender=" + gender);
			}
			
			//得到条件中的一个
			String cellphone = ct.getCellphone();
			//如果得到的条件不为null，且不为""，证明用户填写了这个条件，进行追加
			if(cellphone != null && !cellphone.trim().isEmpty()) {
				sql.append("and cellphone LIKE ?");//使用模糊查询
				al.add("%" + cellphone + "%");//添加到sql模板的参数			
				pb.setUrl(pb.getUrl() + "&cellphone=" + cellphone);
			}
			
			//得到条件中的一个
			String email = ct.getEmail();
			//如果得到的条件不为null，且不为""，证明用户填写了这个条件，进行追加
			if(email != null && !email.trim().isEmpty()) {
				sql.append("and email LIKE ?");//使用模糊查询
				al.add("%" + email + "%");//添加到sql模板的参数			
				pb.setUrl(pb.getUrl() + "&email=" + email);
			}
			
			
			//设置pc和ps
			pb.setPc(pc);
			pb.setPs(ps);
			//设置总记录数
			String sql2 = "SELECT COUNT(*) FROM (" + sql.toString() + ") e";
			Number num = (Number) qr.query(sql2, new ScalarHandler(), al.toArray());
			int tr = num.intValue();
			pb.setTr(tr);
			
			//设置beanList
			String sql1 = "SELECT * FROM (" + sql.toString() + ") e ORDER BY cname LIMIT ?, ? ";
			al.add((pc-1)*ps);//第几行开始查询
			al.add(ps);//查询多少行
			List<Customer> beanList = qr.query(sql1, new BeanListHandler<Customer>(Customer.class), al.toArray());
			pb.setBeanList(beanList);
			return pb;
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
}
