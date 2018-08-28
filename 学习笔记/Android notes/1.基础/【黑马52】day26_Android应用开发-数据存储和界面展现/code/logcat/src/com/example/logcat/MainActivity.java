package com.example.logcat;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        System.out.println(0);//输出的是普通等级的日志
        
        Log.v("tag", "1");//最低等级的日志
        Log.d("tag", "2");//调试日志
        Log.i("tag", "3");//普通日志
        Log.w("tag", "4");//警告日志
        Log.e("tag", "5");//错误日志
    }    
}
