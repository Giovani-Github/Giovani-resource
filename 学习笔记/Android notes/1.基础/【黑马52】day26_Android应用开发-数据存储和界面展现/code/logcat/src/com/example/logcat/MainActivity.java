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
        
        System.out.println(0);//���������ͨ�ȼ�����־
        
        Log.v("tag", "1");//��͵ȼ�����־
        Log.d("tag", "2");//������־
        Log.i("tag", "3");//��ͨ��־
        Log.w("tag", "4");//������־
        Log.e("tag", "5");//������־
    }    
}
