package com.example.meswitch;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class MySwitch extends View {

	private Bitmap mBitmapBg, mBitmapSlide;

	private int MAX_LEFT; // 滑块的最大左边距，就是滑到右边
	private int mSlideLeft; // 滑块当前的左边距
	
	private boolean isOpen; // 开关当前的状态
	private OnCheckChangeListener mOnCheckChangeListener; // 开关状态改变回调接口
	private boolean isClick; // 是不是单击事件
	
	private String nameSpace = "http://schemas.android.com/apk/res/com.example.meswitch";

	// 有style样式的话会走此方法
	public MySwitch(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView();
	}

	// 有属性时走此方法
	public MySwitch(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
		
		// 拿到设置的属性checked
		isOpen = attrs.getAttributeBooleanValue(nameSpace, "checked", false);
		// 拿到设置的属性slide, -1表示没拿到。这个属性设置的是资源图片的id
		int slideId = attrs.getAttributeResourceValue(nameSpace, "slide", -1);

		// 加载自定义滑块图片
		if (slideId > 0) {
			mBitmapSlide = BitmapFactory.decodeResource(getResources(), slideId); 
			MAX_LEFT = mBitmapBg.getWidth() - mBitmapSlide.getWidth(); // 设置滑块最大左边距
		}

		if (isOpen) {
			mSlideLeft = MAX_LEFT;
		} else {
			mSlideLeft = 0; // 设置到当前左边距为0，就是让滑块到左边去
		}
		
		invalidate(); // 刷新view
	}

	// 用代码new对象时,走此方法
	public MySwitch(Context context) {
		super(context);
		initView();
	}

	/**
	 * 初始化自定义控件的方法
	 */
	private void initView() {
		mBitmapBg = BitmapFactory.decodeResource(getResources(), R.drawable.switch_background); // 初始化背景图
		mBitmapSlide = BitmapFactory.decodeResource(getResources(), R.drawable.slide_button); // 初始化滑块图
		MAX_LEFT = mBitmapBg.getWidth() - mBitmapSlide.getWidth(); // 设置滑块最大左边距
		
		this.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (isClick) {
					if (isOpen) {
						mSlideLeft = 0; // 设置到当前左边距为0，就是让滑块到左边去
						isOpen = false;
					} else {
						mSlideLeft = MAX_LEFT;
						isOpen = true; // 让滑块到右边去
					}
					
					// view重绘的方法, 使当前view无效, 就会刷新view, 重新调用onDraw方法， 让view变成有效
					invalidate();
					
					// 回调状态改变接口的方法
					if (mOnCheckChangeListener != null) {
						mOnCheckChangeListener.onCheckChanged(MySwitch.this, isOpen);
					}
				}
			}
		});
	}
	
	private int startX; // 开始x坐标
	private int moveX; // 抬起后滑动的距离
	
	// 控件被触摸会回调该方法
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN: // 按下
			startX = (int) event.getX(); // 1. 记录起始点x坐标
			break;

		case MotionEvent.ACTION_MOVE: // 滑动
			int endX = (int) event.getX(); // 2. 记录移动后的x坐标
			int dx = endX - startX; // 3. 记录x偏移量
			mSlideLeft += dx; // 4. 根据偏移量,更新mSlideLeft
			
			moveX += Math.abs(dx); // 记录滑动的距离，因为dx可能会是负数(划出了左边界)，所以要取绝对值
			
			// 防止出边界
			if (mSlideLeft < 0) {
				mSlideLeft = 0;
			}
			if (mSlideLeft > MAX_LEFT) {
				mSlideLeft = MAX_LEFT;
			}
			
			// 刷新view
			invalidate();
			// 更新开始x左边
			startX = endX;
			break;
			
		case MotionEvent.ACTION_UP: // 抬起
			// 抬起后滑动的总距离小于5像素，就是点击事件
			if (moveX < 5) { 
				isClick = true;
			} else {
				isClick = false;
			}
			
			// 初始化移动的总距离
			moveX = 0;
			
			// 不是点击事件
			if (!isClick) { 
				// 如果抬起后，滑动的距离超过了左边最大距离的一半
				if (mSlideLeft > MAX_LEFT / 2) {
					mSlideLeft = MAX_LEFT;
					isOpen = true; // 让滑块到右边去
				} else {
					mSlideLeft = 0; // 设置到当前左边距为0，就是让滑块到左边去
					isOpen = false;
				}
				
				invalidate(); // 刷新view
				
				// 回调状态改变接口的方法
				if (mOnCheckChangeListener != null) {
					mOnCheckChangeListener.onCheckChanged(MySwitch.this, isOpen);
				}
			}
			break;
		}
		
		// 会根据情况，放回fasle和true，这样子触摸和点击事件就能共存
		return super.onTouchEvent(event);
	}
	
	// 测量view的宽高，设置尺寸的回调
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// 设置控件的宽高, 背景图有多大就有多大
		setMeasuredDimension(mBitmapBg.getWidth(), mBitmapBg.getHeight());
	}
	
	// Andriod底层绘制一个界面时:onMeasure(测量view的宽高), onLayout(安放位置), onDraw(绘制)
	// canvas:画布
	@Override
	protected void onDraw(Canvas canvas) {
		// 画一个200*200的矩形
		//Paint paint = new Paint();
		//paint.setColor(Color.RED);
		//canvas.drawRect(0, 0, 200, 200, paint);
		
		canvas.drawBitmap(mBitmapBg, 0, 0, null); // 画背景图片
		canvas.drawBitmap(mBitmapSlide, mSlideLeft, 0, null); // 画滑块图片
	}
	
	/**
	 * 设置开关状态改变侦听的方法
	 * 
	 * @param onChangeListener
	 */
	public void setOnCheckChangeListener(OnCheckChangeListener onCheckChangeListener) {
		mOnCheckChangeListener = onCheckChangeListener;
	}
	
	/**
	 * 开关状态改变回调接口
	 * 
	 * @author Administrator
	 *
	 */
	public interface OnCheckChangeListener {
		
		/**
		 * 开关状态一改变，就会回调次方法
		 * @param view 当前被点击的view
		 * @param isChecked 当前开关被点击后的状态
		 */
		public void onCheckChanged(View view, boolean isChecked);
	}
}
