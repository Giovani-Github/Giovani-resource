package com.example.btonclick;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        /*
         * 第一种
         *	自定义一个类，继承onclickListener接口。并实现onclick方法
         *	或者直接写匿名内部类
         */
        Button bt1 = (Button) findViewById(R.id.bt1);
        bt1.setOnClickListener(new OnClickListener() {
        	//按钮被点击时调用
        	@Override
			public void onClick(View v) {
        		System.out.println("bt1");
			}
        });
        
        
        /*
         * 第二种
         * 	自身Activity继承onclickListener接口，并实现onclick方法
         */
        Button bt2 = (Button)findViewById(R.id.bt2);
        bt2.setOnClickListener(this);
        
    }
    
    //第二个按钮被点击时调用
    public void onClick(View v) {
    	System.out.println("bt2");
    }
    
    /*
     * 第三种
     * 	在布局界面中为按钮组件添加android:onclick属性
     * 	并且在Activity中，必须有与属性值相同名称的方法
     * 	且方法格式必须是：public void xx(View v) {}	
     */
    /*
     * bt3按钮被点击时会调用本方法
     * View:系统会把触发这个方法的哪个组件的对象作为View对象传过来
     */
    public void disan(View v) {
    	//通过对View对象的判断，就可以知道用户点击的到底是哪个按钮
    	//拿到按钮的资源id
    	int id = v.getId();
    	switch(id) {
    	case R.id.bt3:
    		System.out.println("bt3");
    		break;
    	case R.id.bt4:
    		System.out.println("bt4");
    		break;
    	case R.id.bt5:
    		System.out.println("bt5");
    		break;
    	}
    	
    }

}
