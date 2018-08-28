package com.example.leadcertificate3;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;

import com.example.leadcertificateservice.PublicBusiness;
import com.example.leadcertificateservice.PublicBusiness.Stub;

public class MainActivity extends Activity {

	MyConn conn; // 连接对象，可以获取中间人对象
	PublicBusiness pb; // 中间人对象。这个接口里定义了中间人对象允许我们调用的方法
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //绑定远程服务
        Intent intent = new Intent();
        intent.setAction("lservice");
        conn = new MyConn();
        bindService(intent, conn, BIND_AUTO_CREATE);
    }
    
    //办证
    public void bz(View v) {
    	//会调用中间人PublicBusiness的办证方法，访问远程服务的办证方法
    	try {
			pb.BZ();
		} catch (RemoteException e) { // 服务连接失败异常
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @Override
    protected void onDestroy() {
    	super.onDestroy();
    	//解绑服务
    	unbindService(conn);
    }
    
    class MyConn implements ServiceConnection {

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			//拿到中间人对象进行强转, 得到的就是PublicBusiness
			pb = Stub.asInterface(service);
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			
		}
    	
    }
    
}
