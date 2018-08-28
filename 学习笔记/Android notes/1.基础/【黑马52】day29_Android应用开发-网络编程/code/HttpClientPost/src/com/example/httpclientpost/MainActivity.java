package com.example.httpclientpost;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

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
    			String name = et_name.getText().toString(); 
    			String pass = et_pass.getText().toString();
    			
    			// 1. 创建客户端对象，创建post对象（写入网址） 
    			HttpClient hc = new DefaultHttpClient();
    			HttpPost hp = new HttpPost("http://192.168.1.107:8080/AndroidLogin/LoginServlet");
    			
    			// 2. 把数据封装到List中，且list中只能装NameVluePair
    			List<BasicNameValuePair> dataList = new ArrayList<BasicNameValuePair>();
    			BasicNameValuePair bnvp1 = new BasicNameValuePair("name", name);
    			BasicNameValuePair bnvp2 = new BasicNameValuePair("pass", pass);
    			dataList.add(bnvp1);
    			dataList.add(bnvp2);
    			
    			InputStream is = null;
    			ByteArrayOutputStream bos = null;
    			try {
    				// 3. 创建实体对象，把数据放进实体对象中
					UrlEncodedFormEntity uefe = new UrlEncodedFormEntity(dataList, "utf-8");
					// 4. 把实体对象放进post请求对象
					hp.setEntity(uefe);
					// 5. 使用客户端发送post请求，并且得到响应对象
					HttpResponse hr = hc.execute(hp);
					
					// 6. 使用响应对象得到响应行对象，使用响应行对象得到响应码
					if(hr.getStatusLine().getStatusCode() == 200) {
						// 7. 使用响应对象得到服务器发送过来的数据实体对象
						HttpEntity he = hr.getEntity();
						// 8. 使用实体对象得到服务器发送的输入流
						is = he.getContent();
						
						// 9. 操作输入流，得到想要的数据，弹吐司
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
