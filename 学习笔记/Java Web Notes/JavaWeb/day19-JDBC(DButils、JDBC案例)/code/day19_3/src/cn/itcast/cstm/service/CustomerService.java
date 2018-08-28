package cn.itcast.cstm.service;

import java.util.List;

import cn.itcast.cstm.dao.CustomerDao;
import cn.itcast.cstm.domain.Customer;
import cn.itcast.cstm.domain.PageBean;

/*
 * 业务层，依赖数据层
 * */
public class CustomerService {
	private CustomerDao customerDao = new CustomerDao();
	
	/***
	 * 添加用户方法
	 * @param c
	 */
	public void add(Customer c) {
		//直接调用dao的add方法
		customerDao.add(c);
	}
	
	/***
	 * 查询所有用户
	 * @return 返回的是所有用户对象
	 * 
	 */
//	public List<Customer> findAll() {
//		//直接调用dao层的findAll
//		return customerDao.findAll();
//	}

	/*
	 * 根据当前要查询的页码pc和每页记录数，获取分页数据PageBean
	 * */
	public PageBean<Customer> findAll(int pc, int ps) {
		return customerDao.findAll(pc, ps);
	}
	
	/***
	 * 加载方法，即使用主键cid查询到Customer对象
	 * @param cid
	 * @return
	 */
	public Customer load(String cid) {
		return customerDao.findByCid(cid);
	}

	/***
	 * 编辑方法，更改客户的信息
	 * @param c
	 */
	public void edit(Customer c) {
		customerDao.edit(c);
	}

	/**
	 * 根据主键删除客户
	 * @param cid
	 */
	public void delete(String cid) {
		customerDao.delete(cid);
	}

	/**
	 * 多条件组合查询
	 * @param ct
	 * @return
	 */
//	public List<Customer> query(Customer ct) {
//		return customerDao.query(ct);
//	}
	
	/***
	 * 分页模式的多条件组合查询
	 * @param ct
	 * @return
	 */
	public PageBean<Customer> query(int pc, int ps, Customer ct) {
		return customerDao.query(pc, ps, ct);
	}
}
