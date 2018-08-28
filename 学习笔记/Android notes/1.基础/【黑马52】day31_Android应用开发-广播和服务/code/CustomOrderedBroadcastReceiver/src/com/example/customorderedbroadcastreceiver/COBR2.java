package com.example.customorderedbroadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class COBR2 extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		//拿到自定义有序广播中的数据并修改
		String data = getResultData();
		System.out.println(data);
		data = data + "COBR2改";
		setResultData(data);
	}

}
