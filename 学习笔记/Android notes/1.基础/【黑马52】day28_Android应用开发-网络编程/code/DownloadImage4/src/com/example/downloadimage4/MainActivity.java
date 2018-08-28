package com.example.downloadimage4;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.loopj.android.image.SmartImageView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }    
    
    public void click(View v) {
    	String path = "http://192.168.1.102:8080/Android/wo.jpg";//要访问的网址
    	
    	//得到只能图片查看器对象
    	SmartImageView siv = (SmartImageView) findViewById(R.id.siv);
    	
    	//下载并显示图片
    	//这里面已经高度封装好了我们在Download3项目中所做的事。并且还对开发中所需要的需求进行了封装
    	//这是很强大的一个图片查看器
    	siv.setImageUrl(path);
    	
    }
}
