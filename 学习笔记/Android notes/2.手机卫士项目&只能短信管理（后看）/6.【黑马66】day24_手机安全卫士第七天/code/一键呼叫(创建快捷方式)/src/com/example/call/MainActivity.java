package com.example.call;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		installShortcut();
	}

	/**
	 * 创建快捷方式
	 * 发送广播com.android.launcher.action.INSTALL_SHORTCUT，当桌面Launcher接收到这个广播，就会创建快捷方式
	 * Launcher也是一个app
	 * 快捷方式属于桌面Launcher,而桌面已经具备打电话的权限了,所以无需再配打电话的权限
	 * 
	 * 需要权限：<uses-permission android:name="com.android.launcher.permission.INSTALL_SHORTCUT" />
	 */
	private void installShortcut() {
		Intent intent = new Intent();
		// 设置广播类型为com.android.launcher.action.INSTALL_SHORTCUT
		intent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
		// 快捷方式的名称
		intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, "马上呼叫");
		// 快捷方式图标，在资源文件夹中，变成一个bitmap对象
		intent.putExtra(Intent.EXTRA_SHORTCUT_ICON, BitmapFactory.decodeResource(getResources(), R.drawable.home_trojan));
		
		// 快捷方式的意图（动作），点击快捷方式后要做什么
		Intent actionIntent = new Intent();
		actionIntent.setAction(Intent.ACTION_CALL); // 动作是拨打电话
		actionIntent.setData(Uri.parse("tel:110")); // 拨打的号码
		// 把拨打电话的意图，通过广播告诉桌面
		intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, actionIntent);
		
		// 发送广播
		sendBroadcast(intent);
	}
}
