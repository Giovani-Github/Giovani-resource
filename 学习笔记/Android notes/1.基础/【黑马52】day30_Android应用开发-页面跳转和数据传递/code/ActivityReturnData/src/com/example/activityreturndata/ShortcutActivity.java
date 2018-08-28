package com.example.activityreturndata;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ShortcutActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_else);
		
		ListView lv = (ListView) findViewById(R.id.lv);
		final String[] objects = new String[] {
			"ÄãºÃ",
			"ÔÙ¼û",
			"¹ö"
		};
		
		lv.setAdapter(new ArrayAdapter<String>(this, R.layout.item_else, R.id.tv, objects));
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				Intent data = new Intent();
				data.putExtra("content", objects[position]);
				setResult(20, data);
				finish();
			}			
		});
	}
}
