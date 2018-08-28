package com.example.getsystemsms;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //通过内容提供者，把系统短信数据库里的所有短信读取出来
        //拿到ContentResolver
        ContentResolver cr = getContentResolver();
        //查询所有内容，所以不用在uri中加路径，且只查询sms表里的四个字段：内容，时间，对方号码，发送还是接收
        Cursor cursor = cr.query(Uri.parse("content://sms"), new String[] {"body", "date", "address", "type"}, null, null, null);
        
        //拿到结果集，解析出所有数据
        while(cursor.moveToNext()) {
        	String body = cursor.getString(cursor.getColumnIndex("body"));
        	long date = cursor.getLong(cursor.getColumnIndex("date"));
        	String address = cursor.getString(cursor.getColumnIndex("address"));
        	int type = cursor.getShort(cursor.getColumnIndex("type"));
        	
        	System.out.println(body + ":" + date + ":" + address + ":" + type);
        }
    }
}
