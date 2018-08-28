package com.example.camera;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    
    //拍照
    public void image(View w) {
    	//启动系统提供的拍照activity
    	Intent intent = new Intent();
    	//设置action
    	intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
    	//设置携带的信息
    	//name: 信息的名称，MediaStore.EXTRA_OUTPUT，用于指示内容解析器，Uri用于存储拍摄的图像或视频
    	//value：信息的值，保存的位置，自动加file前缀
    	intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory() + "hh.jpg")));
    	//告诉系统，这个activity销毁时会返回数据
    	startActivityForResult(intent, 10);
    }
    
    //摄像
    public void video(View w) {
    	//启动系统提供的摄像activity
    	Intent intent = new Intent();
    	intent.setAction(MediaStore.ACTION_VIDEO_CAPTURE);
    	//设置携带的信息
    	//name: 信息的名称，MediaStore.EXTRA_OUTPUT，用于指示内容解析器，Uri用于存储拍摄的图像或视频
    	//value：信息的值，保存的位置，自动加file前缀
    	intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory() + "hh.3gp")));
    	//MediaStore.EXTRA_VIDEO_QUALITY，视屏的质量。0低，1高
    	intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
    	startActivityForResult(intent, 20);
    }
    
    //接收activity销毁时返回的数据
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	super.onActivityResult(requestCode, resultCode, data);
    	
    	if(requestCode == 10) {
    		Toast.makeText(this, "拍照成功", Toast.LENGTH_SHORT).show();
    	} else  {
    		Toast.makeText(this, "摄像成功", Toast.LENGTH_SHORT).show();
    	}
    	
    }
    

}
