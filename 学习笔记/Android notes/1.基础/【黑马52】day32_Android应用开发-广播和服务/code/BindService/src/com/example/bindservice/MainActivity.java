package com.example.bindservice;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;

public class MainActivity extends Activity {

    private Intent intent;
	private MyServiceConnection conn;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        intent = new Intent(this, MyService.class);
        conn = new MyServiceConnection();
    }
    
    public void bind(View v) {
    	bindService(intent, conn, BIND_AUTO_CREATE);
    }
    
    public void unBind(View v) {
    	unbindService(conn);
    }
    
    class MyServiceConnection implements ServiceConnection {

    	//服务连接成功时，此方法调用 (成功得到中间对象IBinder，就是连接成功)
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			System.out.println("onServiceConnected()");
		}

		//服务失去连接时，此方法调用
		@Override
		public void onServiceDisconnected(ComponentName name) {
			System.out.println("onServiceDisconnected()");
		}
    	
    }
}