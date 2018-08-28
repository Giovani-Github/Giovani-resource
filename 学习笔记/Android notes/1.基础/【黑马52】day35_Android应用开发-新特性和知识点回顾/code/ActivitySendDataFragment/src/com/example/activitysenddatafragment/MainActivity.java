package com.example.activitysenddatafragment;


import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

    private Fragment01 fg1;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //让activity创建的时候，就显示一个默认的fragment
    	fg1 = new Fragment01();
    	FragmentManager fm = getFragmentManager();
    	FragmentTransaction ft = fm.beginTransaction();
    	ft.replace(R.id.fl, fg1);
    	ft.commit();
    }
    
    public void fragment1(View v) {
    	fg1 = new Fragment01();
    	//获取fragment管理器
    	FragmentManager fm = getFragmentManager();
    	//打开事务
    	FragmentTransaction ft = fm.beginTransaction();
    	//把内容显示在帧布局上
    	ft.replace(R.id.fl, fg1);
    	//提交
    	ft.commit();
    }
	
    public void fragment2(View v) {
    	//把fragment02的界面显示至帧布局中
    	//创建fragment02对象
    	Fragment02 fg2 = new Fragment02();
    	//获取fragment管理器
    	FragmentManager fm = getFragmentManager();
    	//打开事务
    	FragmentTransaction ft = fm.beginTransaction();
    	//把内容显示在帧布局上
    	ft.replace(R.id.fl, fg2);
    	//提交
    	ft.commit();
	}
	
	public void fragment3(View v) {
    	//把fragment03的界面显示至帧布局中
    	//创建fragment03对象
    	Fragment03 fg3 = new Fragment03();
    	//获取fragment管理器
    	FragmentManager fm = getFragmentManager();
    	//打开事务
    	FragmentTransaction ft = fm.beginTransaction();
    	//把内容显示在帧布局上
    	ft.replace(R.id.fl, fg3);
    	//提交
    	ft.commit();
	}
	
	//把数据发送到Fragment01
	public void send(View v) {
		EditText et_main = (EditText) findViewById(R.id.et_main);
		String text = et_main.getText().toString();
		//得到Fragment01对象，调用settext方法，把数据传递过去
		fg1.settText(text);
	}
}
