package com.example.myfragment;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //让activity创建的时候，就显示一个默认的fragment
    	Fragment01 fg1 = new Fragment01();
    	FragmentManager fm = getFragmentManager();
    	FragmentTransaction ft = fm.beginTransaction();
    	ft.replace(R.id.fl, fg1);
    	ft.commit();
    }
    
    public void fragment1(View v) {
    	//把fragment01的界面显示至帧布局中
    	//创建fragment01对象
    	Fragment01 fg1 = new Fragment01();
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
}
