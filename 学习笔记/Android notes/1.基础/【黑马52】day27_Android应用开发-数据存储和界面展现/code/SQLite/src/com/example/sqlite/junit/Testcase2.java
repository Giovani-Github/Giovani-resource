package com.example.sqlite.junit;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

import com.example.sqlite.MySQLiteOpenHelper;

public class Testcase2 extends AndroidTestCase {
	
	//��ʱ���Կ�ܻ�û�г�ʼ����ϣ�û�����������Ķ���
//	MySQLiteOpenHelper oh = new MySQLiteOpenHelper(getContext(), "poeple.db", null, 1);
		
	MySQLiteOpenHelper oh;
	SQLiteDatabase db;
	
	//���Կ�ܳ�ʼ�����֮���ڲ��Է���ִ��֮ǰ���˷�������
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		oh = new MySQLiteOpenHelper(getContext(), "poeple.db", null, 1);
		db = oh.getWritableDatabase();
	}
	
	//���Է���ִ�����֮�󣬴˷�������
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		
		//�ر����ݿ⡣���Բ��أ����ǹ��ϱȽϺ�
		db.close();
	}
	
	//��������
	public void insert() {
		String sql = "INSERT INTO person(name, phone, sal) VALUES(?, ?, ?)";
		Object[] param1 = {"����", 110, 111};
		db.execSQL(sql, param1);
		
		Object[] param2 = {"����", 120, 222};
		db.execSQL(sql, param2);
	
	}
	
	//ɾ������
	public void delete() {
		String sql ="DELETE FROM person WHERE name=?";
		Object[] param = {"����"};
		db.execSQL(sql, param);
		
	}
	
	//�޸�����
	public void update() {
		String sql = "UPDATE person SET phone=? WHERE name=?";
		Object[] param = {"1235", "����"};
		db.execSQL(sql, param);
		
	}
	
	//��ѯ����
	public void select() {
		String sql = "SELECT name, sal FROM person";
		
		//�飬ʹ�õ�����һ��api������sql�����ʺŵĲ���������һ�������
		Cursor cursor = db.rawQuery(sql, null);
		
		/*
		 * �Խ�������в������õ���Ҫ������
		 * ��Ϊ��������α�տ�ʼ�ǵ�һ�е�����һ�С�
		 * moveToNext(),�ѽ�����ƶ�����һ�С����������һ�У�����true����֮������false
		 */
		while(cursor.moveToNext()) {
			/*
			 * cursor#getString(int columnIndex):��������Ӱ��ȡ��ֵ����Ҫ����������
			 * 	��������Ҫ���ݲ�ѯ�����ȷ����
			 * 	SELECT name, sal FROM person: name����0������sal����1����
			 * 	SELECT name, phone, sal FROM person: name����0������phone����1������sal����2����
			 * 	SELECT * FROM person�� _id����0������name����1����.....
			 * 
			 * ��Ϊ�鷳������Google�������ṩ��һ��api��
			 * 	cursor.getColumnIndex("name")�����������ƣ���ȡ������
			 */
			String name = cursor.getString(cursor.getColumnIndex("name"));
			String sal = cursor.getString(cursor.getColumnIndex("sal"));
			
			System.out.println(name + ":" + sal);
		}
	}
}
