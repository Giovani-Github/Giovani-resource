package com.example.asynchttpclientget;

import java.net.URLEncoder;

import org.apache.http.Header;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click(View v) {
    	EditText et_name = (EditText) findViewById(R.id.et_name);
    	EditText et_pass = (EditText) findViewById(R.id.et_pass);
    	final String name = URLEncoder.encode(et_name.getText().toString()); // 需要进行URL编码
    	final String pass = et_pass.getText().toString();
    	String url = "http://192.168.1.107:8080/AndroidLogin/LoginServlet?name=" + name + "&pass=" + pass;
    	
    	// 1. 创建异步客户端对象
    	AsyncHttpClient ahc =  new AsyncHttpClient();
    	
    	// 2. 发送get请求，提交数据
    	ahc.get(url, new MyResponseHandler());
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
