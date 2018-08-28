package com.example.junit;

import android.test.AndroidTestCase;

public class TestCase extends AndroidTestCase {
	public void test() {
		int re = add(1, 2);
		//断言，用来检测期望值和实际值是否一致
		//第一个：期望值
		//第二个：实际值
		assertEquals(3, re);
		
		chu(1, 1);
	}
	
	public int add(int i, int j) {
		return i + j;
	}
	
	public void chu(int i, int j) {
		int a = i / j;
	}
}
