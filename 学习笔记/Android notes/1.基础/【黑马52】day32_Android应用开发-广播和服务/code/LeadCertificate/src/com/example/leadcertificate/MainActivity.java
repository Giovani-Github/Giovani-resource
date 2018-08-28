package com.example.leadcertificate;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;

import com.example.leadcertificate.LeadService.MiShu;

public class MainActivity extends Activity {

    private MyConn conn; // 服务连接对象
    private MiShu mishu; // 中间对象

	@Override
    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        conn = new MyConn();
        //绑定服务
    	Intent intent = new Intent(this, LeadService.class);
    	bindService(intent, conn, BIND_AUTO_CREATE);
    	
        
    }

	//按下就调用MiShu的办证方法
    public void click(View v) {
    	mishu.BZ();//通过中间对象的办证方法，间接调用领导(服务)的办证方法，让领导办证
    }
    
    //服务连接对象
    class MyConn implements ServiceConnection {

    	//服务连接成功，此方法调用（得到了IBinder对象， 就是连接成功）
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			//得到秘书（中间对象）
			mishu = (MiShu) service;
			System.out.println("连接成功");
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			
		}
    	
    }
}
