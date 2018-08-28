package com.example.bsms;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlSerializer;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Xml;
import android.view.View;

import com.example.bsms.domain.Message;

public class MainActivity extends Activity {

	private List<Message> list; // 短信都保存在这里
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = new ArrayList<Message>();
    }
    
    public void click(View v) {
    	
    	//获取到系统所有短信
    	ContentResolver cr = getContentResolver();
    	Cursor cursor = cr.query(Uri.parse("content://sms"), new String[] {"body", "date", "address", "type"}, null, null, null);
    	
    	while(cursor.moveToNext()) {
    		String body = cursor.getString(cursor.getColumnIndex("body"));
    		String date = cursor.getString(cursor.getColumnIndex("date"));
    		String address = cursor.getString(cursor.getColumnIndex("address"));
    		String type = cursor.getString(cursor.getColumnIndex("type"));
    		
    		//保存在集合中
    		Message msg = new Message(body, date, address, type);
    		list.add(msg);	
    	}
    	
    	
    	//在使用序列化器，把集合中的数据，保存到xml文件中
    	XmlSerializer xs = Xml.newSerializer();
    	File file = new File(Environment.getExternalStorageDirectory(), "sms.xml");
    	FileOutputStream fos = null;
    	try {
			fos = new FileOutputStream(file);
			
			xs.setOutput(fos, "utf-8");
			xs.startDocument("utf-8", true);
			xs.startTag(null, "message");
			
			for (Message ms : list) {
				xs.startTag(null, "sms");
				
				xs.startTag(null, "body");
				xs.text(ms.getBody());
				xs.endTag(null, "body");
				
				xs.startTag(null, "date");
				xs.text(ms.getDate());
				xs.endTag(null, "date");
			
				xs.startTag(null, "address");
				xs.text(ms.getAddress());
				xs.endTag(null, "address");
				
				xs.startTag(null, "type");
				xs.text(ms.getType());
				xs.endTag(null, "type");

				xs.endTag(null, "sms");
			}
			
			xs.endTag(null, "message");
			xs.endDocument();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
    
}
