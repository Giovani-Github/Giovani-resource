package com.example.popupwindow;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

	private RelativeLayout rlRoot;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		rlRoot = (RelativeLayout) findViewById(R.id.rlRoot);
	}

	
	public void showPopup(View v) {
		
//		PopupWindow mPopup = new PopupWindow();
//		mPopup.setContentView(contentView); // 设置布局
		
		
		// mPopup的布局，可以是一个布局文件
//		TextView contentView = new TextView(this);
//		contentView.setTextColor(Color.BLACK);
//		contentView.setTextSize(12);
//		contentView.setBackgroundColor(Color.RED);
//		contentView.setText("我是小弹窗哦!!!");
		
		// 使用布局文件，做mPopup的布局
		View contentView = View.inflate(this, R.layout.view, null);
		
		/*	
		 * 	初始化弹窗
		 *  也可以直接设置布局
		 *  focusable:是否焦点
		 */
		PopupWindow mPopup = new PopupWindow(contentView, 100, 100, true);
		
		/*
		 * 必须设置背景，让它认为他有背景
		 * 这样点击返回键或者空白处才可以消失
		 * 
		 * new ColorDrawable()：透明的背景
		 */
		mPopup.setBackgroundDrawable(new ColorDrawable());
		
		/*
		 *  显示弹窗，特定位置展现
		 *  parent:父控件
		 *  gravity：重心（居中..）
		 *  x,y:基于重心的偏移量
		 */
//		mPopup.showAtLocation(rlRoot, Gravity.CENTER, 0, 0);
		
		/*
		 * 显示在某个控件的正下方
		 * xoff，yoff：正下方后，在偏移多少（基于重心偏移，重心：正下方）
		 */
		mPopup.showAsDropDown(v, 0, 0);
		
	}
}
