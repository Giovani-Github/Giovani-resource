package jdbc.test;

import java.sql.Connection;
import java.sql.SQLException;

import utils.JdbcUtils;

/*
 * 业务层，对事务进行操作
 * */
public class UserService {
	/***
	 * 转账事务
	 * @param from 从谁转
	 * @param to 转给谁
	 * @param balance 转多少
	 */
	public void zhuan(String from, String to, double balance) {
		//依赖数据层
		UserDao dao = new UserDao();
		/*
		 * 我们暂时在这里提供Connection，但是要记住，java.sql包下的东西不能出现在数据层以外
		 * 我们这是犯了大忌，但是暂时先这样，以后会解决这个问题
		 * */
		
		//设置Connectin对象的引用
		Connection con = null;
		try {
			//获取Connection对象
			con = JdbcUtils.getConnection();
			
			//开启事务
			con.setAutoCommit(false);
			
			//处理转账事务
			dao.setAccount(con, from, -balance);//先从转账者的账户扣除金额
			dao.setAccount(con, to, balance);//再向被转账者加上金额
			
			
			//提交事务
			con.commit();
		} catch (Exception e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				throw new RuntimeException(e);
			}
		}
	}
}
