package com.example.getsdsurplus;

import java.io.File;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.text.format.Formatter;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //�õ�sd��·��
        File path = Environment.getExternalStorageDirectory();
        //ʹ��sd��·���õ�StatFs����
        //StatFs�����Ի�ȡ���ļ�ϵͳ�ռ��������Ϣ
        StatFs stat = new StatFs(path.getPath());

        /*
         * 4.3֮ǰ��ȡ�����С���������顢�������飺
         * 	stat.getBlockSize();
         * 	stat.getBlockCount();
         * 	stat.getAvailableBlocks();
         * 
         * 4.3��ʼ��
         * 	stat.getBlockSizeLong();
         * 	stat.getBlockCountLong();
         * 	stat.getAvailableBlocksLong();
         * */
        long blockSize;//�����С
        long totalBlocks;//��������
        long availableBlocks;//��������
        
        /*
         * Build.VERSION.SDK_INT��ǰϵͳ�汾�ĵȼ� ��18����4.3
         * Build.VERSION_CODES.JELLY_BEAN_MR2�������18��4.3�汾
         */
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
        	blockSize = stat.getBlockSizeLong();
        	totalBlocks = stat.getBlockCountLong();
        	availableBlocks = stat.getAvailableBlocksLong();
        } else {
        	blockSize = stat.getBlockSize();
        	totalBlocks = stat.getBlockCount();
        	availableBlocks = stat.getAvailableBlocks();
        }
        
        //����ռ� * �������� = ʣ��ռ䣬 Ȼ����ת��һ�µ�λ
        String available = formatSize(availableBlocks * blockSize);
        
        //ʣ��ռ䣬��TextView����ʾ
        TextView tv = (TextView) findViewById(R.id.tv1);
        tv.setText(available);
        
    }
    
    //��λת���������ֽڵ�λ�Ĵ�С���Զ����ֽڵ�λת��ΪM | G | T 
    private String formatSize(long size) {
    	return Formatter.formatFileSize(this, size);
    }
}
