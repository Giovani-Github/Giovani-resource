package com.example.monitor;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

public class MainActivity extends Activity {

	private MyProgressBar pb;

	static{
		System.loadLibrary("monitor");
	}
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pb = (MyProgressBar) findViewById(R.id.pb);
        pb.setMax(100);
    }


   //开始监测压力值
   public void start(View v){
	   //因为是一个循环，每秒运行一次，所以会阻塞主线程
	   new Thread(){
		   public void run() {
			   startMonitor();
		   };
	   }.start();
   }
   
   //停止监测
   public void stop(View v){
	   stopMonitor();
   }
    
   //开启监视器
   public native void startMonitor();
   //关闭监视器
   public native void stopMonitor();
   
   //给c调用，显示进度条
   public void show(int pressure){
	   //进度条可以在子线程中刷新
	   pb.setPressure(pressure);
   }

}
