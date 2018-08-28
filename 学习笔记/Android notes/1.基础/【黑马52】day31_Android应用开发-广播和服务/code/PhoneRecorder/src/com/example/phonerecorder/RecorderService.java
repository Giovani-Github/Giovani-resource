package com.example.phonerecorder;

import java.io.IOException;

import android.app.Service;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

public class RecorderService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	//服务创建时调用
	@Override
	public void onCreate() {
		super.onCreate();
		
		/*
		 * 使用ContextWrapper#getSystemService(String name)，获取系统服务。
		 * 传入TELEPHONY_SERVICE，获取电话服务
		 * 得到电话管理器
		 */
		TelephonyManager tm  = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
		
		/*
		 * 通过电话管理器，监听电话状态
		 * 传入监听器
		 * 		PhoneStateListener是一个类，可直接new。不过没意义
		 * 		我们要重写我们需要的方法，才有意义
		 * 		当事件发生时，就会调用相应的方法
		 * 
		 * 传入要监听的事件
		 * 		如果没有这个，那么监听器监听的就是全部电话的事件
		 * 		电话的每个事件，都会触发PhoneStateListener中的方法，这是没必要的
		 * 		我们只要监听一个事件，这一个事件发生时，调用PhoneStateListener相应的方法即可
		 * 		就是电话状态改变事件，所以传入PhoneStateListener.LISTEN_CALL_STATE，监听电话状态即可
		 * 
		 * 电话状态：空闲、响铃、摘机
		 * 
		 */
		tm.listen(new MyListener(), PhoneStateListener.LISTEN_CALL_STATE);
	}
	
	class MyListener extends PhoneStateListener {
		
		private MediaRecorder recorder;

		//一旦电话状态改变，此方法调用
		@Override
		public void onCallStateChanged(int state, String incomingNumber) {
			super.onCallStateChanged(state, incomingNumber);
			
			switch (state) {
			case TelephonyManager.CALL_STATE_IDLE:
				System.out.println("空闲");
				
				if(recorder != null) {
					//停止录音
					recorder.stop();
					//关闭资源，因为录音机底层调用的是c，所以有些资源要关闭
					recorder.release();
					recorder = null;
				}
				
				break;
			case TelephonyManager.CALL_STATE_RINGING:
				System.out.println("响铃");
				
				//一旦响铃就初始化录音机
				if(recorder == null) {
					recorder = new MediaRecorder();	
					//设置音频来源，麦克风.只能录到我们的声音。电话那头的声音是录不到的，涉及到法律问题
					recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
					//设置音频格式为3gp
					recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
					//设置音频文件保存位置
					recorder.setOutputFile("sdcard/luyin.3gp");
					//设置音频编码。每一种音频格式都有几种不同的编码
					recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
					
					//准备录音
					try {
						recorder.prepare();
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
				break;
			case TelephonyManager.CALL_STATE_OFFHOOK:
				System.out.println("摘机");
				
				if(recorder != null) {
					recorder.start();//开始录音
				}
				break;
			}
		}
	}

}
