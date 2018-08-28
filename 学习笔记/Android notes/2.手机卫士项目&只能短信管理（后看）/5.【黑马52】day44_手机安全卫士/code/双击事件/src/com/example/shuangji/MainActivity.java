package com.example.shuangji;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	private long firstClickTime; // 第一次点击的时间

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void click(View v) {
		// 第一次点击的时间等于0表示第一次有点击过
		if(firstClickTime > 0) {
			// 第一次点击的时间与这次点击的时间间隔500毫秒
			if(System.currentTimeMillis() - firstClickTime > 500) {
				Toast.makeText(this, "双击", 0).show();
				// 重置第一次点击时间
				firstClickTime = 0;
			}
		}
		firstClickTime = System.currentTimeMillis();
	}
}
