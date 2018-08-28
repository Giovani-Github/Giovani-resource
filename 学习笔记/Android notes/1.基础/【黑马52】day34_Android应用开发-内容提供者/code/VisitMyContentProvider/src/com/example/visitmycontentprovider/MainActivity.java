package com.example.visitmycontentprovider;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    
    public void insert(View v) {
    	//通过内容提供者把数据插入MyContentProvider项目的数据库
    	//拿到ContentResolver
    	ContentResolver cr = getContentResolver();
    	//要插入的数据，封装到ContentValues
    	ContentValues values = new ContentValues();
    	values.put("name", "lisi2");
    	values.put("money", "13000");
    	
    	//插入数据
    	//
    	cr.insert(Uri.parse("content://com.example.mycontentprovider"), values);
    	
    }
    public void delete(View v) {
    	//通过内容提供者把数据从MyContentProvider项目的数据库中删除
    	//拿到ContentResolver
    	ContentResolver cr = getContentResolver();
    	
    	//要删除的while条件
    	String where = "name = ?";
    	//填充where条件
    	String[] selectionArgs = {"lisi2"};
    	
    	//删除
    	//它会通过uri，调用指定的内容提供者的delete方法
    	int i = cr.delete(Uri.parse("content://com.example.mycontentprovider"), where, selectionArgs);
    	//影响行数
    	System.out.println(i);
    }
    
    public void update(View v) {
    	//通过内容提供者修改MyContentProvider项目的数据库中的数据
    	//拿到ContentResolver
    	ContentResolver cr = getContentResolver();
    	
    	//修改后的新数据
    	ContentValues values = new ContentValues();
    	values.put("name", "lisiffff");
    	//where条件
    	String where = "name = ?";
    	//填充where条件
    	String[] selectionArgs = {"lisi"}; 
    	
    	//修改
    	//它会通过uri，调用指定的内容提供者的update方法
    	int i = cr.update(Uri.parse("content://com.example.mycontentprovider"), values, where, selectionArgs);
    	//影响行数
    	System.out.println(i);
    	
    }
    public void query(View v) {
    	//通过内容提供者查询出MyContentProvider项目的数据库中的数据
    	//拿到ContentResolver
    	ContentResolver cr = getContentResolver();
    	
    	//要查询的字段，null查全部
    	String[] projection = null;
    	//where条件
    	String selection = null;
    	//填充where条件的
    	String[] selectionArgs = null;
    	//排序
    	String sortOrder = null;
    	
    	//查询
    	//它会通过uri，调用指定的内容提供者的query方法
    	Cursor cursor = cr.query(Uri.parse("content://com.example.mycontentprovider"), projection, selection, selectionArgs, sortOrder);
    	
    	//对结果集进行处理得到想要的数据
    	while(cursor.moveToNext()) {
			String name = cursor.getString(cursor.getColumnIndex("name"));
			System.out.println(name);
    	}
    	
    }
}
