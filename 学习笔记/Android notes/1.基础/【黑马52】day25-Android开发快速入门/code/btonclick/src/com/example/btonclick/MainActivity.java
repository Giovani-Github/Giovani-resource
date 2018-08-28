package com.example.btonclick;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        /*
         * ��һ��
         *	�Զ���һ���࣬�̳�onclickListener�ӿڡ���ʵ��onclick����
         *	����ֱ��д�����ڲ���
         */
        Button bt1 = (Button) findViewById(R.id.bt1);
        bt1.setOnClickListener(new OnClickListener() {
        	//��ť�����ʱ����
        	@Override
			public void onClick(View v) {
        		System.out.println("bt1");
			}
        });
        
        
        /*
         * �ڶ���
         * 	����Activity�̳�onclickListener�ӿڣ���ʵ��onclick����
         */
        Button bt2 = (Button)findViewById(R.id.bt2);
        bt2.setOnClickListener(this);
        
    }
    
    //�ڶ�����ť�����ʱ����
    public void onClick(View v) {
    	System.out.println("bt2");
    }
    
    /*
     * ������
     * 	�ڲ��ֽ�����Ϊ��ť������android:onclick����
     * 	������Activity�У�������������ֵ��ͬ���Ƶķ���
     * 	�ҷ�����ʽ�����ǣ�public void xx(View v) {}	
     */
    /*
     * bt3��ť�����ʱ����ñ�����
     * View:ϵͳ��Ѵ�������������ĸ�����Ķ�����ΪView���󴫹���
     */
    public void disan(View v) {
    	//ͨ����View������жϣ��Ϳ���֪���û�����ĵ������ĸ���ť
    	//�õ���ť����Դid
    	int id = v.getId();
    	switch(id) {
    	case R.id.bt3:
    		System.out.println("bt3");
    		break;
    	case R.id.bt4:
    		System.out.println("bt4");
    		break;
    	case R.id.bt5:
    		System.out.println("bt5");
    		break;
    	}
    	
    }

}
