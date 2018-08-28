package com.example.dialer;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	
	//Activity������ʱ����
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ΪActivity���ò��ֽ���
        setContentView(R.layout.activity_main);
        
        /*
         * 1.�õ�����ť
         * 	> ͨ����Դid��ȡ��ǰ�����ڲ����ļ��У�Ϊ��ť����������Դid
         * 	> �õ�һ��View���󡣲����ļ��е����нڵ�(���)����Android API�ж��ж�Ӧ����
         * 		���Ҷ��̳���View��
         */
        Button bt_call = (Button) findViewById(R.id.bt_call);
        
        /*
         * 2. Ϊ����ť��ӵ���¼�
         * 	> ��Ҫ����View.OnClickListener����������View���ڲ��ӿ�
         *  > ������д����onClick�ӿ�
         */
        
        bt_call.setOnClickListener(new MyClickListener());
        
    }
    
    //����һ���Լ��ĵ���¼������࣬�̳�OnClickListener
    private class MyClickListener implements OnClickListener {
    	//��ť�����ʱ����
    	public void onClick(View v) {
    		//3. �õ������
    		EditText et_phone = (EditText) findViewById(R.id.et_phone);
    		
    		/*
    		 * 4.�õ�����������
    		 * 	> �õ�����һ��Editable���������������ݵĶ���
    		 * 	> ʹ��toString()�����ɵõ���������ݶ�����ַ���
    		 */
    		Editable ea = et_phone.getText();
    		String callPhone = ea.toString();
    		
    		//5. ������Ҫ����ϵͳ�����ǵĶ�������Ҫ��绰
    		//������ͼ����
    		Intent intent = new Intent();
    		//�����ǵĶ�����װ����ͼ������
    		intent.setAction(Intent.ACTION_CALL);
    		//������ͼ��������Ҫ���˭���������ôд������ὲΪʲô��ôд
    		intent.setData(Uri.parse("tel:" + callPhone));
    		
    		//�����Ǵ�绰�Ķ���������ϵͳ��
    		//����һ����绰Ӧ�õ�Activity�����Activity����ͼ��Ҫ��绰
    		startActivity(intent);
    		
    	}
    } 
}
