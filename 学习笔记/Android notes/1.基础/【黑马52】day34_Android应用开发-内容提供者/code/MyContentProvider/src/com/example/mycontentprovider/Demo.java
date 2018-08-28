package com.example.mycontentprovider;

import android.test.AndroidTestCase;

public class Demo extends AndroidTestCase {
	public void fun() {
		//创建数据库
		new MyOpenHelper(getContext()).getWritableDatabase();
	}
}
