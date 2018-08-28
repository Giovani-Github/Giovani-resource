package com.example.appstatusmonitoring;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

public class AppStatusBroadcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		Uri uri = intent.getData();
		if(action.equals(Intent.ACTION_PACKAGE_ADDED)) {
			Toast.makeText(context, uri.toString() + "安装了", 0).show();
		} else if(action.equals(Intent.ACTION_PACKAGE_REPLACED)){
			Toast.makeText(context, uri.toString() + "更新了", 0).show();
		} else {
			Toast.makeText(context, uri.toString() + "卸载了", 0).show();
		}
	}

}
