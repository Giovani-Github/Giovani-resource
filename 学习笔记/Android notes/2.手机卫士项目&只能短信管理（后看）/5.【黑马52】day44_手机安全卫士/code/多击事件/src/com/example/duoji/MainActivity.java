package com.example.duoji;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	long[] mHits = new long[4];// 数组长度表示要点击的次数
	
	public void click(View v) {
		// 拷贝数组
		System.arraycopy(mHits, 1, mHits, 0, mHits.length - 1);
		mHits[mHits.length - 1] = SystemClock.uptimeMillis();// 开机后开始计算的时间
		
		// 这其实是一个数学方程式，简化为：SystemClock.uptimeMillis() - mHits[0] <= 500
		// 实际上就是比较第一次点击的时间，和最后一次点击的时间间隔是否不超过 500 毫秒
		if (mHits[0] >= (SystemClock.uptimeMillis() - 500)) {
			Toast.makeText(this, "是男人!!!", Toast.LENGTH_SHORT).show();
		}
	}
}
