package com.example.musicplay;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;

public class MainActivity extends Activity {
	
	private MusicControl mc;//中间对象
	private MyConn conn; // 连接对象
	private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        intent = new Intent(this, MusicService.class);
        conn = new MyConn();
        //如果单独只是绑定服务，那么服务就会随着activity销毁而关闭，音乐就不能后台播放了
        //所以我们混合调用，使服务所在的进程变成服务进程，不容易被系统杀死，而且activity关闭了，服务也不会关闭
        //必须先开启，在绑定，不然会有bug
        startService(intent);        
        bindService(intent, conn, BIND_AUTO_CREATE);
    }
    
    //播放音乐
    public void play(View v) {
    	//使用中间对象，间接调用服务里的播放音乐功能
    	mc.play();
    } 
    
    //暂停音乐
    public void pause(View v) {
    	mc.pause();
    }
    
    //退出音乐播放器
    public void quit(View v) {
    	//退出时音乐也要停止，所以要关闭服务
    	//服务关闭时也要有顺序，先解绑，在停止
//小bug，如果服务没有开启，直接退出的话，会抛异常。因为没有开启服务，怎么关闭呢
    	unbindService(conn);
    	stopService(intent);
    	//关闭activity
    	finish();
    }
    
    //连接对象
    class MyConn implements ServiceConnection {

    	//连接成功时调用，就是得到了中间对象时调用
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			mc = (MusicControl) service;
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			
		}
    	
    }
}


