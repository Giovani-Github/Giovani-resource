package com.example.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {
	/**
	 * ���췽��
	 * @param context�������Ķ���
	 * @param name�����ݿ������
	 * @param factory���α깤�����α꣺������е��Ǹ��αꡣ�����ֱ�����ɽ�������С���null����ʹ��Ĭ�ϵ��α깤��
	 * @param version���汾�ţ��汾�ŵ����Ӿ������ݿ������
	 */
	public MySQLiteOpenHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
	}
	
	
	/**
	 * �����ݿⴴ��ʱ������
	 * @param db: ���ݿ�Ķ���
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
		System.out.println("���ݿ�������");
	}

}
