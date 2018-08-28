package jndi.test;

import java.io.IOException;
import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.naming.NamingException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;



public class JNDIDemoServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			//获取JNDI上下文对象
			Context cxt = new InitialContext();
			
			//查询出入口(配置好的文件，进入文件中)，这是固定这么写的
			Context envContext = (Context)cxt.lookup("java:comp/env");
			//再在查出入口的情况下，查找出我们的资源，即数据库连接池对象。jdbc/dataSource对应<Resource>中配置的name值
			DataSource dataSource = (ComboPooledDataSource)envContext.lookup("jdbc/dataSource");
			
			Connection con = dataSource.getConnection();
			System.out.println(con);
			con.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
