package com.example.hmimage;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;

import com.mt.mtxx.image.JNI;

public class MainActivity extends Activity {

	private Bitmap bm;
	private ImageView iv;

	//加载美图秀秀，本地方法编译打包出来的类库
	static {
		System.loadLibrary("mtimage-jni");
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		bm = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory() +  "/wo.jpg");
		iv = (ImageView) findViewById(R.id.iv);
		iv.setImageBitmap(bm);
		
	}
	
	public void click(View v) {
		//所有美图秀秀，美化的本地方法都在这个类
		JNI jni = new JNI();
		
		int width = bm.getWidth();
		int height = bm.getHeight();
		
		//保存所有像素信息的数组，大小是总像素
		int[] pixels = new int[width*height];
		/*
		 * 把所有像素颜色信息，保存到pixels
		 * offset：你要从pixels的第几个元素，开始写像速信息
		 * stride： 逐行扫描，每次扫描一行的像素，必须大于或等于图片宽
		 * x： 第一个读取的这张图片的像素，的x坐标
		 * y： 第一个读取的这张图片的像素，的y坐标
		 */
		bm.getPixels(pixels, 0, width, 0, 0, width, height);
		
		/*
		 * paramArrayOfInt:保存所有像素颜色信息的数组
		 * paramInt1： 图片宽
		 * paramInt2： 图片高
		 * 
		 * 此方法是通过改变pixels的像素颜色值，来实现美化效果
		 */
		jni.StyleLomoC(pixels, width, height);
		
		//重新创建一个bitma，使用pixels像素颜色信息创建
		Bitmap bmNew = Bitmap.createBitmap(pixels, width, width, bm.getConfig());
		iv.setImageBitmap(bmNew);
	}
}
