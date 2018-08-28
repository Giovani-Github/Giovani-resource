package com.example.arrayadapter;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        String[] s = {
        	"李四",
        	"zl",
        	"ww"
        };
        
        ListView lv = (ListView) findViewById(R.id.lv);
        
        /*
         * 因为ArrayAdapter只能处理一种数据，所以给它指定个泛型
         * 参数:
         * 	> 上下文对象
         * 	> 要填充成view对象，显示在屏幕的布局文件
         * 	> 把数据显示在填充成view对象的布局文件中，的哪个组件里
         * 	> 要显示的数据
         */
        lv.setAdapter(new ArrayAdapter<String>(this, R.layout.item, R.id.tv, s));
    }  
}
