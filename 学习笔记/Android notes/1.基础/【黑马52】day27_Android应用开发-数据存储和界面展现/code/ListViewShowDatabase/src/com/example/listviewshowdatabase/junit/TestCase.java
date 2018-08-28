package com.example.listviewshowdatabase.junit;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

import com.example.listviewshowdatabase.oh.MySQLiteOpenHelper;

public class TestCase extends AndroidTestCase {
	
	public void create() {
		MySQLiteOpenHelper oh = new MySQLiteOpenHelper(getContext(), "poeple.db", null, 1);
		SQLiteDatabase db = oh.getWritableDatabase();
		
		for(int i = 0; i < 50; i++) {
			ContentValues values = new ContentValues();
			values.put("name", "z"+i);
			values.put("sal", i);
			values.put("phone", "12335" + i);
			db.insert("person", null, values);
		}
	}
}
