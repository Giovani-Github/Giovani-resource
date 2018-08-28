package com.example.smsshield;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

public class SmsBroadcastReceiver extends BroadcastReceiver {

	//接收到广播时调用
	@Override
	public void onReceive(Context context, Intent intent) {
		//收短信广播中，短信是用intent来携带的
		Bundle bundle = intent.getExtras();
		//取出多条短信，广播中有多少条，接收多少条
		//以pdus为键，取出一个object数组，数组中每一个元素，都是一条短信
		//pdu是数据单元，在android中表示短信的意思
		Object[] objects = (Object[]) bundle.get("pdus");
		
		//把所有短信取出来
		for (Object object : objects) {
			//Android中用android.telephony.SmsMessage来封装短信
			//通过pdu来构造短信，这里pdu就是object，需要把object强转为字节数组	
			SmsMessage sms = SmsMessage.createFromPdu((byte[]) object);
			
			//拿到发送者的地址，就是拿到哪个号码发过来的
			String phone = sms.getOriginatingAddress();
			if(phone.equals("138")) { // 如果是138号码发来的，就屏蔽掉
				//阻止其他广播接收者接收这条广播
				abortBroadcast();
			}
			
			System.out.println(sms.getMessageBody());
		}
	}

}
