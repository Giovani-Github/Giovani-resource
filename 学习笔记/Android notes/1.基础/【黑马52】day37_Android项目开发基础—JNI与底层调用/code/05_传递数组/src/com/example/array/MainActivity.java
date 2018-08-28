package com.example.array;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {
	
	static {
		System.loadLibrary("arr");
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void click4(View v) {
		int[] arr = {1,2,3};
		//会把内存地址传给本地方法，所以c和java中操作的是同一个内存地址中的数据
		arrEncode(arr);
		System.out.println("调用");
		for (int i : arr) {
			System.out.println(i);
		}
	}
	
	public native void arrEncode(int[] arr);
}
