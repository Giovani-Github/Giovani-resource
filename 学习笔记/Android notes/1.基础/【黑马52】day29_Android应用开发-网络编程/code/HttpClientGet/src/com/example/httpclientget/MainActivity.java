package com.example.httpclientget;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URLEncoder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

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
    	Thread t = new Thread() {
    		public void run() {
    			EditText et_name = (EditText) findViewById(R.id.et_name);
    			EditText et_pass = (EditText) findViewById(R.id.et_pass);
    			String name = URLEncoder.encode(et_name.getText().toString()); //需要进行URL编码
    			String pass = et_pass.getText().toString();
    			//确定网址，并且把数据作为参数拼接在后面
    			String path = "http://192.168.1.107:8080/AndroidLogin/LoginServlet?name=" + name + "&pass=" + pass;
    			
    			 // 1. 创建HttpClient客户端对象
    			HttpClient hc = new DefaultHttpClient();
    			// 2. 创建请求对象，参数就是网址
    			HttpGet hg = new HttpGet(path); 
    			
    			ByteArrayOutputStream bos = null;
    			InputStream is = null;
    			
    			try {
    				// 3. 使用客户端对象发送请求，并且得到响应对象
					HttpResponse hr =  hc.execute(hg);
					// 4. 使用响应对象，得到状态行对象
					StatusLine sl = hr.getStatusLine();
					
					// 5. 通过状态行对象，得到响应码。判断响应码，做出的对应的操作
					if(sl.getStatusCode() == 200) {
						// 6. 通过响应对象，得到响应实体的对象
						HttpEntity he = hr.getEntity();
						// 7. 通过实体对象，得到服务器响应的数据输入流
						is = he.getContent();
						
						// 8. 对输入流进行操作， 得到数据，使用消息队列弹吐司
						bos = new ByteArrayOutputStream();
						byte[] b = new byte[1024];
						int len = 0;
						while((len = is.read(b)) != -1) {
							bos.write(b, 0, len);
						}
						String text = new String(bos.toByteArray());
						Message msg = handler.obtainMessage();
						msg.obj = text;
						handler.sendMessage(msg);
					}
											
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						if(is != null) is.close();
						if(bos != null) bos.close();
					} catch (Exception e2) {
						throw new RuntimeException(e2);
					}
				}
    		};
    	};
    	
    	t.start();
    }
}
