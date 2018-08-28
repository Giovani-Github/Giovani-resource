package com.example.hello3;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {
	
	static {
		System.loadLibrary("add");
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void click(View v) {
		System.out.println(add(1, 3));
	}
	
	public native int add(int i, int j);
}
