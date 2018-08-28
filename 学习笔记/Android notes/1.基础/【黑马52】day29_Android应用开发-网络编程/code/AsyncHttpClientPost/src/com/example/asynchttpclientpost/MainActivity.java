package com.example.asynchttpclientpost;

import org.apache.http.Header;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click(View v) {
    	EditText et_name = (EditText) findViewById(R.id.et_name);
    	EditText et_pass = (EditText) findViewById(R.id.et_pass);
    	final String name = et_name.getText().toString();
    	final String pass = et_pass.getText().toString();
    	String url = "http://192.168.1.107:8080/AndroidLogin/LoginServlet";
    	
    	// 1. 创建异步客户端对象
    	AsyncHttpClient ahc =  new AsyncHttpClient();
    	
    	// 2. 要提交的数据封装到RequestParams
    	RequestParams rp = new RequestParams();
    	rp.add("name", name);
    	rp.add("pass", pass);
    	
    	// 3. 发送post请求，提交数据
    	ahc.post(url, rp, new MyResponseHandler());
    }
    
    // 响应结果处理器
    private class MyResponseHandler extends AsyncHttpResponseHandler {

    	//请求成功时调用
		@Override
		public void onSuccess(int statusCode, Header[] headers,
				byte[] responseBody) {
			Toast.makeText(MainActivity.this, new String(responseBody), 0).show();
			
		}
		
		//请求失败时调用
		@Override
		public void onFailure(int statusCode, Header[] headers,
				byte[] responseBody, Throwable error) {
			Toast.makeText(MainActivity.this, "请求失败", 0).show();
			
		}
    	
    }
    
}
