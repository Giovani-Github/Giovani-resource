package com.example.bindservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MyService extends Service {

	//服务绑定时此方法调用，如果服务没有创建，会先调用onCreate()创建服务
	@Override
	public IBinder onBind(Intent intent) {
		System.out.println("Bind()");
		return null;
	}
	
	//服务解绑时此方法调用，解绑之后会调用onDestroy()销毁服务
	@Override
	public boolean onUnbind(Intent intent) {
		System.out.println("unBind()");
		return super.onUnbind(intent);
	}
	
	//服务创建时此方法调用
	@Override
	public void onCreate() {
		System.out.println("onCreate()");
		super.onCreate();
	}
	
	//服务启动时此方法调用
	@Override
	public void onStart(Intent intent, int startId) {
		System.out.println("onStart()");
		super.onStart(intent, startId);
	}
	
	//服务销毁时此方法调用
	@Override
	public void onDestroy() {
		System.out.println("onDestroy()");
		super.onDestroy();
	}

}
