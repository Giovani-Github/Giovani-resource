package com.example.junit;

import android.test.AndroidTestCase;

public class TestCase extends AndroidTestCase {
	public void test() {
		int re = add(1, 2);
		//���ԣ������������ֵ��ʵ��ֵ�Ƿ�һ��
		//��һ��������ֵ
		//�ڶ�����ʵ��ֵ
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
