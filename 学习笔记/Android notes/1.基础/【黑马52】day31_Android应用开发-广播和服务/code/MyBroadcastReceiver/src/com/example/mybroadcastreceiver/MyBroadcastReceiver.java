package com.example.mybroadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyBroadcastReceiver extends BroadcastReceiver {

	//接收到广播时调用
	@Override
	public void onReceive(Context context, Intent intent) {
		System.out.println("打电话了!!!");
	}

}
