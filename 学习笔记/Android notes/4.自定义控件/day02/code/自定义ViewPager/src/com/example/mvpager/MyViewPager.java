package com.example.mvpager;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * 1. 写一个类继承ViewGroup
 * 2. 重写onLayout方法, 保证孩子正常显示(一字排开)
 * 3. 重写onTouchEvent, 手势识别器(onScroll),scrollby
 * 4. 监听手指抬起事件,根据当前滑动后位置,确定下一个页面, scrollTo
 * 5. 防止pos过大
 * 6. 平滑移动, Scroller, startScroll->computeScroll
 * 7. 增加RadioButton, 监听自定义viewpager页面切换(回调接口),更改raidoButton选中位置
 * 8. 监听RadioButton切换事件, 更改页面
 * 9. 增加测试页面(ScrollView)
 * 11. 重写onMeasure,测量每个孩子
 * 12. 重写onInterceptTouchEvent, 在适当时机(水平滑动),中断事件传递
 * @author Administrator
 *
 */
public class MyViewPager extends ViewGroup {

	private GestureDetector mGestureDetector; // 手势识别器
	private Scroller mScroller;
	OnPageChangeListener onPageChangeListener;

	public MyViewPager(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView();
	}
	public MyViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public MyViewPager(Context context) {
		super(context);
		initView();
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		
		// 需要手动测量所有孩子的宽高，解决测试页面无法展示的问题
		for (int i = 0; i < getChildCount(); i++) {
			// 如果孩子还有孩子，那么孩子的所有孩子会自动测量
			getChildAt(i).measure(widthMeasureSpec, heightMeasureSpec);
		}
	}

	// Andriod底层绘制一个界面时:onMeasure(测量view的宽高), onLayout(安放位置), onDraw(绘制)
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// 当添加了子控件的时候，需要手动设置他们的位置，否则MyViewPager不知道要放在那里，从而显示不出来
		// 一字排开安放子空间
		int childCount = getChildCount();
		for (int i = 0; i < childCount; i++) {
			getChildAt(i).layout(0 + i * getWidth(), 0, (i+1) * getWidth(), getHeight());
		}
	}
	
	private void initView() {
		mGestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {
			// 手势滑动，distanceX滑动的距离
			@Override
			public boolean onScroll(MotionEvent e1, MotionEvent e2,
					float distanceX, float distanceY) {
				scrollBy((int)distanceX, 0); // 相对于当前位置。滑动多少，控件就跟着滑动移动多少
				
				return super.onScroll(e1, e2, distanceX, distanceY);
			}
		});
		
		mScroller = new Scroller(getContext()); // 初始化滑动器
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// 交给手势识别器处理
		mGestureDetector.onTouchEvent(event);
		
		switch (event.getAction()) {
		case MotionEvent.ACTION_UP:
			// 确定要显示页面的位置
			int scrollX = getScrollX(); // 1. 当前所在的x坐标
			int pos = (scrollX + getWidth() / 2) / getWidth(); // 2. 根据当前移动距离，计算要跳转到哪个页面。同时解决了左边界超出问题
			
			if (pos > getChildCount() - 1) {
				pos = getChildCount() - 1;
			}
			
//			scrollTo(pos * getWidth(), 0); // 3. 跳转到相应页面
			
			setCurrentItem(pos);
			
			break;
		}
		return true;
	}
	
	// 当调用startScroll后,系统会不断回调此方法
	@Override
	public void computeScroll() {
		if (mScroller.computeScrollOffset()) { // 判断滑动回调有没有结束
			int currX = mScroller.getCurrX(); // 获取当前应该移动到的位置
			scrollTo(currX, 0);
			invalidate(); // 刷新view
		}
	}
	
	public void setCurrentItem(int position) {
		// 平滑切换页面
		int distance = position * getWidth() - getScrollX(); // 要移动的距离
		// 参1:开始x;参2:开始y;参3:x偏移,参4:y偏移;参5:滑动时间， 平滑距离越长，时间越久
		// 此方法不会产生滑动,而是会导致不断回调computeScroll,需要在这个方法中处理滑动逻辑
		mScroller.startScroll(getScrollX(), 0, distance, Math.abs(distance));
		invalidate(); // 刷新view,保证滑动器正常运行
		
		if (onPageChangeListener != null) {
			onPageChangeListener.onPageSelected(position);
		}
	}
	
	private int startX;
	private int startY;
	
	// 事件中断 onInterceptTouchEvent->onTouchEvent
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			// 将ACTION_DOWN传递给手势识别器, 避免事件丢失
			mGestureDetector.onTouchEvent(ev);
			startX = (int) ev.getX();
			startY = (int) ev.getY();
			break;

		case MotionEvent.ACTION_MOVE:
			int endX = (int) ev.getX();
			int endY = (int) ev.getY();
			
			int dx = Math.abs(endX - startX);
			int dy = Math.abs(endY - startY);
			
			System.out.println(dx + ":" + dy);
			if (dx > dy) {
				// 左右滑动
				// 中断事件传递, 不允许孩子响应事件了, 由父控件处理
				return true;
			}
			break;
		}
		return false;
	}
	
	public void setonPageChangeListener(OnPageChangeListener onPageChangeListener) {
		this.onPageChangeListener = onPageChangeListener;
	}

	/**
	 * 页面切换时的回调接口
	 * 
	 * @author Administrator
	 *
	 */
	public interface OnPageChangeListener {
		/**
		 * 页面切换后调用
		 * 
		 * @param position 切换后的页面是第几个页面
		 */
		public void onPageSelected(int position);
	}
}
