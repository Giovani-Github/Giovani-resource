package com.example.alertdialog2;

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
    	Builder builder = new Builder(this);
    	
    	//可以跟确定取消对话框一样，设置标题，正文，图片
    	builder.setTitle("选择性别");
    	
    	//单选的选项, 局部变量要在匿名内部类中使用，需要final修饰
    	final String[] items = {"男", "女"};
    	
    	/*
    	 *	设置单选
    	 * 参数：
    	 * 	> 单选的选项
    	 * 	> 默认的选项： 传递单选选项的下标，-1表示没有默认选项
    	 * 	> 选择之后触发的事件
    	 */
    	builder.setSingleChoiceItems(items, -1, new OnClickListener() {
    		/*
    		 * dialog：触发本事件的对话框
    		 * which：用户所选条目的下标
    		 */
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(MainActivity.this, items[which], 0).show();
				
				//选择完之后，关闭对话框
				dialog.dismiss();
			}
		});
    	
    	AlertDialog ad = builder.create();
    	ad.show();
    	
    	//也可直接show
    	//builder.show();
    	
    }
}
