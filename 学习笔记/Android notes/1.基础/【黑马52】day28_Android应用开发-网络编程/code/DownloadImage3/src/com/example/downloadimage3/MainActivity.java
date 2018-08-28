package com.example.downloadimage3;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
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
	
	static Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch(msg.what) {
			case 1:
				iv.setImageBitmap((Bitmap)msg.obj); // 在ImageView中显示图片
				break;
				
			case 0:
				Toast.makeText(main, "连接失败", 0).show();
				break;
			}
		};
	};
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    
        iv = (ImageView) findViewById(R.id.iv);
        main = this;
    }
    
    public void click(View v) {
    	
    	final String path = "http://192.168.1.102:8080/Android/wo.jpg";//要请求的网络地址
    	final File file = new File(getFilesDir(), getFileName(path));//要缓存到这个路径
    	
    	if(file.exists()) { //如果已经存在缓存
System.out.println("缓存！！！");
    		Bitmap bm = BitmapFactory.decodeFile(file.getAbsolutePath());//根据绝对路径，构造成位图对象
    		//因为这里是主线程，所以不需要消息队列
    		iv.setImageBitmap(bm);//显示在ImageView中
    		
    	} else { //如果没有缓存，开启子线程，请求服务器获取数据
System.out.println("网络！！！"); 		
    		Thread t = new Thread() {
    			public void run() {
    	    		FileOutputStream fos = null;
    	    		InputStream is = null;
    	    		try {
    	    			//使用path，构造RUL对象，得到连接对象
    					HttpURLConnection conn = (HttpURLConnection) new URL(path).openConnection();
    					//初始化连接对象，并且发送请求
    					conn.setRequestMethod("GET");
    					conn.setReadTimeout(5000);
    					conn.setConnectTimeout(5000);
    					conn.connect();
    					
    					if(conn.getResponseCode() == 200) { //请求成功
    						fos = new FileOutputStream(file);//文件输出流
    						is = conn.getInputStream();//服务器发送过来的数据输入流
    						byte[] bytes = new byte[1024];
    						int len = 0;
    						while((len = is.read(bytes)) != -1) { //读取输入流中的数据，没有返回-1.就表示还有数据
    							fos.write(bytes, 0, len);//把bytes的0下标到len，写入到fos中。就是把数据写入到文件中
    						}
    						
    						//把从内部存储中读取出来的文件，构造成位图对象，并且使用消息队列，在主线程中显示到ImageView中
    						Bitmap bm = BitmapFactory.decodeFile(file.getAbsolutePath());//根据绝对路径，构造位图对象
    						Message msg = handler.obtainMessage();//
    						msg.obj = bm;
    						msg.what = 1;//成功的消息
    						handler.sendMessage(msg);//把消息发送给消息队列
    						
    					} else { //请求失败
    						Message msg = handler.obtainMessage();
    						msg.what = 0; //失败的消息
    						handler.sendMessage(msg);
    					}
    					
    				} catch (Exception e) {
    					throw new RuntimeException(e);
    				} finally {
    					try {
    						if(is != null) is.close();
    						if(fos != null) is.close();
    					} catch (Exception e2) {
    						throw new RuntimeException(e2);
    					}
    				}
    			};
    		};
    		t.start();
    	}
    }
    
    private String getFileName(String path) {
    	int index = path.lastIndexOf("/");
    	return path.substring(index + 1);
    }
}
