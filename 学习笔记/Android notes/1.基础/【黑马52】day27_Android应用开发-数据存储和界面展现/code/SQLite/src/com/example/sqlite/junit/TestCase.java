package com.example.sqlite.junit;

import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

import com.example.sqlite.MySQLiteOpenHelper;

public class TestCase extends AndroidTestCase {
	
	public void test() {
		/*
		 * ����SQLiteOpenHelper
		 * 
		 * AndroidTestCase#getContext()����ȡ���������Ķ���
		 * ���ݿ��ļ��������Բ�����׺��������ø�����׺
		 * �α깤����null����ʹ��Ĭ�ϵ��α깤��
		 * �汾��Ϊ��1
		 */
		MySQLiteOpenHelper msqloh = new MySQLiteOpenHelper(getContext(), "poeple.db", null, 1);
		
		/*
		 * ������ݿ����
		 * ������ݿⲻ���ڣ����ȴ������ݿ⣬���á�
		 * ������ڣ�ֱ�ӻ��
		 * �õ�����һ���ɶ���д�����ݿ�
		 * һ��ʹ�����
		 */
		SQLiteDatabase db = msqloh.getWritableDatabase();
		
		//�õ�����һ���ɶ���д�����ݿ⣬�������ڲ��洢�ռ䲻��ʱ����õ���һ��ֻ�����ݿ�
		//SQLiteDatabase db = msqloh.getReadableDatabase();
	}
}
