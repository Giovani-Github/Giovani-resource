package com.example.jumpactivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class SecondActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		
		//获取启动此activity的意图对象
		Intent intent = getIntent();
		Uri uri = intent.getData();
		System.out.println(uri.toString()); // jc2:456
	}
}
