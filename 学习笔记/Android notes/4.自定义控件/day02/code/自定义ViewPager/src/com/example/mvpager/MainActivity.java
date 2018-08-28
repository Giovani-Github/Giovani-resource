package com.example.mvpager;

import com.example.mvpager.MyViewPager.OnPageChangeListener;
import com.example.myviewpager.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MainActivity extends Activity {
	
	private int[] mImageIds = new int[] { R.drawable.a1, R.drawable.a2, R.drawable.a3, R.drawable.a4, R.drawable.a5, R.drawable.a6 };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final MyViewPager my_viewpager = (MyViewPager) findViewById(R.id.my_viewpager);
		
		// 往MyViewPager添加控件
		for (int i = 0; i < mImageIds.length; i++) {
			ImageView iv = new ImageView(this);
			iv.setBackgroundResource(mImageIds[i]);
			my_viewpager.addView(iv);
		}
		
		// 添加测试页面
		View view = View.inflate(this, R.layout.list_item, null);
		my_viewpager.addView(view, 1); // 添加到第二个位置
		
		// 初始化RadioButton
		final RadioGroup rg_group = (RadioGroup) findViewById(R.id.rg_group);
		for (int i = 0; i < mImageIds.length + 1; i++) {
			RadioButton rb = new RadioButton(this);
			rb.setId(i); // 设置了id才能单选
			if (i == 0) {
				rb.setChecked(true); // 第一个设置为选中
			}
			rg_group.addView(rb);
		}
		
		my_viewpager.setonPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				rg_group.check(position); // 设置指定RadioButton选中
			}
		});
		
		// RadioButton被选中的监听
		rg_group.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				my_viewpager.setCurrentItem(checkedId); // 跳转到被点中的相应页面
			}
		});
	}
}
