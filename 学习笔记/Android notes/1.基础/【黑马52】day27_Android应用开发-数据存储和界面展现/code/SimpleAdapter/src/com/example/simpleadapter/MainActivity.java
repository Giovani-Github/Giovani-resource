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
         * Ҫ������view����Ĳ����ļ�����ʾ������
         * 	������list,list��ֻ�ܴ��Map��Map<String, Oject>
         * 	������ÿ��map����һ����ĿҪ��ʾ������
         */
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("image", R.drawable.ab); //Ҫ��ʾ��ͼƬ
        map1.put("name", "zs"); //Ҫ��ʾ������
        mapList.add(map1); //װ��list
        
        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("image", R.drawable.c); //Ҫ��ʾ��ͼƬ
        map2.put("name", "ls"); //Ҫ��ʾ������
        mapList.add(map2); //װ��list
        
        Map<String, Object> map3 = new HashMap<String, Object>();
        map3.put("image", R.drawable.d); //Ҫ��ʾ��ͼƬ
        map3.put("name", "zl"); //Ҫ��ʾ������
        mapList.add(map3); //װ��list
        
        /*
         * SimpleAdapter������ʾ��������
         * ������
         * 	> �����Ķ���
         * 	> Ҫ��ʾ������
         * 	> Ҫ����view����Ĳ����ļ�
         * 	> map��key������ͨ��key���������������ʾ������view����Ĳ����ļ��е������
         * 	> ����view����Ĳ����ļ������id����һ��������ʾ���ĸ�������ڶ���������ʾ���ĸ����
         * ����������������Ӧ������˳������
         */
        lv.setAdapter(new SimpleAdapter(this, mapList, R.layout.item,
        		new String[] {"image", "name"}, new int[] {R.id.image, R.id.tv}));
    }    
}
