package com.example.jumpactivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    
    /**
     * 跳转至其他应用的Activity
     * 跳转至打电话Activity
     * 隐式跳转
     * @param v
     */
    public void click1(View v) {
    	Intent intent = new Intent(); // 创建意图对象
    	//隐式意图，通过指定aciton和data
    	intent.setAction(Intent.ACTION_CALL); // 指定打电话的动作
    	intent.setData(Uri.parse("tel:15819072869")); // 指定要拨打的号码
    	//跳转
    	startActivity(intent);
    }
    
    /**
     * 在本应用中跳转
     * 跳转到secondActivity
     * 显式跳转
     * @param v
     */
    public void click2(View v) {
    	Intent intent = new Intent(); // 创建意图对象
    	//显式意图，cls：直接指定目标Activity的类名
    	intent.setClass(this, SecondActivity.class);
    	//跳转
    	startActivity(intent);
    }
    
    /**
     * 跳转至其他应用的Activity
     * 跳转至打电话Activity
     * 显式跳转
     * @param v
     */
    public void click3(View v) {
    	Intent intent = new Intent();
    	//显式意图，指定目标Activity的包名和类名
    	intent.setClassName("com.android.browser", "com.android.browser.BrowserActivity");
    	startActivity(intent);
    }
    
    /**
     * 隐式跳转activity
     * @param v
     */
    public void click4(View v) {
    	Intent intent = new Intent();
    	//匹配第一个意图过滤器
/*
   		intent.setAction("com.example.jc"); //action必须与清单文件中配置的一致
   		// 如果清单文件中配置的category是default，那么可以不写这行代码 
   		// 因为系统会自动添加一个android.intent.category.DEFAULT
    	intent.addCategory(Intent.CATEGORY_DEFAULT); 
*/    	
    	//匹配第二个意图过滤器
/*    	
 		intent.setAction("com.example.jc2");
//    	intent.setData(Uri.parse("jc:123")); // 会自动清空type的数据
//    	intent.setType("text/username"); // 会自动清空data的数据
    	
    	intent.setDataAndType(Uri.parse("jc:123"), "text/username"); // 同时设置data和type
*/    	
    	// 匹配第三个意图过滤器
    	intent.setAction("com.example.jc3");
    	intent.setData(Uri.parse("jc2:456"));
    	startActivity(intent);
    }
    
    /**
     * 隐式跳转至浏览器
     * @param v
     */
    public void click5(View v) {
    	Intent intent = new Intent();
    	intent.setAction(Intent.ACTION_VIEW);
    	intent.setData(Uri.parse("http://www.baidu.com"));
    	startActivity(intent);
    }
}
