package com.example.cache;

import java.lang.reflect.Method;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.pm.IPackageStatsObserver;
import android.content.pm.PackageManager;
import android.content.pm.PackageStats;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	private EditText etPackage;
	private TextView tvResult;
	private PackageManager mPm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		etPackage = (EditText) findViewById(R.id.et_package);
		tvResult = (TextView) findViewById(R.id.tv_result);
		
		mPm = getPackageManager();
	}
	
	public void click(View v) {
		String packageName = etPackage.getText().toString();
		if (!TextUtils.isEmpty(packageName)) {
			// mPM.getPackageSizeInfo(packageName, IPackageStatsObserver.Stub对象); 被隐藏，不能用，要用反射调用
			// 权限:android.permission.GET_PACKAGE_SIZE
			try {
				Method method = mPm.getClass().getMethod("getPackageSizeInfo", String.class, IPackageStatsObserver.class);
				method.invoke(mPm, packageName, new MyObserver());
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	class MyObserver extends IPackageStatsObserver.Stub {

		/**
		 * 这个方法是在子线程运行的
		 */
		public void onGetStatsCompleted(PackageStats pStats, boolean succeeded)
				throws RemoteException {
			String cacheSize = "缓存大小：" + Formatter.formatFileSize(MainActivity.this, pStats.cacheSize);
			String codeSize = "程序大小：" + Formatter.formatFileSize(MainActivity.this, pStats.codeSize);
			String dataSize = "数据大小：" + Formatter.formatFileSize(MainActivity.this, pStats.dataSize); // 数据：数据库,SharedPreferences...
			
			final String result = cacheSize + "\n" + codeSize + "\n" + dataSize;
			
			System.out.println(result);
			
			runOnUiThread(new Runnable() {
				
				public void run() {
					tvResult.setText(result);
				}
			});
		}
		
	}
}
