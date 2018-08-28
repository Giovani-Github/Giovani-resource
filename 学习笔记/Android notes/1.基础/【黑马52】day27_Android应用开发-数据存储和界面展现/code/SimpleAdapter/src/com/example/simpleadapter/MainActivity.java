package com.example.simpleadapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    
        ListView lv = (ListView) findViewById(R.id.lv);
        
        /*
         * 要在填充成view对象的布局文件中显示的数据
         * 	必须是list,list中只能存放Map，Map<String, Oject>
         * 	集合中每个map就是一个条目要显示的数据
         */
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("image", R.drawable.ab); //要显示的图片
        map1.put("name", "zs"); //要显示的文字
        mapList.add(map1); //装进list
        
        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("image", R.drawable.c); //要显示的图片
        map2.put("name", "ls"); //要显示的文字
        mapList.add(map2); //装进list
        
        Map<String, Object> map3 = new HashMap<String, Object>();
        map3.put("image", R.drawable.d); //要显示的图片
        map3.put("name", "zl"); //要显示的文字
        mapList.add(map3); //装进list
        
        /*
         * SimpleAdapter可以显示多种数据
         * 参数：
         * 	> 上下文对象
         * 	> 要显示的数据
         * 	> 要填充成view对象的布局文件
         * 	> map的key，他会通过key，按下面个参数显示在填充成view对象的布局文件中的组件上
         * 	> 填充成view对象的布局文件的组件id，第一个数据显示在哪个组件，第二个数据显示在哪个组件
         * 后面两个参数，对应的数据顺序不能乱
         */
        lv.setAdapter(new SimpleAdapter(this, mapList, R.layout.item,
        		new String[] {"image", "name"}, new int[] {R.id.image, R.id.tv}));
    }    
}
