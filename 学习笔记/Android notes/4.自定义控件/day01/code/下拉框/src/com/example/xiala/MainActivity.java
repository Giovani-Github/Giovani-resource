package com.example.xiala;

import java.util.ArrayList;
import org.apache.http.conn.ManagedClientConnection;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.util.Base64;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

public class MainActivity extends Activity {

	private ArrayList<String> mList = new ArrayList<String>(); 
	private ListView mLv;
	private PopupWindow mPw;
	
	private ImageView iv_drop;
	private EditText et_content;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		iv_drop = (ImageView) findViewById(R.id.iv_drop);
		et_content = (EditText) findViewById(R.id.et_content);
		
		// 模拟出数据
		for (int i = 0; i < 200; i++) {
			mList.add("abdcd" + i);
		}
		
		iv_drop.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				showDropDown();
			}
		});
		
		mLv = new ListView(this);
		mLv.setAdapter(new MyAdapter());
		
		mLv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				et_content.setText(mList.get(position));
				mPw.dismiss();				
			}
		});
	}
	
	// 显示下拉列表
	private void showDropDown() {
		if (mPw == null) {
			mPw = new PopupWindow(mLv, et_content.getWidth(), 400, true);
			mPw.setBackgroundDrawable(new ColorDrawable()); // 设置背景，才能返回消失
		}
		mPw.showAsDropDown(et_content); // 显示在et_content正下方
	}
	
	class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return mList.size();
		}

		@Override
		public Object getItem(int position) {
			return mList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			ViewHodler viewHolder;
			
			if (convertView == null) {
				convertView = View.inflate(MainActivity.this, R.layout.list_item, null);
				
				viewHolder = new ViewHodler();
				viewHolder.iv_item_delete = (ImageView) convertView.findViewById(R.id.iv_item_delete);
				viewHolder.tv_item_content = (TextView) convertView.findViewById(R.id.tv_item_content);
				convertView.setTag(viewHolder);
			} else {
				viewHolder = (ViewHodler) convertView.getTag();
			}
			
			viewHolder.tv_item_content.setText(mList.get(position));
			viewHolder.iv_item_delete.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					mList.remove(position);
					MyAdapter.this.notifyDataSetChanged();
				}
			});
			
			return convertView;
		}
		
	}
	
	class ViewHodler {
		public TextView tv_item_content;
		public ImageView iv_item_delete;
	}

}
