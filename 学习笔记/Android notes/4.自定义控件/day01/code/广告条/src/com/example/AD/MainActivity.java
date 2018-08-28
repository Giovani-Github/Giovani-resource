package com.example.AD;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	private int[] mImageId = { R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e }; // 要展示的广告图片的id 
	private final String[] mImageDes = { "巩俐不低俗，我就不能低俗", "朴树又回来啦！再唱经典老歌引万人大合唱", "揭秘北京电影如何升级", "乐视网TV版大派送", "热血屌丝的反杀" }; // 图片标题集合
	private int mPreviousPos;// 上一个页面位置
	
	private ViewPager vp_pager;
	private TextView tv_title;
	private LinearLayout ll_container;
	
	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			// 翻到下一页
			int currentItem = vp_pager.getCurrentItem();
			vp_pager.setCurrentItem(++currentItem);
			
			// 这样做，相当于递归了，循环的发送消息
			mHandler.sendEmptyMessageDelayed(0, 2000); // 两秒后发送消息
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		vp_pager = (ViewPager) findViewById(R.id.vp_pager);
		tv_title = (TextView) findViewById(R.id.tv_title);
		ll_container = (LinearLayout) findViewById(R.id.ll_container);
		
		vp_pager.setAdapter(new MyPagerAdapter());
		vp_pager.setCurrentItem(mImageId.length * 10000); // 这样可以往回滑动
		
		tv_title.setText(mImageDes[0]); // 设置初始化广告标题
		
		// 设置滑动侦听
		vp_pager.setOnPageChangeListener(new OnPageChangeListener() {
			
			// 某个页面被选中(滑到某个页面)
			@Override
			public void onPageSelected(int position) {
				int pos = position % mImageId.length;
				tv_title.setText(mImageDes[pos]); // 设置广告标题
				
				// 设置小圆点的状态
				ll_container.getChildAt(pos).setEnabled(true); // 当前小圆点
				ll_container.getChildAt(mPreviousPos).setEnabled(false); // 上一个小圆点
				
				mPreviousPos = pos; // 当前页就是下一页的上一页
			}
			
			// 滑动过程中
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}
			// 滑动状态变化
			@Override
			public void onPageScrollStateChanged(int state) {}
		});
		
		// 动态初始化小圆点
		for (int i = 0; i < mImageId.length; i++) {
			// 小圆点
			ImageView view = new ImageView(this);
			view.setBackgroundResource(R.drawable.selector_shape_point);
			
			if (i != 0) { // 第一个页面的小圆点设置为可用，其他页面都为不可用
				view.setEnabled(false);
			}
			
			ll_container.addView(view);  
		}
		
		// 自动轮播
		mHandler.sendEmptyMessageDelayed(0, 2000); // 两秒后发送消息
		
		// 触摸事件侦听
		vp_pager.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN: // 按下
					/*
					 *  清除所有消息和Runnable对象，这样就不会在自动轮播了
					 *  mHandler.post(Runnable r), Runnable就是Callback
					 */
					mHandler.removeCallbacksAndMessages(null); 
					break;

				case MotionEvent.ACTION_UP:
					// 继续轮播
					mHandler.sendEmptyMessageDelayed(0, 2000); // 两秒后发送消息
					break;
				}
				
				return false; // 不能消费掉事件，否则就不能滑动了
			}
		});
	
	}
	
	class MyPagerAdapter extends PagerAdapter {

		@Override
		public int getCount() {
//			return mImageId.length;
			return Integer.MAX_VALUE; // 这样就能滑动很多页，可以通过设置显示每页的内容，实现循环滑动
		}

		// 当前要展示的view是否是instantiateItem返回的view，如果是，才展示
		// arg1:当前要展示的view，arg2:tantiateItem返回的view
		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}
		
		// 类似getView方法, 初始化每个item的布局, viewpager默认自动加载前一张和后一张图片, 保证始终保持3张图片, 剩余的都需要销毁
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			int pos = position % mImageId.length; // 得到的结果始终是0-4，到了4，下一次又是0, 这样就能实现轮回滑动
			
			ImageView view = new ImageView(MainActivity.this);
			view.setBackgroundResource(mImageId[pos]);

			// 将item的布局添加给容器， 这个容器其实就是viewpager
			container.addView(view);
			
			return view;
		}
		
		// item销毁时回调的方法 
		// arg3: 就是要从容器中移除的view
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}
	}
}
