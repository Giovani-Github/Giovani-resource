package com.example.alertdialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       
    }
    
    public void click(View v) {
    	//创建对话框构建器对象，类似工厂模式
    	//使用android.app.AlertDialog.Builder
    	Builder builder = new Builder(this);
    	
    	//设置左上角图片
    	builder.setIcon(android.R.drawable.arrow_down_float);
    	builder.setTitle("是否确定");//设置标题
    	builder.setMessage("是否删除");//设置正文
    	
    	//设置确定按钮，注意：这里的监听器是:android.content.DialogInterface.OnClickListener;
    	builder.setPositiveButton("确定", new OnClickListener(){
			public void onClick(DialogInterface dialog, int which) {
				//显示吐司对话框
				Toast.makeText(MainActivity.this, "确定", 0).show();
			}
    	});
    	
    	//设置取消按钮
    	builder.setNegativeButton("取消", new OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				//显示吐司对话框
				Toast.makeText(MainActivity.this, "取消", 0).show();
			}
		});
    	
    	//使用构建器创建出对话框对象
    	AlertDialog ad = builder.create();
    	//显示对话框
    	ad.show();

		//也可以直接这样
//		duilder.show();
    }
}
