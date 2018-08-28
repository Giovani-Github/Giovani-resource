package com.example.sqlite.junit;

import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

import com.example.sqlite.MySQLiteOpenHelper;

public class TestCase extends AndroidTestCase {
	
	public void test() {
		/*
		 * 创建SQLiteOpenHelper
		 * 
		 * AndroidTestCase#getContext()：获取虚拟上下文对象
		 * 数据库文件名：可以不给后缀。但是最好给出后缀
		 * 游标工厂给null，会使用默认的游标工厂
		 * 版本号为：1
		 */
		MySQLiteOpenHelper msqloh = new MySQLiteOpenHelper(getContext(), "poeple.db", null, 1);
		
		/*
		 * 获得数据库对象
		 * 如果数据库不存在，会先创建数据库，后获得。
		 * 如果存在，直接获得
		 * 得到的是一个可读可写的数据库
		 * 一般使用这个
		 */
		SQLiteDatabase db = msqloh.getWritableDatabase();
		
		//得到的是一个可读可写的数据库，但是在内部存储空间不足时，获得的是一个只读数据库
		//SQLiteDatabase db = msqloh.getReadableDatabase();
	}
}
