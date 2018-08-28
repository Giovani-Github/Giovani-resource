package com.example.password;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
	
	static {
		System.loadLibrary("pass");
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void click(View v) {
		EditText et = (EditText) findViewById(R.id.et);
		int p = Integer.parseInt(et.getText().toString());
		System.out.println(passjia(p));
	}
	
	public native int passjia(int pass);

}
