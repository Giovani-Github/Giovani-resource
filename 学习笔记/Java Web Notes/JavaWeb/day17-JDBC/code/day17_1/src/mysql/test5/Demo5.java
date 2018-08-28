package mysql.test5;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.rowset.serial.SerialBlob;

import mysql.test4.JdbcUtils;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

public class Demo5 {
	/*
	 * 大数据
	 * 写入MP3到数据库
	 * */
	@Test
	public void fun1() throws Exception {
		//1.得到Connection
		Connection con = JdbcUtils.getConnection();
		//2.设置sql模板
		String sql = "INSERT INTO tab_bin VALUES(?,?,?)";
		//3.得到PrepareStatement
		PreparedStatement pstmt = con.prepareStatement(sql);
		//4.为模板赋值
		pstmt.setInt(1, 1);
		pstmt.setString(2, "a.jpg");
		//5.使用IOUtils的toByteArray方法，把mp3转换为字节
		byte[] bytes = IOUtils.toByteArray(new FileInputStream("E:/a.jpg"));
		//6.把字节文件转换为Blob类型
		Blob blob = new SerialBlob(bytes);
		pstmt.setBlob(3, blob);
		//执行sql语句
		pstmt.executeUpdate();
	}
	
	/*
	 * 从数据库读取MP3到文件中
	 * */
	@Test
	public void fun2() throws Exception {
		//1.得到Connection
		Connection con = JdbcUtils.getConnection();
		//2.设置sql模板
		String sql = "SELECT * FROM tab_bin";
		//3.得到PrepareStatement
		PreparedStatement pstmt = con.prepareStatement(sql);
		//4.执行sql语句,得到结果集
		ResultSet rs = pstmt.executeQuery();
		
		//获取结果集中，data列数据
		if(rs.next()) {
			Blob blob = rs.getBlob("data");
			
			//把Blob变成硬盘上的文件
			/*
			 * 1.通过Blob得到读取流对象
			 * 2.自己创建写入流对象
			 * 3.把读取流中的数据写入到写入流中
			 * 	使用IOUtils.copy(intput,output);
			 * */
			InputStream in = blob.getBinaryStream();
			OutputStream out = new FileOutputStream("e:/b.jpg");
			IOUtils.copy(in, out);
		}
	}
}
