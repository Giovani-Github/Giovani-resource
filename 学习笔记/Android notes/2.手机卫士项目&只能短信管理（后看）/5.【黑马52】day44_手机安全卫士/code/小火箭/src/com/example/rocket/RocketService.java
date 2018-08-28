package com.example.rocket;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

public class RocketService extends Service {

	private WindowManager mWM;
	private View view;
	
	int winWidth;
	
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			// 拿到y值，更改小火箭的y坐标
			params.y = msg.arg1;
			// 更新小火箭位置
			mWM.updateViewLayout(view, params);
		};
	};
	
	// view的开始坐标
	int startX; 
	int startY;
	private WindowManager.LayoutParams params;
	private int winHeight;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	
	/**
	 * 如果要拖动浮窗，需要权限：android.permission.SYSTEM_ALERT_WINDOW
	 */
	@Override
	public void onCreate() {
		super.onCreate();

		mWM = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);

		params = new WindowManager.LayoutParams();
		params.height = WindowManager.LayoutParams.WRAP_CONTENT;
		params.width = WindowManager.LayoutParams.WRAP_CONTENT;
		params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
		// 为了能触摸浮窗，这个不要| WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
				| WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
		params.format = PixelFormat.TRANSLUCENT;

		// 为了浮窗权限够大，可以一直触摸。需要TYPE_PHONE
		params.type = WindowManager.LayoutParams.TYPE_PHONE;

		// 设置浮窗的重心为图片左上角，而不是图片重心
		params.gravity = Gravity.LEFT + Gravity.TOP;
		params.setTitle("Toast");
		
		// 小火箭布局填充成view
		view = View.inflate(this, R.layout.rocket, null);
		
		// 让view显示在窗口
		mWM.addView(view, params);
		
		// 屏幕的宽高
		winWidth = mWM.getDefaultDisplay().getWidth();
		winHeight = mWM.getDefaultDisplay().getHeight();
		
		// 启动火箭的帧动画
		ImageView ivRocket = (ImageView) view.findViewById(R.id.iv_rocket);
		ivRocket.setBackgroundResource(R.drawable.rocket_animation);
		AnimationDrawable rocketAnimation = (AnimationDrawable) ivRocket.getBackground();
		rocketAnimation.start();
		
		// 拖拽火箭
		view.setOnTouchListener(new OnTouchListener() {
			
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					// 初始化起点坐标
					startX = (int) event.getRawX();
					startY = (int) event.getRawY();
					break;
				case MotionEvent.ACTION_MOVE:
					// 拿到当前移动到的坐标
					int endX = (int) event.getRawX();
					int endY = (int) event.getRawY();
					
					// 计算偏移量
					int dx = endX - startX;
					int dy = endY - startY;
					
					// 设置浮窗的位置
					params.x += dx;
					params.y += dy;
					
					// 当浮窗在边界时，虽然显示上没有跑出边界，实际上坐标已经出了边界了
					if(params.x < 0) {
						params.x = 0;
					}
					if(params.y < 0) {
						params.y = 0;
					}
					// 浮窗的右边不能出边界
					if(params.x > winWidth-view.getWidth()) {
						params.x = winWidth-view.getWidth();
					}
					// 浮窗的下面不能出边界
					if(params.y > winHeight-view.getHeight()) {
						params.y = winHeight-view.getHeight();
					}
					
					// 更新浮窗位置
					mWM.updateViewLayout(view, params);
					
					// 重新初始化起点坐标（当前移动到的坐标就是下一次的开始坐标）
					startX = endX;
					startY = endY;
					
					break;
				case MotionEvent.ACTION_UP:
					// 判断火箭是否移动到屏幕底部的位置
					if(params.x > 40 && params.x < 250 && params.y > winHeight-100) {
						// 发射火箭
						sendRocket();
						
						// 同时启动火箭背景
						Intent intent = new Intent(RocketService.this, BackgrounActivity.class);
						// 因为service没有页面，就没有任务栈。activit需要任务栈存放。所以新建一个任务栈
						intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						startActivity(intent);
					}
					break;

				default:
					break;
				}
				return true;
			}
		});
	}
	
	/**
	 * 发送火箭
	 */
	public void sendRocket() {
		// 把小火箭的位置强制更改为水平居中
		params.x = winWidth/2 - view.getWidth()/2;
		mWM.updateViewLayout(view, params);
		
		new Thread(){
			public void run() {
				int pos = 380;
				for(int i=0; i<10; i++) {
					int y = pos - 38*i;
					
					// 控制火箭发射的速度
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					// 发送消息，让主线程更新火箭的位置
					Message msg = handler.obtainMessage();
					msg.arg1 = y;
					handler.sendMessage(msg);
				}
			};
		}.start();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if(mWM!= null && view != null) {
			mWM.removeView(view);
			view = null;
		}
	}
}
