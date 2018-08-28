package com.example.drawableanimation;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.view.Menu;
import android.widget.ImageView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ImageView iv = (ImageView) findViewById(R.id.iv);
		//把帧动画的资源文件指定为iv的背景
		iv.setBackgroundResource(R.drawable.animation);
		//获取iv的背景
		AnimationDrawable ad = (AnimationDrawable) iv.getBackground();
		//开始切换显示图片，形成动画效果
		ad.start();
	}
}
