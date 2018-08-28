package com.example.videoplayer;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class MainActivity extends Activity {

	MediaPlayer player; // 播放视屏用的
	// 当前播放进度，加上静态。就会和进程共存亡。即使activity关闭了。也还能存在。按返回键后再次进入也能继续播放
	static int currentPosition; 
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    
        //拿到surfaceView
        SurfaceView sv = (SurfaceView) findViewById(R.id.sv);
        //拿到surfaceView的控制器
       final SurfaceHolder sh = sv.getHolder();
       
       //如果SurfaceView还是不可见状态的话，是不会创建它的。所以不可见的时候，开始播放，也是看不到视屏的
       //解决：在子线程中，等待一段时间之后（一段时间后，SurfaceView可见了），在进行播放
       //有个弊端：时间慢了的话，会导致比较慢看到视频。快了的话，也还是看不见视频
/*      new Thread() {
    	   public void run() {
    		   try{
    			   //睡眠
    			   sleep(2000);
    		   } catch(Exception e) {
    			   e.printStackTrace();
    		   }
    		   
    		   //一段时间过后
    		   //在主线程（UI线程）中执行
    		   runOnUiThread(new Runnable() {
    			   	@Override
	    			public void run() {
	    				MediaPlayer player = new MediaPlayer();
	    				//重置
	    				player.reset();
	    				try {
		    				//加载资源
							player.setDataSource("sdcard/b1.3gp");
		    				//设置视频在surfaceView中显示
		    				player.setDisplay(sh);
		    				//同步准备
		    				player.prepare();
		    				//开始播放
		    				player.start();
System.out.println("开始播放");
						} catch (Exception e) {
							e.printStackTrace();
						} 
	    			}
    		   }); 
    	   }
		}.start();*/
       
       //使用另一种方法
       sh.addCallback(new Callback() {
		
    	   	//surfaceView销毁时调用
			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {
				//每次surfaceview销毁时，同时停止播放视频，和记录当前播放进度
				if(player != null) {
					currentPosition = player.getCurrentPosition();
					player.stop();
					player.release();
					player = null;
				}
			}
			
			//surfaceView创建时调用
			@Override
			public void surfaceCreated(SurfaceHolder holder) {
				//每次surfaceView创建时才去播放视频
				if(player == null) {
					player = new MediaPlayer();
					player.reset();
					try {
						player.setDataSource("sdcard/b3.3gp");
						player.setDisplay(sh);
						player.prepare();
						player.start();
						player.seekTo(currentPosition);
					}catch(Exception e) {
						e.printStackTrace();
					}
				}
			}
			
			//surfaceView结构改变时调用
			@Override
			public void surfaceChanged(SurfaceHolder holder, int format, int width,int height) {
			}
		});
    }
}
