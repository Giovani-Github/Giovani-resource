package com.example.insertsms;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.view.Menu;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    
        //往系统短信数据库插入断线
        ContentResolver cr = getContentResolver();
        ContentValues values = new ContentValues();
        values.put("body", "haha");
        values.put("address", 135);
        values.put("date", System.currentTimeMillis());
        values.put("type", 1);
        cr.insert(Uri.parse("content://sms"), values);
    }
}
