package com.example.drawing;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

public class MainActivity extends Activity {
	
	private ImageView iv;
	int startX; // 上一次的x坐标
	int startY; // 上一次的y坐标
	private Canvas canvas;
	private Paint paint;
	private Bitmap copyBm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //先加载图片
        //因为是在资源文件夹中的，所以这样写
        Bitmap srcBm = BitmapFactory.decodeResource(getResources(), R.drawable.huabu);
        copyBm = Bitmap.createBitmap(srcBm.getWidth(), srcBm.getHeight(), srcBm.getConfig());
        paint = new Paint();
        canvas = new Canvas(copyBm);
        //把原图画在副本上
        canvas.drawBitmap(srcBm, new Matrix(), paint);
        //显示在屏幕上
        iv = (ImageView) findViewById(R.id.iv);
        iv.setImageBitmap(copyBm);        
                
        //设置触摸侦听
        iv.setOnTouchListener(new OnTouchListener() {

        	/*
        	 * 触摸屏幕时此方法调用
        	 * v : 谁触发的，就是谁
        	 * event: 动作事件。有：触摸屏幕，滑动屏幕，离开屏幕。还有当前位置的坐标
        	 */
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				//得到当前的动作事件
				int action = event.getAction();
				switch(action) {
				//触摸屏幕事件
				case MotionEvent.ACTION_DOWN:
					startX = (int) event.getX();//拿到触摸时的x坐标，作为开始x坐标
					startY = (int) event.getY();//拿到触摸时的y坐标，作为开始y坐标
					break;
					
				//滑动屏幕事件
				case MotionEvent.ACTION_MOVE:
					int x = (int) event.getX(); //拿到当前x坐标，作为结束x坐标
					int y = (int) event.getY(); //拿到当前y坐标，作为结束y坐标
					
					//画一条线，开始坐标和本次坐标之间
					canvas.drawLine(startX, startY, x, y, paint);
					
					//每次的结束坐标都是下一次的开始坐标
					startX = x;
					startY = y;
					
					//然后再次把副本显示在屏幕上
					iv.setImageBitmap(copyBm);
					
					System.out.println("滑动屏幕：" + x + ":" + y);
					
					break;
					
				//离开屏幕事件
				case MotionEvent.ACTION_UP:
					break;
				}
				
				/*
				 * false：除了接收到的第一个事件，后续的事件都不处理，而是交给父节点处理
				 * true: 所有时间都给我处理
				 * 
				 * 一个父节点，一个子节点。
				 * 如果两个节点返回的都是false。那么就丢失这个时间，不做处理
				 * 如果两个节点都是ture。那么就是子节点处理。
				 * 实际上第一个接收到事件的是父节点，至于为什么会是子节点处理，后续讲解
				 */
				return true;
			}
        	
        });
    }
}
