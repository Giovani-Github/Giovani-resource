package com.example.activityreturndata;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    
    //启动联系人activity
    public void startContacts(View v) {
    	Intent intent = new Intent(this, ContactsActivity.class);
    	startActivityForResult(intent, 1);
    }
    
    //启动快捷回复activity
    public void startShortcut(View v) {
    	Intent intent = new Intent(this, ShortcutActivity.class);
    	startActivityForResult(intent, 2);
    }
    
    //有activity在销毁时返回了数据，就会调用这个方法
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	
    	if(requestCode == 1) { // 判断是哪个activity被销毁并返回数据
    		String name = data.getStringExtra("name"); //得到返回的数据
        	EditText et_contacts = (EditText) findViewById(R.id.et_contacts);
        	et_contacts.setText(name);
    	} else {
    		String content = data.getStringExtra("content");
        	EditText et_contacts = (EditText) findViewById(R.id.et_content);
        	et_contacts.setText(content);
    	}

    }
}
