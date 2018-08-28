package com.example.activityreturndata;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ContactsActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_else);
	
		ListView lv =(ListView) findViewById(R.id.lv);
		final String[] objects = new String[] {
			"王八",
			"王二",
			"王三"
		};
		
		lv.setAdapter(new ArrayAdapter<String>(this, R.layout.item_else, R.id.tv, objects));
		
		//给ListView设置条目的点击侦听
		lv.setOnItemClickListener(new OnItemClickListener() {
			
			//position:条目的下标
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				//activity销毁时返回数据也是通过意图
				Intent intent = new	Intent();
				intent.putExtra("name", objects[position]);
				//当前activity销毁时，会把数据传递给启动当前activity的那个activity的onActivityResult方法
				//同时，也会把启动本activity的activity传递过来的请求码，也返回给onActivityResult方法
				setResult(10, intent);
				
				//销毁当前activity
				finish();
			}
		});
	}
}
