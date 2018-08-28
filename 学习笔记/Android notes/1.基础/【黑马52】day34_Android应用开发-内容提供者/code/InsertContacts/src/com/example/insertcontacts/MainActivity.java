package com.example.insertcontacts;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //往raw_contacts表中，插入联系人id
        //要先通过计算主键，得到我们要插入的id是多少
        //查询出所有主键
        ContentResolver cr = getContentResolver();
        Cursor contactsId = cr.query(Uri.parse("content://com.android.contacts/raw_contacts"), new String[] {"contact_id"}, null, null, null);
        
        //默认联系人id为1，如果系统没有联系人
        int contact_id = 1;
        //指针移动到最后一行，如果有数据就返回true
        if(contactsId.moveToLast()) {
        	//获取到最后一个联系人id
        	int _id = contactsId.getInt(contactsId.getColumnIndex("contact_id"));
        	
        	//我们要插入的联系人id，是最后一个联系人主键+1
        	contact_id = ++_id;
        }
        
        //联系人id计算出来了，就往raw_contacts表中插入
        ContentValues values = new ContentValues();
        values.put("contact_id", contact_id);
        cr.insert(Uri.parse("content://com.android.contacts/raw_contacts"), values);
        
        //往data表中插入数据
        //插入名字，（作为一行存在，有三个字段）
        values.clear();
        values.put("data1", "李四");
        values.put("mimetype", "vnd.android.cursor.item/name");
        values.put("raw_contact_id", contact_id);
        cr.insert(Uri.parse("content://com.android.contacts/data"), values);
        
        //插入号码
        values.clear();
        values.put("data1", "135469822");
        values.put("mimetype", "vnd.android.cursor.item/phone_v2");
        values.put("raw_contact_id", contact_id);
        cr.insert(Uri.parse("content://com.android.contacts/data"), values);
    }
}
