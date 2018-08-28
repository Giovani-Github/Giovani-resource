package com.example.sdcardmonitoring;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class SdBroadcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		
		if(action.equals(Intent.ACTION_MEDIA_MOUNTED)) {
			System.out.println("sd卡keyong");
		} else if(action.equals(Intent.ACTION_MEDIA_REMOVED)) {
			System.out.println("sd卡卸载");
		} else {
			System.out.println("sd未挂载");
		}
	}

}
