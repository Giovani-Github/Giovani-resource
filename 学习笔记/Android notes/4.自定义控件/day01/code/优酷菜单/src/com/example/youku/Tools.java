package com.example.youku;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;

public class Tools {
	public static void hideView(ViewGroup view) {
		hideView(view, 0);
	}
	
	public static void hideView(ViewGroup view, long delay) {
		RotateAnimation animation = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 1f);
		animation.setDuration(500);
		animation.setFillAfter(true); // 保持动画结束后的状态
		animation.setStartOffset(delay); // 延时执行
		
		view.startAnimation(animation);
		
		// 动画执行后，原来位置还是存在那个控件的。动画只是复制了一份控件，拿来执行。把原来的控件隐藏
		
		// 禁用所有孩子(该控件所有子节点)的点击事件
		int childCount = view.getChildCount();
		for (int i = 0; i < childCount; i++) {
			View childAt = view.getChildAt(i);
			childAt.setEnabled(false); // 设置控件是否可用(禁用点击事件)
		}
	}
	
	public static void showView(ViewGroup view) {
		showView(view, 0);
	}
	
	public static void showView(ViewGroup view, long delay) {
		RotateAnimation animation = new RotateAnimation(180, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 1f);
		animation.setDuration(500);
		animation.setFillAfter(true); // 保持动画结束后的状态
		animation.setStartOffset(delay); // 延时执行
		
		view.startAnimation(animation);
		
		// 开启所有孩子(该控件所有子节点)的点击事件
		int childCount = view.getChildCount();
		for (int i = 0; i < childCount; i++) {
			View childAt = view.getChildAt(i);
			childAt.setEnabled(true); // 设置控件是否可用(开启点击事件)
		}
		
	}
} 
