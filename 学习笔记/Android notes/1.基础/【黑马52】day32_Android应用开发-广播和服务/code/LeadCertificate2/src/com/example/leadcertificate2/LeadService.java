package com.example.leadcertificate2;

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
	class MiShu extends Binder implements PublicFunction{

		//这个方法可以被平民(Activity)访问
		@Override
		public void BZ() {
			System.out.println("找领导办证");
			leadBZ();
		}
		
		//这个方法不能被平民(Activity)访问
		public void damajiang() {
			System.out.println("和领导打麻将");
		}
		
	}	
	
	public void leadBZ() {
		System.out.println("领导办证");
	}

}
