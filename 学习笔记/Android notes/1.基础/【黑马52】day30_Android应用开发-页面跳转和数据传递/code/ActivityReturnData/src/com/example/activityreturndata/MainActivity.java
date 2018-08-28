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
    
    //������ϵ��activity
    public void startContacts(View v) {
    	Intent intent = new Intent(this, ContactsActivity.class);
    	startActivityForResult(intent, 1);
    }
    
    //������ݻظ�activity
    public void startShortcut(View v) {
    	Intent intent = new Intent(this, ShortcutActivity.class);
    	startActivityForResult(intent, 2);
    }
    
    //��activity������ʱ���������ݣ��ͻ�����������
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	
    	if(requestCode == 1) { // �ж����ĸ�activity�����ٲ���������
    		String name = data.getStringExtra("name"); //�õ����ص�����
        	EditText et_contacts = (EditText) findViewById(R.id.et_contacts);
        	et_contacts.setText(name);
    	} else {
    		String content = data.getStringExtra("content");
        	EditText et_contacts = (EditText) findViewById(R.id.et_content);
        	et_contacts.setText(content);
    	}

    }
}
