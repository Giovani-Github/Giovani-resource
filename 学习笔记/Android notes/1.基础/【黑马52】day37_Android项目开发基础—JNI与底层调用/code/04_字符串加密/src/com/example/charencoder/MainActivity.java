package com.example.charencoder;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	private EditText et;
	static {
		System.loadLibrary("coder");
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		et = (EditText) findViewById(R.id.et);
		
	}
	
	public void click1(View v) {
		String text = et.getText().toString();
		et.setText(encoder(text, text.length()));
	}
	
	public void click2(View v) {
		String text = et.getText().toString();
		et.setText(decoder(text, text.length()));
	}
	
	public native String encoder(String text, int lenght); //加密
	public native String decoder(String text, int lenght); //解密
}
