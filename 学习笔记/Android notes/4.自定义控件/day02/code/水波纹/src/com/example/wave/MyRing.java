package com.example.wave;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class MyRing extends View{

	private int cx;
	private int cy;
	private int mRadius; // 圆的半径
	private Paint mPaint;
	
	Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			mRadius += 3;
			mPaint.setStrokeWidth(mRadius/3);
			int alpha = mPaint.getAlpha();
			alpha -= 10;
			
			if (alpha < 0) {
				alpha = 0;
			}
			
			mPaint.setAlpha(alpha);
			
			invalidate();

			if (alpha > 0) {
				mHandler.sendEmptyMessageDelayed(0, 50);
			}
		};
	};
	
	public MyRing(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView();
	}

	public MyRing(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public MyRing(Context context) {
		super(context);
		initView();
	}
	
	private void initView() {
		mRadius = 0;
		
		mPaint = new Paint(); 
		mPaint.setColor(Color.RED);
		mPaint.setStyle(Style.STROKE);
		mPaint.setStrokeWidth(mRadius/3);
		mPaint.setAntiAlias(true);
		mPaint.setAlpha(255); // 0-255， 255 完全不透明
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawCircle(cx, cy, mRadius, mPaint);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			cx = (int) event.getX();
			cy = (int) event.getY();
			
			initView();
			
			mHandler.sendEmptyMessage(0);
			break;
		}
		return super.onTouchEvent(event);
	}
}
