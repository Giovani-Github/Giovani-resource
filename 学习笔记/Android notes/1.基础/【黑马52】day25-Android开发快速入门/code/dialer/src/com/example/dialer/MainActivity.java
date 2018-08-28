package com.example.dialer;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	
	//Activity被创建时调用
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //为Activity设置布局界面
        setContentView(R.layout.activity_main);
        
        /*
         * 1.得到拨打按钮
         * 	> 通过资源id获取。前提是在布局文件中，为按钮组件添加了资源id
         * 	> 得到一个View对象。布局文件中的所有节点(组件)，在Android API中都有对应的类
         * 		并且都继承至View类
         */
        Button bt_call = (Button) findViewById(R.id.bt_call);
        
        /*
         * 2. 为拨打按钮添加点击事件
         * 	> 需要接收View.OnClickListener参数，它是View的内部接口
         *  > 并且重写它的onClick接口
         */
        
        bt_call.setOnClickListener(new MyClickListener());
        
    }
    
    //定义一个自己的点击事件监听类，继承OnClickListener
    private class MyClickListener implements OnClickListener {
    	//按钮被点击时调用
    	public void onClick(View v) {
    		//3. 得到输入框
    		EditText et_phone = (EditText) findViewById(R.id.et_phone);
    		
    		/*
    		 * 4.得到输入框的文字
    		 * 	> 得到的是一个Editable，它是输入框的内容的对象
    		 * 	> 使用toString()，即可得到输入框内容对象的字符串
    		 */
    		Editable ea = et_phone.getText();
    		String callPhone = ea.toString();
    		
    		//5. 我们需要告诉系统，我们的动作：我要打电话
    		//创建意图对象
    		Intent intent = new Intent();
    		//把我们的动作封装进意图对象中
    		intent.setAction(Intent.ACTION_CALL);
    		//告诉意图对象，我们要打给谁。这个先这么写。后面会讲为什么这么写
    		intent.setData(Uri.parse("tel:" + callPhone));
    		
    		//把我们打电话的动作，告诉系统。
    		//启动一个打电话应用的Activity，这个Activity的意图是要打电话
    		startActivity(intent);
    		
    	}
    } 
}
