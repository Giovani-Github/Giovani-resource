package com.example.musicplayer;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
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
        	//加载多媒体（未真正加载，只是得到资源的路径）
			mp.setDataSource("sdcard/a.mp3");
			//准备播放（真正开始加载，同步加载，会阻塞。但是因为是加载本地的，所以无所谓）
			mp.prepare();
			//开始播放，如果没有重置，会从上次结束的地方继续播放
			mp.start();
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
