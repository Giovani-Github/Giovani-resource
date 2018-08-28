package com.example.specialimage;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.Menu;
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

    	Matrix m = new Matrix();
    	
/***************************************************************************************************/
    	//加特效
    
    	//直线
    	//canvas.drawLine(20, 20, 40, 40, paint);
    	
    	/*
    	 * 平移
    	 * dx：水平方向平移20
    	 * dy: 竖直方向平移40
    	 * 
    	 * imageView显示是有个显示范围的，如果移动出了范围，那出去的那部分就看不见了
    	 * */
    	//m.setTranslate(40, 40);
    	
    	/*
    	 * 缩放
    	 * sx:水平方向的缩放比例， 1表示不缩放, 大于1放大，小于1缩小
    	 * sy:竖直方向的缩放比例， 1表示不缩放, 大于1放大，小于1缩小
    	 *
    	 * 是有缩放点的
    	 * px: 缩放点的x坐标
    	 * py: 缩放点的y坐标
    	 */
    	//m.setScale(0.5f, 0.5f); // 缩放点在左上角
    	//m.setScale(0.5f, 0.5f, copyBm.getWidth()/2, copyBm.getHeight()/2);// 缩放点在中间
    	
    	/*
    	 * 旋转
    	 * degrees: 旋转角度
    	 *
    	 * 是有旋转点的
    	 * px: 旋转点的x坐标
    	 * py: 旋转点的y坐标
    	 */
    	//m.setRotate(45);
    	//m.setRotate(45, copyBm.getWidth()/2, copyBm.getHeight()/2);
    	
    	/*
    	 * 镜面（水平翻转）
    	 * 把x轴的坐标全部改为负的
    	 * 使用缩放，1就是不缩放，-1就是图片反过来，然后不缩放
    	 * 这样x轴就会超出屏幕，我们需要把它平移到原来的位置
    	 * 
    	 * 两个特效一起使用，第一个使用setxxx, 第二个使用postxxx
    	 */
    	//m.setScale(-1, 1);
    	//m.postTranslate(copyBm.getWidth(), 0);
    	/*
    	 * 倒影（竖直翻转）
    	 */
    	m.setScale(1, -1);
    	m.postTranslate(0, copyBm.getHeight());
    	
    	
    	
/***************************************************************************************************/    	
   
    	
    	/*
    	 * 把原图的内容绘制在副本上
    	 * bitmap：原图
    	 * matrix: 矩阵，做特效用的
    	 * paint: 画笔
    	 */
    	canvas.drawBitmap(srcBm, m, paint);
    	 	
    	
    	//显示原图和副本在屏幕
    	ImageView iv_src = (ImageView) findViewById(R.id.iv_src);
    	ImageView iv_copy = (ImageView) findViewById(R.id.iv_copy);
    	iv_src.setImageBitmap(srcBm);
    	iv_copy.setImageBitmap(copyBm);
    }
}
