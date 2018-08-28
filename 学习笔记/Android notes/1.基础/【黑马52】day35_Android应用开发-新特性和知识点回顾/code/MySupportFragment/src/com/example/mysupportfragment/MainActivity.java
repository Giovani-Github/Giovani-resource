package com.example.mysupportfragment;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

/*
 * 因为getFragmentManager()得到的是android..app.FragmentManager;
 * 但是为了旧版本能使用，我们导入的是android.support.v4.app.FragmentManager;
 * 所以，我们要把MAinActivity改成继承android.support.v4.app.FragmentActivity;，他也是继承至activity
 * 再使用getSupportFragmentManager();，就能得到android.support.v4.app.FragmentManager;
 */
public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //让activity创建的时候，就显示一个默认的fragment
    	Fragment01 fg1 = new Fragment01();
    	FragmentManager fm = getSupportFragmentManager();
    	FragmentTransaction ft = fm.beginTransaction();
    	ft.replace(R.id.fl, fg1);
    	ft.commit();
    }
    
    public void fragment1(View v) {
    	//把fragment01的界面显示至帧布局中
    	//创建fragment01对象
    	Fragment01 fg1 = new Fragment01();
    	//获取fragment管理器
    	FragmentManager fm = getSupportFragmentManager();
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
    	FragmentManager fm = getSupportFragmentManager();
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
    	FragmentManager fm = getSupportFragmentManager();
    	//打开事务
    	FragmentTransaction ft = fm.beginTransaction();
    	//把内容显示在帧布局上
    	ft.replace(R.id.fl, fg3);
    	//提交
    	ft.commit();
	}
}
