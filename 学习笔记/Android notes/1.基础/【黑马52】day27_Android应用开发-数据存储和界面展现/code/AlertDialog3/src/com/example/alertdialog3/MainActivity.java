package com.example.alertdialog3;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    
    public void click(View v) {
    	Builder builder = new Builder(this);
    	
    	//设置标题
    	builder.setTitle("多选");
    	
    	//多选的条目
    	final String[] items = {"1", "2", "3", "4"};
    	
    	//记录条目是否被选中，对应多选条目
    	final boolean[] checkedItems = {true, true, false, false};
    	//这里的侦听器是：android.content.DialogInterface.OnMultiChoiceClickListener;
    	builder.setMultiChoiceItems(items, checkedItems, new OnMultiChoiceClickListener() {
    		/*
    		 * which:被选择的条目的下标
    		 * isChecked：该条目是被选择，还是被取消
    		 */
			public void onClick(DialogInterface dialog, int which, boolean isChecked) {
				//改变条目为选中，或取消
				checkedItems[which] = isChecked;
			}
		});
    	
    	//设置确定按钮
    	builder.setPositiveButton("确定", new OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				StringBuffer text = new StringBuffer();
				for(int i = 0; i < items.length; i++) {
					//如果该选项是被选中的，那么就加到字符串中，显示出来。否则不加
					text.append(checkedItems[i] ? items[i] + " " : "");
				}
				
				Toast.makeText(MainActivity.this, text.toString(), 0).show();
			}
		});
    	
    	builder.show();
    }
}
