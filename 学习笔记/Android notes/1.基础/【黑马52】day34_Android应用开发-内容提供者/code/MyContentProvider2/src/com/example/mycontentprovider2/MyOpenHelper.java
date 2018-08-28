package com.example.mycontentprovider2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MyOpenHelper extends SQLiteOpenHelper {

	public MyOpenHelper(Context context) {
		super(context, "person.db", null, 3);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE person(" +
				"_id INTEGER PRIMARY KEY AUTOINCREMENT," +
				"name CHAR(10)," +
				"money INTEGER" +
				");");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("CREATE TABLE person2(" +
				"_id INTEGER PRIMARY KEY AUTOINCREMENT," +
				"name CHAR(10)" +
				");");
		System.out.println("声级计了");
	}

}
