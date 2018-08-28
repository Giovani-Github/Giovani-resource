package com.example.ipcall;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
	private EditText et_ip;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_ip = (EditText) findViewById(R.id.et_ip);
    }
    
    public void click(View v) {
    	//用SharedPreference保存数据
    	SharedPreferences spf = getSharedPreferences("ip", MODE_PRIVATE);
    	Editor ed = spf.edit();
    	ed.putString("ip", et_ip.getText().toString());
    	ed.commit();
    }
}
