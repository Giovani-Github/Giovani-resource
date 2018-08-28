package com.example.downloadimage;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    
    public void click(View v) throws Exception {
    	//1. 确定要请求的网址，不能写loaclhost，必须写ip地址
    	String path = "http://192.168.1.107:8080/Android/wo.jpg";
		//2. 通过网址，构造成一个URL对象
		URL url = new URL(path);
		//3. 通过url，得到客户端与服务器的连接对象，此时还没有建立连接
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		
		//4. 给连接对象设置初始化参数
		conn.setRequestMethod("GET");//设置请求方式，必须是大写
		conn.setConnectTimeout(5000);//设置连接超时时间，5秒
		conn.setReadTimeout(5000);//设置读取超时时间，5秒
		
		//5. 建立连接，发送GET请求
		conn.connect();
		
		//6. 根据返回的响应/状态码，做不同的操作
		if(conn.getResponseCode() != 200) { //如果请求码不是200，就是请求没有成功
			Toast.makeText(this, "请求失败！", 0).show();
		} else { //请求成功
			
			//7. 拿到服务器返回的输入流，流里的数据就是客户端请求的数据
			InputStream is = conn.getInputStream();
			//8. 读取出理你的数据，并构造成位图对象。位图其实就是图片
			Bitmap bm = BitmapFactory.decodeStream(is);
			
			
			//9. 把位图对象显示到ImageView中，其实就是把图片显示在ImageView中
			ImageView iv = (ImageView) findViewById(R.id.iv);
			iv.setImageBitmap(bm);
		}
    }
}
