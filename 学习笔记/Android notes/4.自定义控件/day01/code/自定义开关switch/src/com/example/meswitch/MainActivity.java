package com.example.meswitch;

import com.example.meswitch.MySwitch.OnCheckChangeListener;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		MySwitch mswitch = (MySwitch) findViewById(R.id.mswitch);
		mswitch.setOnCheckChangeListener(new OnCheckChangeListener() {

			@Override
			public void onCheckChanged(View view, boolean isChecked) {
				Toast.makeText(MainActivity.this, "开关当前：" + isChecked, Toast.LENGTH_SHORT).show();				
			}
			
		});
	}
}
