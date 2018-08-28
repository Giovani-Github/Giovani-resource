package com.example.activitysenddatafragment;



import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.TextView;

public class Fragment01 extends Fragment {
	
	private TextView tv_f01;

	//返回的view对象会作为fragment01的内容显示在屏幕上
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		//把要显示在fragment01上的布局文件，填充成view对象返回
		View v = inflater.inflate(R.layout.fragment01, null);
		tv_f01 = (TextView) v.findViewById(R.id.tv_f01);
		return v;
	}
	
	//修改TextView的文本内容
	public void settText(String text) {
		tv_f01.setText(text);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	
	
}
