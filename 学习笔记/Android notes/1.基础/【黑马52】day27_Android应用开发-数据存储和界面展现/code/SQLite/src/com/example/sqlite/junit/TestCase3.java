package com.example.sqlite.junit;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

import com.example.sqlite.MySQLiteOpenHelper;

public class TestCase3 extends AndroidTestCase {
	MySQLiteOpenHelper oh;
	SQLiteDatabase db;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		oh = new MySQLiteOpenHelper(getContext(), "poeple.db", null,	1);
		db = oh.getWritableDatabase();
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		
		db.close();
	}
	
	/**
	 * ��
	 */
	public void insertApi() {
		//��Ҫ���������ȫ����װ��ContentValues����
		ContentValues values = new ContentValues();
		values.put("name", "����");
		values.put("sal", 12000);
		
		/*
		 * ʹ��api��������
		 * ��������
		 * nullColumnHack��һ���null�����values=Null���߳���Ϊ0ʱ����Ϊ�������ݲ���nullֵ
		 * �������������
		 * 
		 * �᷵��һ����id���������²�����һ�е���id������������������ص���-1��˵������ʧ��
		 */
		long id = db.insert("person", null, values);
		System.out.println(id);
		
	}
	
	/**
	 * ɾ
	 */
	public void deleteApi() {
		String table = "person"; // ����Ҫ�����ı�
		String whereClause = "name=? AND _id=?"; //����where����		
		String[] whereArgs = {"����", "3"};//�������where����վλ���Ĳ���
		//ִ��ɾ������,�᷵��Ӱ�������
		int i = db.delete(table, whereClause, whereArgs);
		
		System.out.println(i);
	}
	
	/**
	 * ��
	 */
	public void updateApi() {
		String table = "person";
		
		//����������
		ContentValues values = new ContentValues();
		values.put("sal", 1200);
		
		String whereClause = "name=?";
		String[] whereArgs = {"����"};
		
		//ִ�иĲ������᷵��Ӱ������
		int i = db.update(table, values, whereClause, whereArgs);
		
		System.out.println(i);
	}
	
	/**
	 * ��
	 */
	public void selectApi() {
		String table = "person";
		String[] columns = {"name", "sal"};//����Ҫ��ѯ���У����Ҫ��ѯȫ������null
		String selection = "name=?";//where����
		String[] selectionArgs = {"����"};//���where����ռλ��
		String groupBy = null;//��������
		String having = null;//����������
		String orderBy = null;//��������
		String limit = null;//��ҳ����		
		
		//ִ�в�ѯ���������ؽ����
		Cursor cursor = db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy, limit);
		
		//�Խ�������в����������Ҫ������
		while(cursor.moveToNext()) {
			String name = cursor.getString(cursor.getColumnIndex("name"));
			String sal = cursor.getString(cursor.getColumnIndex("sal"));
			
			System.out.println(name + ":" + sal);
		}
	}
	
	//����
	public void transaction() {
		try {
			db.beginTransaction();//��������
			
			ContentValues values = new ContentValues();
			values.put("sal", 1000);
			db.update("person", values, "name=?", new String[]{"����"});
			
			values.clear();//�������
			values.put("sal", 12200);
			db.update("person", values, "name=?", new String[]{"����"});
			
			//��;�׳����쳣������db.setTransactionSuccessful();���û��ִ�У���ô����ͻ���finally�����лع�
			int i = 10/0;
			
			db.setTransactionSuccessful();//��������Ϊִ�гɹ�
		} catch(Exception e) {
			throw new RuntimeException(e);
		} finally {
			/*
			 * �ر�����ͬʱ�ύ
			 * ��������Ѿ�����Ϊִ�гɹ�����ôsql������Ч��
			 * �������û�б�����Ϊִ�гɹ���Ҳ����db.setTransactionSuccessful();���û�б�ִ��
			 * 	��ô������ع�
			 */
			db.endTransaction();
		}
	}
}
