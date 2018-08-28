package com.example.admin;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	private DevicePolicyManager mDPM;
	private ComponentName mDeviceAdminSample;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// 获取设备策略服务
		mDPM = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
		mDeviceAdminSample = new ComponentName(this, AdminReceiver.class);
		
		// 点击图标就锁屏
//		mDPM.lockNow();
//		finish();
	}
	
	/**
	 * 激活设备管理器
	 * 也可以在设置->安全->设备管理器中手动激活
	 * 
	 * @param view
	 */
	public void activeAdmin(View view) {
		Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
		intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, mDeviceAdminSample);
		intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "有了设备管理器，好牛逼");
		startActivity(intent);
	}

	/**
	 * 一键锁屏
	 * 
	 * @param view
	 */
	public void lockScreen(View view) {
		// 判断设备管理器是否已经激活
		if(mDPM.isAdminActive(mDeviceAdminSample)) {
			mDPM.lockNow();
			/*
			 *  重置开屏时的密码， 可以在系统设置中清除
			 *  flags: 
			 *  	0：设置好密码的情况下，不允许其他设备管理器重新设置密码。一般为这个
			 *  	RESET_PASSWORD_REQUIRE_ENTRY：是否允许已经设置密码的情况下，另外的设备管理器也可以重新设置密码
			 */
			
			//mDPM.resetPassword("123466", 0);
		} else {
			Toast.makeText(this, "必须先激活设备管理器", 0).show();
		}
	}
	
	/**
	 * 卸载程序
	 * 
	 * @param view
	 */
	public void unInstall(View view) {
		// 取消激活设备管理器
		mDPM.removeActiveAdmin(mDeviceAdminSample);
		
/*		// 卸载程序
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.addCategory(Intent.CATEGORY_DEFAULT);
		// 传递要卸载应用的包名
		intent.setData(Uri.parse("package:" + getPackageName()));
		startActivity(intent);*/
		
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.addCategory(Intent.CATEGORY_DEFAULT);
		intent.setData(Uri.parse("package:" + getPackageName()));
		startActivity(intent);
	}
}
