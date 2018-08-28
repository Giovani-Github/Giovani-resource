package com.example.musicplayer2;

import com.example.musicplayer2.R;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;

public class MainActivity extends Activity {
	
	MusicInterface mi;//中间人
	MyConn conn;
	private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        intent = new Intent(this, MusicService.class);
        conn = new MyConn();
        startService(intent);
        bindService(intent, conn, BIND_AUTO_CREATE);
    }

    //播放
    public void play(View v) {
    	mi.play();
    }
    
    //暂停
    public void pause(View v) {
    	mi.pause();
    }
    
    //继续
    public void continuePlay(View v) {
    	mi.continuePlay();
    }
    
	//退出
    public void exit(View v) {
    	unbindService(conn);
    	stopService(intent);
    	finish();
    }
    
    class MyConn implements ServiceConnection {

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			mi = (MusicInterface) service;
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			
		}
    	
    }
}
