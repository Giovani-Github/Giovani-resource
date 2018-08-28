package com.example.myview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class MyView extends View {

	public MyView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public MyView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	//在组件里绘制内容
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		Paint paint = new Paint();
		paint.setColor(Color.RED);
		paint.setTextSize(20);
		
		//绘制文本
		canvas.drawText("哈哈", 10, 20, paint);
		
		/*
		 * 绘制矩形
		 * left:左，左线距离左边的像素大小
		 * top：上，上线距离上边的像素大小
		 * right：右线距离左边的像素大小
		 * bottom：下线距离上边的像素大小
		 */
		canvas.drawRect(50, 100, 100, 200, paint);
	}
}