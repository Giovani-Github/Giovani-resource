package com.example.bindandstartservice;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;

public class MainActivity extends Activity {

    private MyConn conn;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    
    //startService远程服务
    public void start(View v) {
    	//意图要与清单文件中配置的Intent-filter匹配
    	Intent intent = new Intent();
    	intent.setAction("my3");
    	startService(intent);
    }
    
    //stop远程服务
    public void stop(View v) {
    	//意图要与清单文件中配置的Intent-filter匹配
    	Intent intent = new Intent();
    	intent.setAction("my3");
    	stopService(intent);
    }
    
    //bind远程服务
    public void bind(View v) {
    	conn = new MyConn();
    	//意图要与清单文件中配置的Intent-filter匹配
    	Intent intent = new Intent();
    	intent.setAction("my3");
    	bindService(intent, conn, BIND_AUTO_CREATE);
    }
    
    //unbind远程服务
    public void unbind(View v) {
    	unbindService(conn);
    }
    
    class MyConn implements ServiceConnection {

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			
		}
    	
    }
}
