package com.example.downloadimage2;

import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	static ImageView iv;
	static MainActivity main;
	
	//5. 创建消息处理器对象
	static Handler handler = new Handler() {
		//如果消息队列中有消息，Looper会调用本方法，并把消息对象传递过来
		public void handleMessage(android.os.Message msg) {
			//6. 根据消息的类型，做不同操作
			switch(msg.what) {
			case 1: //如果是成功的信息
				
				//7. 显示图片在ImageView中
				iv.setImageBitmap((Bitmap)msg.obj);				
				break;
			
			case 0: //失败的信息
				
				//8. 弹吐司
				Toast.makeText(main, "请求失败", 0).show();
				break;
			}
		}
	};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        iv = (ImageView) findViewById(R.id.iv);
        main = this;
    }
    
    public void click(View v) {
    	//1. 创建一个子线程，用来访问网络连接，获取服务器返回的数据
    	Thread t = new Thread() {
    		public void run() {
    			try {
    				//2.确定网址，封装成URL对象，通过URL获取与服务了连接的对象
					HttpURLConnection conn = (HttpURLConnection) new URL("http://192.168.1.102:8080/Android/wo.jpg").openConnection();
					
					//3. 对连接对象初始化,并且发送请求
					conn.setRequestMethod("GET");
					conn.setReadTimeout(5000);
					conn.setConnectTimeout(5000);
					conn.connect();
					
					if(conn.getResponseCode() == 200) { //如果请求成功
						//4.获取服务器发送过来的数据流，并且构造成位图对象
						Bitmap bm = BitmapFactory.decodeStream(conn.getInputStream());
						
						//6. 使用消息队列，让显示图片在主线程中执行
						
						Message msg = handler.obtainMessage();//不建议new，用消息处理器的方法来创建
						msg.obj = bm; //携带数据
						msg.what = 1; // 自己定义，1表示请求成功的信息
						handler.sendMessage(msg);//把消息发送至主线程的消息队列
					} else { //请求失败
						Message msg = handler.obtainMessage();
						msg.what = 0;//表示失败的信息
						handler.sendMessage(msg);
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
    		}
    	};
    	
    	//启动线程
    	t.start();
    }
}
