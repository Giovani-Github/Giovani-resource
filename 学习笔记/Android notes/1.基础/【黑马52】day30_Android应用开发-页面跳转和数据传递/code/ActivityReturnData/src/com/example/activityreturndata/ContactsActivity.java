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
			"����",
			"����",
			"����"
		};
		
		lv.setAdapter(new ArrayAdapter<String>(this, R.layout.item_else, R.id.tv, objects));
		
		//��ListView������Ŀ�ĵ������
		lv.setOnItemClickListener(new OnItemClickListener() {
			
			//position:��Ŀ���±�
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				//activity����ʱ��������Ҳ��ͨ����ͼ
				Intent intent = new	Intent();
				intent.putExtra("name", objects[position]);
				//��ǰactivity����ʱ��������ݴ��ݸ�������ǰactivity���Ǹ�activity��onActivityResult����
				//ͬʱ��Ҳ���������activity��activity���ݹ����������룬Ҳ���ظ�onActivityResult����
				setResult(10, intent);
				
				//���ٵ�ǰactivity
				finish();
			}
		});
	}
}
