package com.example.musicplay;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;


public class MusicService extends Service{

	@Override
	public IBinder onBind(Intent intent) {
		return new MyBinder();
	}
	
	public void play() {
		System.out.println("播放音乐");
	}
	
	public void pause() {
		System.out.println("暂停音乐");
	}

	//中间对象
	class MyBinder extends Binder implements MusicControl {

		@Override
		public void play() {
			MusicService.this.play();
		}

		@Override
		public void pause() {
			MusicService.this.pause();			
		}
		
	}
}
