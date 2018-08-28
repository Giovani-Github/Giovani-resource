package com.example.xutilsdownload;

import java.io.File;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

public class MainActivity extends Activity {

    private TextView tv_error;
	private ProgressBar pb;
	private TextView tv_wj;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        tv_error = (TextView) findViewById(R.id.tv_error);
        pb = (ProgressBar) findViewById(R.id.pb);
        tv_wj = (TextView) findViewById(R.id.tv_wj);
       
    }
    
    public void click(View v) {
    	HttpUtils utils = new HttpUtils();
    	String path = "http://192.168.1.110:8080/Android/QQPlayer.exe";
    	utils.download(
    			path, // 下载地址 
    			"sdcard/QQPlayer.exe", // 文件保存路径 
    			true, // 是否支持断点续传
    			true, // 是否从响应头中获取文件名，下载成功后以这个文件名重命名文件
		    	new RequestCallBack<File>() {
					
    				//下载成功后调用
					@Override
					public void onSuccess(ResponseInfo<File> responseInfo) {
						//弹出保存路径
						Toast.makeText(MainActivity.this, responseInfo.result.getPath(), 0).show();					
					}
					
					/*
					 * 下载失败后调用
					 * error:异常
					 * msg：异常信息
					 */
					@Override
					public void onFailure(HttpException error, String msg) {
						tv_error.setText(msg);
					}
					
					/*
					 * 传递进度信息到这里
					 * total : 文件总大小
					 * current: 当前下载的大小
					 */
					@Override
					public void onLoading(long total, long current,
							boolean isUploading) {
						//设置进度条
						pb.setMax((int)total);
						pb.setProgress((int)current);
						//设置文本进度
						tv_wj.setText(current * 100 / total + "%");
					}
				});
    }
}
