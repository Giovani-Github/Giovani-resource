package com.example.sendsms;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    
    //���Ͱ�ť�����ʱ����
    public void send(View v) {
    	//��Ϊ���Ų�������api����ֱ�ӵ��ã����в������ö����ˡ�ֱ�ӵ��ö��ŵ�API
    	
    	//�õ��û��Ľ����ߺ��������
    	EditText et_phone = (EditText) findViewById(R.id.et_phone);
    	EditText et_content = (EditText) findViewById(R.id.et_content);
    	
    	String phone = et_phone.getText().toString();
    	String content = et_content.getText().toString();
    	
    	//1. ��ö��Ź�����
    	SmsManager sm = SmsManager.getDefault();
    	
    	/*
    	 * 2. ���Ͷ���
    	 * 	> ��Ҫ�������Ž�����
    	 * 	> ���ŷ������ĵĺ��롣ֱ�Ӹ�null
    	 * 	> ��������
    	 * 	> ���ŷ��ͳɹ���Ĺ㲥��null
    	 * 	> ���ű��Է��ɹ����պ�Ĺ㲥�� null
    	 */
    	//sm.sendTextMessage(phone, null, content, null, null);
    	
    	//������ŵ����ݹ������޷����͵ģ��������ǰ����ݽ�ȡ�ɼ������ݡ��ֶη���
    	ArrayList<String> al = sm.divideMessage(content);
    	for(String sms : al) {
    		sm.sendTextMessage(phone, null, sms, null, null);
    	}
    }
}
