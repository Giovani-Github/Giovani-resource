package com.example.sqlite.junit;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

import com.example.sqlite.MySQLiteOpenHelper;

public class TestCase3 extends AndroidTestCase {
	MySQLiteOpenHelper oh;
	SQLiteDatabase db;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		oh = new MySQLiteOpenHelper(getContext(), "poeple.db", null,	1);
		db = oh.getWritableDatabase();
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		
		db.close();
	}
	
	/**
	 * 增
	 */
	public void insertApi() {
		//把要插入的数据全部封装进ContentValues对象
		ContentValues values = new ContentValues();
		values.put("name", "赵六");
		values.put("sal", 12000);
		
		/*
		 * 使用api插入数据
		 * 给出表名
		 * nullColumnHack：一般给null。如果values=Null或者长度为0时。会为表中数据插入null值
		 * 给出插入的数据
		 * 
		 * 会返回一个行id：返回最新插入那一行的行id，就是主键。如果返回的是-1，说明插入失败
		 */
		long id = db.insert("person", null, values);
		System.out.println(id);
		
	}
	
	/**
	 * 删
	 */
	public void deleteApi() {
		String table = "person"; // 给出要操作的表
		String whereClause = "name=? AND _id=?"; //给出where条件		
		String[] whereArgs = {"李四", "3"};//给出填充where条件站位符的参数
		//执行删除操作,会返回影响的行数
		int i = db.delete(table, whereClause, whereArgs);
		
		System.out.println(i);
	}
	
	/**
	 * 改
	 */
	public void updateApi() {
		String table = "person";
		
		//给出新数据
		ContentValues values = new ContentValues();
		values.put("sal", 1200);
		
		String whereClause = "name=?";
		String[] whereArgs = {"李四"};
		
		//执行改操作，会返回影响行数
		int i = db.update(table, values, whereClause, whereArgs);
		
		System.out.println(i);
	}
	
	/**
	 * 查
	 */
	public void selectApi() {
		String table = "person";
		String[] columns = {"name", "sal"};//给出要查询的列，如果要查询全部，给null
		String selection = "name=?";//where条件
		String[] selectionArgs = {"李四"};//填充where条件占位符
		String groupBy = null;//分组条件
		String having = null;//分组后的条件
		String orderBy = null;//排序条件
		String limit = null;//分页条件		
		
		//执行查询操作，返回结果集
		Cursor cursor = db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy, limit);
		
		//对结果集进行操作，获得想要的数据
		while(cursor.moveToNext()) {
			String name = cursor.getString(cursor.getColumnIndex("name"));
			String sal = cursor.getString(cursor.getColumnIndex("sal"));
			
			System.out.println(name + ":" + sal);
		}
	}
	
	//事务
	public void transaction() {
		try {
			db.beginTransaction();//开启事务
			
			ContentValues values = new ContentValues();
			values.put("sal", 1000);
			db.update("person", values, "name=?", new String[]{"李四"});
			
			values.clear();//清空数据
			values.put("sal", 12200);
			db.update("person", values, "name=?", new String[]{"赵六"});
			
			//中途抛出了异常，导致db.setTransactionSuccessful();语句没有执行，那么事务就会在finally语句块中回滚
			int i = 10/0;
			
			db.setTransactionSuccessful();//设置事务为执行成功
		} catch(Exception e) {
			throw new RuntimeException(e);
		} finally {
			/*
			 * 关闭事务，同时提交
			 * 如果事务已经设置为执行成功，那么sql语句就生效了
			 * 如果事务没有被设置为执行成功，也就是db.setTransactionSuccessful();语句没有被执行
			 * 	那么就事务回滚
			 */
			db.endTransaction();
		}
	}
}
