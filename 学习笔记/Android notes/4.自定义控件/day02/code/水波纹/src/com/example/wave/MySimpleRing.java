package com.example.wave;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.DragEvent;
import android.view.View;

/**
 * 简单的圆 
 */
public class MySimpleRing extends View {

	private Paint mPaint; // 直线的画笔
	private Paint mCirclePaint; // 圆的画笔

	public MySimpleRing(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView();
	}

	public MySimpleRing(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public MySimpleRing(Context context) {
		super(context);
		initView();
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		setMeasuredDimension(200, 200);
	}
	
	private void initView() {
		mPaint = new Paint();
		mPaint.setColor(Color.GREEN);
		mPaint.setStrokeWidth(2); // 画笔大小
		
		mCirclePaint = new Paint();
		mCirclePaint.setColor(Color.RED);
		mCirclePaint.setStyle(Style.STROKE); // 空心圆
		mCirclePaint.setStrokeWidth(2);
		mCirclePaint.setAntiAlias(true); // 去掉锯齿 
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		for (int i = 10; i < 200; i+=20) {
			canvas.drawLine(0, i, 200, i, mPaint); // 水平直线
			canvas.drawLine(i, 200, i, 0, mPaint); // 垂直直线
		}
		
		canvas.drawCircle(getWidth()/2, getHeight()/2, 40, mCirclePaint);
		canvas.drawCircle(getWidth()/2, getHeight()/2, 1, mCirclePaint);
		
		canvas.translate(-20, -20); // 移动画布
		mCirclePaint.setColor(Color.BLACK);
		canvas.drawCircle(getWidth()/2, getHeight()/2, 40, mCirclePaint);
		canvas.drawCircle(getWidth()/2, getHeight()/2, 1, mCirclePaint);
	}
}
