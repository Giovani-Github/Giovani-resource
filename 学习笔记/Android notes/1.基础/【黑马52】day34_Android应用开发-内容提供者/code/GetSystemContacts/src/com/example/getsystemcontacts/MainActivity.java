package com.example.getsystemcontacts;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import com.example.getsystemcontacts.domain.Contact;

public class MainActivity extends Activity {
	
	private List<Contact> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = new ArrayList<Contact>();
    
        //通过联系人应用的内容提供者，获取联系人数据库中的信息
        ContentResolver cr = getContentResolver();
        //先到raw_contacts表获取所有联系人id
        Cursor contactsId = cr.query(Uri.parse("content://com.android.contacts/raw_contacts"), new String[] {"contact_id"}, null, null, null);
        
        //得到每个id，通过id，去查找data表，得到每个联系人的全部信息
        while(contactsId.moveToNext()) { 
        	//得到一个id
        	String cId = contactsId.getString(contactsId.getColumnIndex("contact_id"));
System.out.println(cId);
        	//通过id，查询指定id的联系人的信息，实际上查询的是视图view_data
        	Cursor contactsDate = cr.query(Uri.parse("content://com.android.contacts/data"), new String[] {"data1", "mimetype"}, "raw_contact_id = ?", new String[] {cId}, null);
        	
System.out.println(contactsDate);
        	
			Contact contact = new Contact();
        	//拿出所有信息，判断他的类型，再存放到集合中
        	while(contactsDate.moveToNext()) {
        		String mimetype = contactsDate.getString(contactsDate.getColumnIndex("mimetype"));
        		String data1 = contactsDate.getString(contactsDate.getColumnIndex("data1"));
        		
        		if(mimetype.equals("vnd.android.cursor.item/phone_v2")) { // 如果是电话类型
        			contact.setPhone(data1);
        		} else if(mimetype.equals("vnd.android.cursor.item/name")) { //如果是名字
        			contact.setName(data1);
        		} else if(mimetype.equals("vnd.android.cursor.item/email_v2")) { // 如果是邮箱
        			contact.setEmail(data1);
        		}
        	}
        	list.add(contact);
        }
        
        
        for (Contact c : list) {
			System.out.println(c.toString());
		}
    }
}
