package com.example.monitor;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;



public class MyProgressBar extends View {

	private int pressure; //当前压力值
	private int max; //最大压力值
	public MyProgressBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public MyProgressBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public MyProgressBar(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public void setPressure(int pressure) {
		this.pressure = pressure;
		//使当前视图无效，如果当前视图是可见的，那么onDraw会调用，只能在主线程使用
//		invalidate();
		//子线程可以使用
		postInvalidate();
	}
	
	public void setMax(int max) {
		this.max = max;
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		
		Paint paint = new Paint();
		
		//根据压力大小，变换矩形颜色
		if(pressure < 50){
			paint.setColor(Color.GREEN);
		}
		else if(pressure < 80){
			paint.setColor(0xffff8800);
		}
		else {
			paint.setColor(Color.RED);
			
		}
		
		//绘制矩形
		canvas.drawRect(10, 10 + max - pressure, 30, 10 + max, paint);
	}
}
