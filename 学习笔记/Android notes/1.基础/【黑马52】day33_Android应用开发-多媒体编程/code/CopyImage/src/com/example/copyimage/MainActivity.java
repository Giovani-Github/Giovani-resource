package com.example.copyimage;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click(View v) {
    	//加载原图，这个原图是只读的，不能做修改
    	Bitmap srcBm = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + "/photo3.jpg");
System.out.println(srcBm);
    	
    	/*
    	 * 创建一个与原图大小一致的空白Bitmap（看做白纸）
    	 * width:宽
    	 * height:高
    	 * config:配置参数
    	 * 全部都是用原图的
    	 * 
    	 * 这个就是副本了，可修改（加水印等..）
    	 */
    	Bitmap copyBm = Bitmap.createBitmap(srcBm.getWidth(), srcBm.getHeight(), srcBm.getConfig());
    	System.out.println(copyBm);
    	
    	//定义画笔
    	Paint paint = new Paint();
    	
    	//定义画板，把白纸铺在画板上
    	Canvas canvas = new Canvas(copyBm);
    	/*
    	 * 把原图的内容绘制在副本上
    	 * bitmap：原图
    	 * matrix: 矩阵，做特效用的
    	 * paint: 画笔
    	 */
    	canvas.drawBitmap(srcBm, new Matrix(), paint);
    	
    	//显示原图和副本在屏幕
    	ImageView iv_src = (ImageView) findViewById(R.id.iv_src);
    	ImageView iv_copy = (ImageView) findViewById(R.id.iv_copy);
    	iv_src.setImageBitmap(srcBm);
    	iv_copy.setImageBitmap(copyBm);
    }
}
