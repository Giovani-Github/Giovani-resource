package com.example.leadcertificateservice;

import com.example.leadcertificateservice.PublicBusiness.Stub;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class LeadService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		//返回中间人对象，中间对象被系统接收，然后被系统传递到了连接对象ServiceConnection#onServiceConnected()方法中
		return new MiShu();
	}
	
	//直接继承生成的文件中的Stub对象，因为它已经继承了binder类，实现了publicBusiness接口。这个就是中间人对象
	//只需要实现我们在publicBusiness接口中定义的方法即可
	class MiShu extends Stub {

		@Override
		public void BZ() throws RemoteException {
			System.out.println("找领导办证");
			leadBZ();
		}
		
	}
	
	public void leadBZ() {
		System.out.println("领导办证");
	}
}
