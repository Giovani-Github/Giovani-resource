package com.example.musicplayer2;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Binder;
import android.os.IBinder;

public class MusicService extends Service{

	//播放音乐的类
	MediaPlayer mp;
	@Override
	public IBinder onBind(Intent intent) {
		return new MusicController();
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		mp = new MediaPlayer();
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
		
	}
	
    public void play() {
    	//重置
    	mp.reset();

    	try {
    		//加载网络多媒体资源（没有加载，只是得到路径）
    		mp.setDataSource("http://192.168.1.105:8080/Android/a.mp3");
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
	
}
