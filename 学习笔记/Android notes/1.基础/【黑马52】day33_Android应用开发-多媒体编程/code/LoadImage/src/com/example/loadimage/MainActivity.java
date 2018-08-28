package com.example.loadimage;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.Bundle;
import android.os.Environment;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click(View v) {
    	//解析图片是，需要使用到的参数都封装在这个对象里了
    	Options opts = new Options();
    	//不为像素申请内存（所有像素信息都不解析，这样就不会内存溢出了）， 只获取图片信息（宽高）
    	//不会反会一个Bitmap对象了，而是把信息写到了Options对象中
    	opts.inJustDecodeBounds = true;
    	BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + "/dog.jpg", opts);
    	//通过Options对象拿到图片宽高
    	int imageWidth = opts.outWidth;
    	int imageHeight = opts.outHeight;
    	
    	//拿到手机屏幕管理器,通过管理器拿到显示对象Display
    	Display dp = getWindowManager().getDefaultDisplay();
    	/*
    	 * 拿到屏幕宽高
    	 * 这两个api是过时的，但是它推荐我们使用的api在低版本Android中是用不了的。
    	 * 所以还是使用过时的api
    	 */
    	int screenWidth = dp.getWidth();
    	int screenHeight = dp.getHeight(); 
    	
    	//计算缩放比例
    	int scale = 1; //默认为1，即不缩放
    	//宽的缩放倍数，图片宽/手机宽
    	int scaleWidth = imageWidth / screenWidth;
    	//高的缩放倍数，图片高/手机高
    	int scaleHeight  = imageHeight / screenHeight;
    	/*
    	 * 判断哪个比例比较大，选择最大的那个比例
    	 * 如果两个比例都相等，那么就随便哪个都行
    	 * 并且，计算出来的其中一个比例小于1的话，那么就证明图片比屏幕还小。就不用缩放了
    	 */
    	if(scaleWidth >= scaleHeight && scaleWidth >= 1) {
    		scale = scaleWidth;
    	} else if(scaleWidth < scaleHeight && scaleHeight >= 1) {
    		scale = scaleHeight;
    	}
    	
    	//设置缩放比例
    	//如果比例大于1，那么就会把图片在复制一个，再把它按比例缩小。再把这个缩小的图片返回
    	opts.inSampleSize = scale;
    	//设置需要为所有像素信息进行解析，这样才能返回位图对象
    	opts.inJustDecodeBounds = false;
    	//这个对象是只读的
    	Bitmap bm = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() + "/dog.jpg", opts);
    	
    	ImageView iv = (ImageView) findViewById(R.id.iv);
    	iv.setImageBitmap(bm);
    }
}
