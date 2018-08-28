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
        	"����",
        	"zl",
        	"ww"
        };
        
        ListView lv = (ListView) findViewById(R.id.lv);
        
        /*
         * ��ΪArrayAdapterֻ�ܴ���һ�����ݣ����Ը���ָ��������
         * ����:
         * 	> �����Ķ���
         * 	> Ҫ����view������ʾ����Ļ�Ĳ����ļ�
         * 	> ��������ʾ������view����Ĳ����ļ��У����ĸ������
         * 	> Ҫ��ʾ������
         */
        lv.setAdapter(new ArrayAdapter<String>(this, R.layout.item, R.id.tv, s));
    }  
}
