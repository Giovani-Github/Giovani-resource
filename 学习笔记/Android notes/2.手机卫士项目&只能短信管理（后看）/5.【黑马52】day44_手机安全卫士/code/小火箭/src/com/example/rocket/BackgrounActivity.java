package com.example.rocket;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

/**
 * 火箭烟雾背景的activit
 * 
 * @author Administrator
 * 
 */
public class BackgrounActivity extends Activity {
	private ImageView ivTop;
	private ImageView ivBottom;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_backgroun);
		ivTop = (ImageView) findViewById(R.id.iv_top);
		ivBottom = (ImageView) findViewById(R.id.iv_bottom);
		
		AlphaAnimation anim = new AlphaAnimation(0, 1);
		anim.setDuration(1000);
		// 动画结束后不变回原样
		anim.setFillAfter(true);
		
		// 启动动画
		ivTop.startAnimation(anim);
		ivBottom.startAnimation(anim);
		
		// 延时1秒关闭activit
		new Handler().postDelayed(new Runnable() {
			
			public void run() {
				finish();
			}
		}, 1000);
	}
}
