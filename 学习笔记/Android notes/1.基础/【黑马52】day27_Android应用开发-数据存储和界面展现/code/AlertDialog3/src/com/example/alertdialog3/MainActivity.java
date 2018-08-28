package com.example.alertdialog3;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    
    public void click(View v) {
    	Builder builder = new Builder(this);
    	
    	//���ñ���
    	builder.setTitle("��ѡ");
    	
    	//��ѡ����Ŀ
    	final String[] items = {"1", "2", "3", "4"};
    	
    	//��¼��Ŀ�Ƿ�ѡ�У���Ӧ��ѡ��Ŀ
    	final boolean[] checkedItems = {true, true, false, false};
    	//������������ǣ�android.content.DialogInterface.OnMultiChoiceClickListener;
    	builder.setMultiChoiceItems(items, checkedItems, new OnMultiChoiceClickListener() {
    		/*
    		 * which:��ѡ�����Ŀ���±�
    		 * isChecked������Ŀ�Ǳ�ѡ�񣬻��Ǳ�ȡ��
    		 */
			public void onClick(DialogInterface dialog, int which, boolean isChecked) {
				//�ı���ĿΪѡ�У���ȡ��
				checkedItems[which] = isChecked;
			}
		});
    	
    	//����ȷ����ť
    	builder.setPositiveButton("ȷ��", new OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				StringBuffer text = new StringBuffer();
				for(int i = 0; i < items.length; i++) {
					//�����ѡ���Ǳ�ѡ�еģ���ô�ͼӵ��ַ����У���ʾ���������򲻼�
					text.append(checkedItems[i] ? items[i] + " " : "");
				}
				
				Toast.makeText(MainActivity.this, text.toString(), 0).show();
			}
		});
    	
    	builder.show();
    }
}
