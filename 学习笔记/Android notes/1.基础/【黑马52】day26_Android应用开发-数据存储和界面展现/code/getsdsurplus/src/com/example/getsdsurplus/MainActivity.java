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
        
        //得到sd卡路径
        File path = Environment.getExternalStorageDirectory();
        //使用sd卡路径得到StatFs对象
        //StatFs：可以获取到文件系统空间的所有信息
        StatFs stat = new StatFs(path.getPath());

        /*
         * 4.3之前获取区块大小、所有区块、可用区块：
         * 	stat.getBlockSize();
         * 	stat.getBlockCount();
         * 	stat.getAvailableBlocks();
         * 
         * 4.3开始：
         * 	stat.getBlockSizeLong();
         * 	stat.getBlockCountLong();
         * 	stat.getAvailableBlocksLong();
         * */
        long blockSize;//区块大小
        long totalBlocks;//所有区块
        long availableBlocks;//可用区块
        
        /*
         * Build.VERSION.SDK_INT当前系统版本的等级 ：18就是4.3
         * Build.VERSION_CODES.JELLY_BEAN_MR2：这就是18，4.3版本
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
        
        //区块空间 * 可用区块 = 剩余空间， 然后再转换一下单位
        String available = formatSize(availableBlocks * blockSize);
        
        //剩余空间，在TextView中显示
        TextView tv = (TextView) findViewById(R.id.tv1);
        tv.setText(available);
        
    }
    
    //单位转换，根据字节单位的大小，自动把字节单位转换为M | G | T 
    private String formatSize(long size) {
    	return Formatter.formatFileSize(this, size);
    }
}
