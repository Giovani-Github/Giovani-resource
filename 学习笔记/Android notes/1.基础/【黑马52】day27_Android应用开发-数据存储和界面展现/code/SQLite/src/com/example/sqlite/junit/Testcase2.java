package com.example.sqlite.junit;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

import com.example.sqlite.MySQLiteOpenHelper;

public class Testcase2 extends AndroidTestCase {
	
	//此时测试框架还没有初始化完毕，没有虚拟上下文对象
//	MySQLiteOpenHelper oh = new MySQLiteOpenHelper(getContext(), "poeple.db", null, 1);
		
	MySQLiteOpenHelper oh;
	SQLiteDatabase db;
	
	//测试框架初始化完毕之后，在测试方法执行之前，此方法调用
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		oh = new MySQLiteOpenHelper(getContext(), "poeple.db", null, 1);
		db = oh.getWritableDatabase();
	}
	
	//测试方法执行完毕之后，此方法调用
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		
		//关闭数据库。可以不关，但是关上比较好
		db.close();
	}
	
	//插入数据
	public void insert() {
		String sql = "INSERT INTO person(name, phone, sal) VALUES(?, ?, ?)";
		Object[] param1 = {"张三", 110, 111};
		db.execSQL(sql, param1);
		
		Object[] param2 = {"李四", 120, 222};
		db.execSQL(sql, param2);
	
	}
	
	//删除数据
	public void delete() {
		String sql ="DELETE FROM person WHERE name=?";
		Object[] param = {"张三"};
		db.execSQL(sql, param);
		
	}
	
	//修改数据
	public void update() {
		String sql = "UPDATE person SET phone=? WHERE name=?";
		Object[] param = {"1235", "李四"};
		db.execSQL(sql, param);
		
	}
	
	//查询数据
	public void select() {
		String sql = "SELECT name, sal FROM person";
		
		//查，使用的是另一个api，给出sql语句和问号的参数。返回一个结果集
		Cursor cursor = db.rawQuery(sql, null);
		
		/*
		 * 对结果集进行操作，得到想要的数据
		 * 因为结果集的游标刚开始是第一行的上面一行。
		 * moveToNext(),把结果集移动到下一行。如果存在下一行，返回true。反之，返回false
		 */
		while(cursor.moveToNext()) {
			/*
			 * cursor#getString(int columnIndex):根据列缩影获取列值，需要给出列索引
			 * 	列索引需要根据查询语句来确定：
			 * 	SELECT name, sal FROM person: name就是0索引，sal就是1索引
			 * 	SELECT name, phone, sal FROM person: name就是0索引，phone就是1索引，sal就是2索引
			 * 	SELECT * FROM person： _id就是0索引，name就是1索引.....
			 * 
			 * 因为麻烦，所以Google给我们提供了一个api：
			 * 	cursor.getColumnIndex("name")：根据列名称，获取列索引
			 */
			String name = cursor.getString(cursor.getColumnIndex("name"));
			String sal = cursor.getString(cursor.getColumnIndex("sal"));
			
			System.out.println(name + ":" + sal);
		}
	}
}
