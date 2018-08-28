package com.example.mycontentobserver;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.ContentResolver;
import android.database.ContentObservable;
import android.database.ContentObserver;
import android.view.Menu;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    
        //注册一个内容观察者，监听短信数据库内容的改变
        ContentResolver cr = getContentResolver();
        
        /*
         * 注册
         * uri:要监听哪个内容提供者的数据库改变通知
         * notifyForDescendents:
         * 	> true: 只要是前缀和content://sms匹配的uri，都接收， 例如：content://sms/inbox也能接收
         * 	> false: 必须和content://sms完全匹配的uri，才接收.
         * observer：观察者
         */
        cr.registerContentObserver(Uri.parse("content://sms"), true, new MyObserver(new Handler()));
    }
    
    class MyObserver extends ContentObserver {

		public MyObserver(Handler handler) {
			super(handler);
		}
    	
		//收到数据改变通知，此方法调用
		@Override
		public void onChange(boolean selfChange) {
			super.onChange(selfChange);
			System.out.println("短信数据库内容改变了");
		}
    }
    
}
