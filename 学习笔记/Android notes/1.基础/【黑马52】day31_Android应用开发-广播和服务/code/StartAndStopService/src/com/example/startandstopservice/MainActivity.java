package com.example.startandstopservice;

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
    
    //开启服务（显式）
    public void click1(View v) {
    	//定义意图
    	Intent intent = new Intent(this, MyService.class);
    	//开启服务
    	startService(intent);
    }
    
    //关闭显式开启的服务
    public void click2(View v) {
    	Intent intent = new Intent(this, MyService.class);
    	//关闭服务
    	stopService(intent);
    }
    
    //开启服务（隐式）
    public void click3(View v) {
    	Intent intent = new Intent();
    	intent.setAction("my");
    	startService(intent);
    }
    
    //关闭隐式开启的服务
    public void click4(View v) {
    	Intent intent = new Intent();
    	intent.setAction("my");
    	stopService(intent);
    }
    
    
}
