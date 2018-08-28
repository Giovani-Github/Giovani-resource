package com.example.hellojni;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {
	
	//加载打包完毕的so类库
	static {
		//类库名是在Android.mk中定义的名字
		System.loadLibrary("hello");
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void click(View v) {
		//调用本地方法，会返回一个字符串
		System.out.println(helloC());
	}
	
	//实现一个本地方法，这个方法体由c实现，一旦调用，就会调用c的代码
	public native String helloC();
}
