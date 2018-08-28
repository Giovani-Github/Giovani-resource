package com.example.objectanimator;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private ImageView iv;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		iv = (ImageView) findViewById(R.id.iv);
		iv.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Toast.makeText(MainActivity.this, "点击", 0).show();
			}
		});
	}

	
	public void translate(View v) {
		/*
		 * 得到属性动画
		 * target: 动画作用于哪个组件
		 * propertyName：属性，决定动画效果
		 * 		>translationX: 平移x坐标
		 * 		>translationY: 平移Y坐标
		 * values：可变参数，
		 * 		> 10, 70 : 表示x的开始坐标，iv的真实x + 10。平移到结束坐标, iv的真实x + 70
		 * 		> 10, 70, 20, 100: 10平移到70，再从70平移到20，再从20平移到100。也是上面的算法	
		 */
		ObjectAnimator oa = ObjectAnimator.ofFloat(iv, "translationX", 10, 70, 20, 100);
		//动画持续时间
		oa.setDuration(2000);
		//动画循环次数
		oa.setRepeatCount(1);
		//循环模式
		oa.setRepeatMode(ValueAnimator.REVERSE);
		oa.start();
	}
	
	public void scale(View v) {
		/*
		 * 得到属性动画
		 * propertyName
		 * 		>scaleX:x坐标缩放：iv宽 *1 到 iv宽*1.6，再从iv宽*1.6到iv宽1.2...
		 * 		>scaleY:Y坐标缩放
		 */
		ObjectAnimator oa = ObjectAnimator.ofFloat(iv, "scaleX", 1, 1.6f, 1.2f, 2);
		oa.setDuration(2000);
		oa.start();
	}
	
	public void alpha(View v) {
		/*
		 * 得到属性动画
		 * propertyName
		 * 		>alpha:透明度
		 * 1不透明 - 0完全透明
		 */
		ObjectAnimator oa = ObjectAnimator.ofFloat(iv, "alpha", 0, 0.6f, 0.2f, 1);
		oa.setDuration(2000);
		oa.start();
	}
	
	public void rotate(View v) {
		/*
		 * 得到属性动画
		 * propertyName
		 * 		>rotation:顺时针旋转，如果是负数，可以逆时针旋转	
		 * 		>rotationX:竖直翻转
		 * 		>rotationY:水平翻转
		 */
		ObjectAnimator oa = ObjectAnimator.ofFloat(iv, "rotationX", 0, 180, 90, 360);
		oa.setDuration(2000);
		oa.start();
	}
	
	public void fly(View v) {
		//得到动画集合
		AnimatorSet as = new AnimatorSet();
		
		
		ObjectAnimator oa1 = ObjectAnimator.ofFloat(iv, "translationX", 10, 70, 20, 100);
		oa1.setDuration(2000);
		oa1.setRepeatCount(1);
		oa1.setRepeatMode(ValueAnimator.REVERSE);
		
		ObjectAnimator oa2 = ObjectAnimator.ofFloat(iv, "translationY", 10, 70, 20, 100);
		oa2.setDuration(2000);
		oa2.setRepeatCount(1);
		oa2.setRepeatMode(ValueAnimator.REVERSE);
		
		ObjectAnimator oa3 = ObjectAnimator.ofFloat(iv, "scaleX", 1, 1.6f, 1.2f, 2);
		oa3.setDuration(2000);
		oa3.setRepeatCount(1);
		oa3.setRepeatMode(ValueAnimator.REVERSE);
		
		ObjectAnimator oa4 = ObjectAnimator.ofFloat(iv, "rotation", 0, 180, 90, 360);
		oa4.setDuration(2000);
		oa4.setRepeatCount(1);
		oa4.setRepeatMode(ValueAnimator.REVERSE);
		
		//设置为按顺序播放动画效果
		as.playSequentially(oa1, oa2, oa3, oa4);
		//设置为一起播播放动画效果
//		as.playTogether(oa1, oa2, oa3, oa4);
		as.start();
		
	}
	
	public void xml(View v) {
		//把动画所在的xml文件，填充成动画
		Animator at = AnimatorInflater.loadAnimator(this, R.animator.animation);
		//设置作用于哪个组件
		at.setTarget(iv);
		at.start();
	}
}
