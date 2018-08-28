package com.example.musicplayer3;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;

public class MusicService extends Service{

	//播放音乐的类
	MediaPlayer mp;
	private Timer timer;
	@Override
	public IBinder onBind(Intent intent) {
		return new MusicController();
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		mp = new MediaPlayer();
		
		//开始计时任务，不断获取播放进度
		addTime();
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		
		//停止播放
		mp.stop();
		//释放占用的资源，此时mp已经废了。要用就要重新new
		mp.release();
		//既然废了，就置为null，等待垃圾回收
		mp = null;
		
		//退出时，也要把计时任务关闭
		if(timer != null) {
			//停止执行计时任务
			timer.cancel();
			timer = null;
		}
		
		
	}
	
	//中间人
	class MusicController extends Binder implements MusicInterface {

		@Override
		public void play() {
			MusicService.this.play();			
		}

		@Override
		public void pause() {
			MusicService.this.pause();			
		}

		@Override
		public void continuePlay() {
			MusicService.this.continuePlay();
		}

		@Override
		public void seekTo(int progress) {
			MusicService.this.seekTo(progress);		
		}
	
	}
	
    public void play() {
    	//重置
    	mp.reset();

    	try {
    		//加载本地多媒体资源（没有加载，只是得到路径）
    		mp.setDataSource("sdcard/a.mp3");
    		//异步准备（同步准备是在主线程加载，在获取网络的资源的时候，一阻塞，那么就卡死了）
    		//异步准备会开启一个子线程
    		mp.prepareAsync();
    		
    		//设置准备侦听
    		mp.setOnPreparedListener(new OnPreparedListener() {
				//准备完成后此方法调用
				@Override
				public void onPrepared(MediaPlayer mp) {
					//开始播放
					mp.start();
				}
			});
		} catch (Exception e) {	
			e.printStackTrace();
		}
    	
    }
    
    public void pause() {
    	//暂停
    	mp.pause();
    }
    
    public void continuePlay() {
    	//继续播放
    	mp.start();
    }
    
    //改变播放进度
    public void seekTo(int progress) {
    	mp.seekTo(progress);
    }
    
    //不断的执行任务，获取歌曲当前播放进度。传递给消息队列，设置进度条进度
    public void addTime() {
    	if(timer == null) {
	    	timer = new Timer();
	    	//开启一个子线程执行任务
	    	timer.schedule(new TimerTask() {
				
				@Override
				public void run() {
					//获取歌曲总时长（毫秒）
					int duration = mp.getDuration();
					//获取当前播放进度（毫秒）
					int currentPosition = mp.getCurrentPosition();
					
					//把进度封装至消息对象中
					Message msg = MainActivity.handler.obtainMessage();
					Bundle bundle = new Bundle();
					bundle.putInt("duration", duration);
					bundle.putInt("currentPosition", currentPosition);
					msg.setData(bundle);
					MainActivity.handler.sendMessage(msg);
					
				}    
				//开始及时任务后的毫秒，第一次执行run方法，以后每500毫秒执行一次run方法
	    	}, 5, 500);
	    }
    }
	
}
