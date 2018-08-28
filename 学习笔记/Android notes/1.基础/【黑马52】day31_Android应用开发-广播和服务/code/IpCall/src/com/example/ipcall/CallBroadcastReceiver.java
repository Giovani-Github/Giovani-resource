package com.example.ipcall;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class CallBroadcastReceiver extends BroadcastReceiver {

	//接收到广播时调用
	@Override
	public void onReceive(Context context, Intent intent) {
		System.out.println("接收");
		
		//打电话广播会携带拨打出去的号码，获取到携带的号码
		String number = getResultData();
		
		//在从SharedPreference中取出保存的ip段号
		SharedPreferences sp = context.getSharedPreferences("ip", Context.MODE_PRIVATE);
		String ip = sp.getString("ip", "");
		number = ip + number;
		
		//在重新设置回广播中
		setResultData(number);
	}

}
