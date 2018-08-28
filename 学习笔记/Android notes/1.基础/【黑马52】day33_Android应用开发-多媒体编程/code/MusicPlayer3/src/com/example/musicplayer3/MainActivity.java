package com.example.musicplayer3;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.view.View;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class MainActivity extends Activity {
	
	MusicInterface mi;//中间人
	MyConn conn;
	private Intent intent;
	private static SeekBar sb; // 进度条

	//消息处理对象
	static Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			//得到消息，进行处理
			Bundle bundle = msg.getData();
			//得到歌曲总进度
			int duration = bundle.getInt("duration");
			//得到歌曲当前播放进度
			int currentPosition = bundle.getInt("currentPosition");
			
			//刷新进度条
			sb.setMax(duration);//设置最大值
			sb.setProgress(currentPosition);//设置当前最值
		}
	};
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //获取进度条
        sb = (SeekBar) findViewById(R.id.sb);
        //设置进度条改变侦听
        sb.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
        	//手指抬起
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				//根据拖动的进度条，改变音乐播放的进度
				int progress = seekBar.getProgress();
				mi.seekTo(progress);
			}
			
			//手指触摸
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}
			
			//手指滑动进度条
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {				
			}
		});
                        
        //开启服务
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
