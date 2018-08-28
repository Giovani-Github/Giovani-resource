package com.example.leadcertificate;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class LeadService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		System.out.println("onBind");
		//返回中间对象，中间对象被系统接收，然后被系统传递到了连接对象ServiceConnection#onServiceConnected()方法中
		return new MiShu();
	}

	//中间对象
	class MiShu extends Binder {
		public void BZ() {
			System.out.println("秘书找领导办证");
			leadBZ();
		}
	}
		
	public void leadBZ() {
		System.out.println("领导办证了");
	}
}
