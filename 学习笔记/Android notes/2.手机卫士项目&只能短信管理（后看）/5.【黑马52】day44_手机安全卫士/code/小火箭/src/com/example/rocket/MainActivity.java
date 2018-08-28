package com.example.rocket;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	// 启动火箭服务
	public void startRocket(View v) {
		startService(new Intent(this, RocketService.class));
		finish();
	}
	// 停止火箭服务
	public void stopRocket(View v) {
		stopService(new Intent(this, RocketService.class));
	}
}
