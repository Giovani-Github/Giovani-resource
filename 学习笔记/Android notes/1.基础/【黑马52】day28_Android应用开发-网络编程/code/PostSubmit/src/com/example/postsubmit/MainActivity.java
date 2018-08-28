package com.example.postsubmit;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	static MainActivity main;
	static Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			Toast.makeText(main, (String)msg.obj, 0).show();
		};
	};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        main = this;
    }
    
    public void click(View v) {
    	EditText et_name = (EditText) findViewById(R.id.et_name);
    	EditText et_pass = (EditText) findViewById(R.id.et_pass);
    	final String name = URLEncoder.encode(et_name.getText().toString()); // 需要进行URL编码
    	final String pass = et_pass.getText().toString();
    	
    	Thread t = new Thread() {
    		public void run() {
    			String path = "http://192.168.1.110:8080/AndroidLogin/LoginServlet";
    			
    			//拼接要提交的数据
    			String data = "name=" + name + "&pass=" + pass;
    			
    			try {
					HttpURLConnection conn = (HttpURLConnection) new URL(path).openConnection();
					conn.setRequestMethod("POST");
					conn.setReadTimeout(5000);
					conn.setConnectTimeout(5000);
					
					//增加两个请求头
					conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
					conn.setRequestProperty("Content-Length", data.length() + "");
					
					//打开输出流
					conn.setDoOutput(true);
					//得到输出流，并且把数据写到输出流，这时并没有提交到服务器
					OutputStream os = conn.getOutputStream();
					os.write(data.getBytes());
					
					if(conn.getResponseCode() == 200) { // 发送请求并得到状态码。发送请求时会把输出流中的数据提交
						InputStream in = conn.getInputStream();
						ByteArrayOutputStream bos = new ByteArrayOutputStream();
						
						byte[] bytes = new byte[1024];
						int len = 0;
						while((len = in.read(bytes)) != -1) {
							bos.write(bytes, 0, len);
						}
						
						String text = new String(bos.toByteArray());
						
						//使用消息队列，弹吐司
						Message msg = handler.obtainMessage();
						msg.obj = text;
						handler.sendMessage(msg);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
    		}
    	};
     	
    	t.start();
    }
}
