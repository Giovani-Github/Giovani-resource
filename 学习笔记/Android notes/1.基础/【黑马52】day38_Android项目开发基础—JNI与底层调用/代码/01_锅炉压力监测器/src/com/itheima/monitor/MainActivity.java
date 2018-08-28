package com.itheima.monitor;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;

public class MainActivity extends Activity {

	private MyProgressBar pb;

	static{
		System.loadLibrary("hello");
	}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pb = (MyProgressBar) findViewById(R.id.pb);
        pb.setMax(100);
    }


   public void start(View v){
	   new Thread(){
		   public void run() {
			   startMonitor();
		   };
	   }.start();
   }
   public void stop(View v){
	   stopMonitor();
   }
    
   public native void startMonitor();
   public native void stopMonitor();
   
   public void show(int pressure){
	   pb.setPressure(pressure);
   }
}
