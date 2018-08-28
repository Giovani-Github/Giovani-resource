package com.example.wave;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * 开发流程:
 * 1. 重写onTouchEvent,ACTION_DOWN,ACTION_MOVE
 * 2. 封装Wave对象
 * 3. 圆环集合mWaveList
 * 4. addPoint(x,y), 第一次进入,启动绘制
 * 5. 在Handler接受消息
 * 6. flushData,刷新集合中所有圆环的属性
 * 7. invalidate,刷新界面, 继续发消息,形成内循环
 * 8. onDraw, 绘制所有圆环
 * 9. 圆环间距, 透明度变化速度, 宽度变化速度
 * 
 * @author kingLi
 * @date: 2016-6-23 下午7:30:07
 */
public class MyRingWave extends View {
	
	private ArrayList<Wave> mWaveList = new ArrayList<MyRingWave.Wave>();
	private int[] mColors = new int[] { Color.RED, Color.YELLOW, Color.BLUE, Color.GREEN };
	
	private static final int MIN_DIS = 10;// 圆环最小间距
	
	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			flushDate();
			invalidate();
			System.out.println(mWaveList.size());
			
			if (!mWaveList.isEmpty()) {
				System.out.println(mWaveList.size());
				sendEmptyMessageDelayed(0, 20);
			}
		};
	};
	
	public MyRingWave(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public MyRingWave(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	
	public MyRingWave(Context context) {
		super(context);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		// 循环绘制出集合里所有的圆环
		for (Wave wave : mWaveList) {
			canvas.drawCircle(wave.cx, wave.cy, wave.radius, wave.paint);
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
		case MotionEvent.ACTION_MOVE:
			addPoint((int) event.getX(), (int) event.getY());
			break;
		}
		return true;
	}
	
	/**
	 * 刷新集合里所有圆环的属性
	 * 删除透明度为0的圆环
	 */
	protected void flushDate() {
		ArrayList<Wave> removeList = new ArrayList<MyRingWave.Wave>();
		for (Wave wave : mWaveList) {
			wave.radius += 3;
			
			wave.paint.setStrokeWidth(wave.radius / 3);
			
			// 如果透明度为0了，删除
			if (wave.paint.getAlpha() == 0) {
				removeList.add(wave);
				continue;
			}
			
			int alpha = wave.paint.getAlpha();
			alpha -= 10;
			
			if (alpha < 0) {
				alpha = 0;
			}
			
			wave.paint.setAlpha(alpha);
		}
		
		// 把removeList中的元素，从mWaveList中删除
		mWaveList.removeAll(removeList);
	}
	
	/**
	 * 添加圆心坐标
	 * @param x
	 * @param y
	 */
	private void addPoint(int x, int y) {
		if (mWaveList.isEmpty()) { // 第一次，启动循环绘制
			addWave(x, y);
			mHandler.sendEmptyMessage(0);
		} else {
			Wave lastWave = mWaveList.get(mWaveList.size() - 1);
			if (Math.abs(x - lastWave.cx) > MIN_DIS || Math.abs(y - lastWave.cy) > MIN_DIS) {
				addWave(x, y);
			}
		}
	}

	/**
	 * 添加圆环
	 * @param x
	 * @param y
	 */
	private void addWave(int x, int y) {
		Wave wave = new Wave();
		Paint paint = new Paint();
		
		wave.cx = x;
		wave.cy = y;
		paint.setStrokeWidth(wave.radius / 3);
		paint.setAntiAlias(true);
		paint.setAlpha(255);
		paint.setStyle(Style.STROKE);
		// 设置随机颜色
		int i = (int) (Math.random() * 4);
		paint.setColor(mColors[i]);

		wave.paint = paint;
		mWaveList.add(wave);
	}

	/**
	 * 圆环对象 
	 * @author kingLi
	 * @date: 2016-6-23 下午7:40:04
	 */
	private class Wave {
		private int cx;
		private int cy;
		private int radius;
		private Paint paint;
	}
}
