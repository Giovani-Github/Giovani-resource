package com.example.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {
	/**
	 * 构造方法
	 * @param context：上下文对象
	 * @param name：数据库的名称
	 * @param factory：游标工厂。游标：结果集中的那个游标。在这里，直接理解成结果集就行。给null，会使用默认的游标工厂
	 * @param version：版本号，版本号的增加就是数据库的升级
	 */
	public MySQLiteOpenHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
	}
	
	
	/**
	 * 在数据库创建时被调用
	 * @param db: 数据库的对象
	 */
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE person(" +
				"_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
				"name char(10), " +
				"phone cahr(20), " +
				"sal char(20)" +
				")");
	}
	
	/**
	 * 
	 */
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		System.out.println("数据库升级了");
	}

}
