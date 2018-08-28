package jdbc.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.Test;

/*
 * 数据层，对数据库的表进行操作
 * */
public class UserDao {
	/*
	 * 修改account表中指定条件的数据
	 * 
	 * 同一事务中所有的操作，都在使用同一个Connection对象！
	 * 而数据层是项目中最底层的操作，所以Connection对象由调用者来提供
	 * */
	
	/***
	 * 
	 * @param con 有调用者提供数据库连接对象Connection
	 * @param name 对account表中，哪个name进行操作
	 * @param balance 减去或加上的金额
	 */
	public void setAccount(Connection con, String name, Double balance) {
		//准备sql模板
		String sql = "UPDATE account SET balance=balance+? WHERE name=?";
		
		PreparedStatement pstmt = null;
		try {
			//得到sql语句发送者
			pstmt = con.prepareStatement(sql);
			
			//为模板的?号赋值
			pstmt.setDouble(1, balance);
			pstmt.setString(2, name);
			
			//发送sql语句
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if(pstmt != null) pstmt.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}
}
