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
    
    //发送按钮被点击时调用
    public void send(View v) {
    	//因为短信拨号器的api可以直接调用，所有不用设置动作了。直接调用短信的API
    	
    	//拿到用户的接收者号码和内容
    	EditText et_phone = (EditText) findViewById(R.id.et_phone);
    	EditText et_content = (EditText) findViewById(R.id.et_content);
    	
    	String phone = et_phone.getText().toString();
    	String content = et_content.getText().toString();
    	
    	//1. 获得短信管理器
    	SmsManager sm = SmsManager.getDefault();
    	
    	/*
    	 * 2. 发送短信
    	 * 	> 需要给出短信接收者
    	 * 	> 短信服务中心的号码。直接给null
    	 * 	> 短信内容
    	 * 	> 短信发送成功后的广播。null
    	 * 	> 短信被对方成功接收后的广播。 null
    	 */
    	//sm.sendTextMessage(phone, null, content, null, null);
    	
    	//如果短信的内容过多是无法发送的，所有我们把内容截取成几个内容。分段发送
    	ArrayList<String> al = sm.divideMessage(content);
    	for(String sms : al) {
    		sm.sendTextMessage(phone, null, sms, null, null);
    	}
    }
}
