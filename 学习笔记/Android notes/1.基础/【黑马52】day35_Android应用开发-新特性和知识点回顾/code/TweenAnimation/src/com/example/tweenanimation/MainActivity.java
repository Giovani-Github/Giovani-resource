package com.example.tweenanimation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class MainActivity extends Activity {

	private ImageView iv;
	private RotateAnimation ra;
	private AlphaAnimation aa;
	private ScaleAnimation sa;
	private TranslateAnimation ta;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		iv = (ImageView) findViewById(R.id.iv);
	}
	
	public void translate(View v) {
		/*
		 * 得到位移动画
		 * fromXDelta: x坐标的起始位置，iv的真实x + fromXDelta
		 * toXDelta: x坐标的结束位置， iv的真实x +　toXDelta
		 * fromYDelta:　y坐标的起始位置，iv的真实y +　fromYDelta
		 * toYDelta:　ｙ坐标的结束位置，iv的真实y + toYDelta
		 */
		//TranslateAnimation ta = new TranslateAnimation(10, 100, 20, 200);
		
		
		 /* 得到位移动画
		 * fromXType ：起始x坐标的类型
		 * 		> Animation.RELATIVE_TO_SELF: 相对于自身
		 * 		> Animation.RELATIVE_TO_PARENT：相对于父元素
		 * 
		 * fromXValue: x坐标的起始位置，分两种类型
		 * 		> 相对与自身：iv的真实x + (fromXValue * iv宽)
		 * 		> 相对于父元素： iv的真实x + (fromXValue * 父元素宽)
		 * 
		 * toXType：结束坐标的类型
		 * 		> Animation.RELATIVE_TO_SELF: 相对于自身
		 * 		> Animation.RELATIVE_TO_PARENT：相对于父元素
		 * 
		 * toXValue: x坐标的结束位置，分两种类型
		 * 		> 相对与自身：iv的真实x + (toXValue * iv宽)
		 * 		> 相对于父元素： iv的真实x + (toXValue * 父元素宽)
		 * 
		 * fromYType：起始y坐标的类型
		 * fromYValue：y坐标的起始位置
		 * toYType：结束y坐标的类型
		 * toYValue：与坐标的结束位置
		 */
		ta = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 1, Animation.RELATIVE_TO_SELF, 2, 
				Animation.RELATIVE_TO_SELF, 1, Animation.RELATIVE_TO_SELF, 2);
		
		//设置播放时间
		ta.setDuration(2000);
		//设置重复次数
		ta.setRepeatCount(1);
		/*
		 * 设置循环模式
		 * Animation.REVERSE：从结束位置往回播放
		 * Animation.RESTART: 从头开始播放，默认的就这个
		 */
		ta.setRepeatMode(Animation.RESTART);
		//开始播放动画
		iv.startAnimation(ta);		
	}

	public void scale(View v) {
		/*
		 * 获取缩放动画
		 * fromX: x坐标的起始位置，相对于自身，iv宽 * fromX
		 * toX: x坐标的结束位置，相对与自身，iv宽 * toX
		 * fromY: y坐标的起始位置，相对于自身，iv高 * fromY
		 * toY: y坐标的结束位置，相对于自身，iv高  * toY
		 */
		 sa = new ScaleAnimation(0.5f, 1, 0.5f, 1);
		
		/*
		 * 获取缩放动画
		 * pivotX: 缩放点x坐标，iv真实的x + pivotX
		 * pivotY：缩放点y坐标， iv真实的y + pivotY
		 * 默认缩放点在iv左上角
		 */
		//ScaleAnimation sa = new ScaleAnimation(0.5f, 1, 0.5f, 1, iv.getWidth()/2, iv.getHeight()/2);
		
		/*
		 * 获取缩放动画
		 * pivotXType: x坐标缩放点的类型
		 * 		> Animation.RELATIVE_TO_SELF: 相对于自身
		 * 		> Animation.RELATIVE_TO_PARENT：相对于父元素
		 * pivotXValue: x坐标缩放点的位置，有两种类型
		 * 		> 相对与自己: iv的真实x + (pivotXValue * iv的宽)
		 * 		> 相对与父元素：iv的真实x + (pivotXValue * 父元素的宽)
		 * pivotYType：y坐标缩放点的类型
		 * pivotYValue：y坐标缩放点的位置
		 */
		//ScaleAnimation sa = new ScaleAnimation(0.5f, 1, 0.5f, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		sa.setDuration(2000);
		sa.setRepeatCount(1);
		sa.setRepeatMode(Animation.REVERSE);
		//把动画填充在结束的位置上。（把结束的动画，画在屏幕上）
		sa.setFillAfter(true);
		iv.startAnimation(sa);
	}
	
	public void alpha(View v) {
		aa = new AlphaAnimation(0, 1);
		aa.setDuration(2000);
		aa.setRepeatCount(1);
		aa.setRepeatMode(Animation.REVERSE);
		iv.startAnimation(aa);
	}
	
	public void rotate(View v) {
		ra = new RotateAnimation(0, 180);
		//RotateAnimation ra = new RotateAnimation(0, 180, iv.getWidth()/2, iv.getHeight()/2);
		//RotateAnimation ra = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		ra.setDuration(2000);
		ra.setRepeatCount(1);
		ra.setRepeatMode(Animation.REVERSE);
		iv.startAnimation(ra);
	}
	
	public void fly(View v) {
		/*
		 * 获取动画集合
		 * shareInterpolator: 校对器
		 * 	> true: 使用集合的校对器
		 * 	> false：使用每个动画自己的校对器
		 * 
		 * 校对器：
		 * 	ra.setDuration(2000);
		 * 	ra.setRepeatCount(1);
		 * 	...
		 */
		AnimationSet as = new AnimationSet(false);
		//添加动画到集合
		as.addAnimation(aa);
		as.addAnimation(ra);
		as.addAnimation(sa);
		as.addAnimation(ta);
		
		iv.startAnimation(as);
	}
}
