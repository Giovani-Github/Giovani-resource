package com.example.custombroadcast;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    
    public void click(View v) {
    	Intent intent = new Intent();
    	//设置action
    	intent.setAction("zdy.broadcast");
    	//发送自定义广播
    	sendBroadcast(intent);
    }
}
