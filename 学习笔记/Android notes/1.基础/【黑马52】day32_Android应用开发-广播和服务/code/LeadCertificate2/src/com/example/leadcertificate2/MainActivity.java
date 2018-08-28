package com.example.leadcertificate2;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;

public class MainActivity extends Activity {

    private MyConn conn;
    PublicFunction pf; // 中间对象

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //连接对象
        conn = new MyConn();
        //绑定服务
        Intent intent = new Intent(this, LeadService.class);
        bindService(intent, conn, BIND_AUTO_CREATE);
    }
	
	public void click(View v) {
		//让中间人找领导帮我办证
		pf.BZ();
	}
    
    class MyConn implements ServiceConnection {

    	//连接成功调用，成功得到了IBinder对象，就是连接成功
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			System.out.println("连接成功");
			pf = (PublicFunction) service;
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			
		}
    	
    }
}
