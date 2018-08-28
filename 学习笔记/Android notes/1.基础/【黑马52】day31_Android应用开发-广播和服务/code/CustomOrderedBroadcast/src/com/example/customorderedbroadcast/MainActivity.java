package com.example.customorderedbroadcast;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    
    public void click(View v) {
    	Intent intent = new Intent();
    	intent.setAction("zdy.ordered.broadcast");
    	//发送有序广播
    	/*	
    	 	A.	Intent：意图对象
			B.	receiverPermission：接收此广播所需要的权限
			C.	resultReceiver：最终接收者对象
			D.	scheduler：
			E.	initialCode：和请求码类似，做标记用
			F.	initialData：携带数据用，携带一些比较简单的数据。如果数据比较复杂，可使用Intent携带
			G.	initialExtras：这是一个Bundle，也是用来携带数据的，携带比较复杂的数据
    	 */
    	sendOrderedBroadcast(intent, null, new ResultReceiver(), null, 0, "自定义广播", null);
    }
    
    //最终接收者
    class ResultReceiver extends BroadcastReceiver {
		public void onReceive(Context context, Intent intent) {
			String data = getResultData();
			System.out.println(data);
		}
    	
    }
}
