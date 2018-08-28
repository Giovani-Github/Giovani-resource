package com.example.downloadtext;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	static TextView tv;
	
	static Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			tv.setText((String)msg.obj);//把数据显示在TextView
		};
	};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        tv = (TextView) findViewById(R.id.tv);
    }
    
    public void click(View v) {
    	Thread t = new Thread() {
    		public void run() {
    			try {
    				HttpURLConnection conn = (HttpURLConnection) new URL("http://192.168.1.102:8080/Android/wo.txt").openConnection();
    				conn.setRequestMethod("GET");
    				conn.setConnectTimeout(5000);
    				conn.setReadTimeout(5000);
    				if(conn.getResponseCode() == 200) { //获取状态码，自带建立连接的功能
    					InputStream in = conn.getInputStream();//得到服务器发送过来的文本数据
    					ByteArrayOutputStream bos = new ByteArrayOutputStream();//字节数组输出流，读取的数据存放到这里
    					byte[] b = new byte[1024];
    					int len = 0;
    					while((len = in.read(b)) != -1) {
    						bos.write(b, 0, len);
    					}
    					
    					//把字节数组输出流里的数据，构造成字符串
    					String text = new String(bos.toByteArray());
    					
    					//把文本，通过处理器，传递给消息队列。在主线程中，操作文本显示在界面
    					Message msg = handler.obtainMessage();
    					msg.obj = text;
    					handler.sendMessage(msg);    					
    					
    				}
    			} catch (Exception e) {
    				throw new RuntimeException(e);
    			}
    		};
    	};
    	t.start();   	
    }
}
