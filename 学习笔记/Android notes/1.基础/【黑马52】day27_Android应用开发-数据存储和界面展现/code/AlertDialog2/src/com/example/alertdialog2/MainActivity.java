package com.example.alertdialog2;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    
    public void click(View v) {
    	Builder builder = new Builder(this);
    	
    	//���Ը�ȷ��ȡ���Ի���һ�������ñ��⣬���ģ�ͼƬ
    	builder.setTitle("ѡ���Ա�");
    	
    	//��ѡ��ѡ��, �ֲ�����Ҫ�������ڲ�����ʹ�ã���Ҫfinal����
    	final String[] items = {"��", "Ů"};
    	
    	/*
    	 *	���õ�ѡ
    	 * ������
    	 * 	> ��ѡ��ѡ��
    	 * 	> Ĭ�ϵ�ѡ� ���ݵ�ѡѡ����±꣬-1��ʾû��Ĭ��ѡ��
    	 * 	> ѡ��֮�󴥷����¼�
    	 */
    	builder.setSingleChoiceItems(items, -1, new OnClickListener() {
    		/*
    		 * dialog���������¼��ĶԻ���
    		 * which���û���ѡ��Ŀ���±�
    		 */
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(MainActivity.this, items[which], 0).show();
				
				//ѡ����֮�󣬹رնԻ���
				dialog.dismiss();
			}
		});
    	
    	AlertDialog ad = builder.create();
    	ad.show();
    	
    	//Ҳ��ֱ��show
    	//builder.show();
    	
    }
}
