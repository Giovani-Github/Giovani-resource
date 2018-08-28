package com.example.voicechat;

import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.voicechat.bean.TalkBean;
import com.example.voicechat.bean.VoiceBean;
import com.example.voicechat.bean.VoiceBean.WSBean;
import com.google.gson.Gson;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.SynthesizerListener;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;

public class MainActivity extends Activity {

	private StringBuffer mBuffer = new StringBuffer();
	private ArrayList<TalkBean> mTalkList = new ArrayList<TalkBean>(); // 会话集合
	
	private String[] mAnswers = new String[] { "这张怎么样?", "约吗?", "老地方见!","不要再要美女了", "最后一张了" };
	private int[] mAnswerPics = new int[] { R.drawable.p1, R.drawable.p2, R.drawable.p3, R.drawable.p4 };
	
	private ListView lv_list;
	private MyAdapter mAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 初始化语音识别
		SpeechUtility.createUtility(this, SpeechConstant.APPID + "=577ca79e");
		setContentView(R.layout.activity_main);
		
		lv_list = (ListView) findViewById(R.id.lv_list);
		mAdapter = new MyAdapter();
		lv_list.setAdapter(mAdapter);
	}
	
	public void startRecognize(View v) {
		// 1.创建RecognizerDialog对象
		RecognizerDialog mDialog = new RecognizerDialog(this, null);
		// 2.设置accent、language等参数
		mDialog.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
		mDialog.setParameter(SpeechConstant.ACCENT, "mandarin");
		// 若要将UI控件用于语义理解，必须添加以下参数设置，设置之后onResult回调返回将是语义理解
		// 结果
		// mDialog.setParameter("asr_sch", "1");
		// mDialog.setParameter("nlp_version", "2.0");

		// 3.设置回调接口
		mDialog.setListener(new RecognizerDialogListener() {

			@Override
			public void onResult(RecognizerResult arg0, boolean arg1) {
				String parseData = parseData(arg0.getResultString());
				mBuffer.append(parseData);
				
				if (arg1) { // 是否结束语音
					// 提问对象
					String finalResult = mBuffer.toString();
					System.out.println("最终结果：" + finalResult);
					mBuffer = new StringBuffer(); // 相当于清空
					TalkBean askBean = new TalkBean(finalResult, true, -1);
					mTalkList.add(askBean);
					
					// 回答对象
					Random random = new Random();
					String answer = "没听清";
					int imageId = -1;
					if (finalResult.contains("你好")) {
						answer = "你好呀!";
					} else if (finalResult.contains("你是谁")) {
						answer = "我是你的小助手!";
					} else if (finalResult.contains("天王盖地虎")) {
						answer = "小鸡炖蘑菇";
						imageId = R.drawable.m;
					} else if (finalResult.contains("图片")) {
						// 取随机内容和随机图片
						int strId = random.nextInt(mAnswers.length);
						answer = mAnswers[strId];
						imageId = mAnswerPics[random.nextInt(mAnswerPics.length)];
					}
					
					TalkBean answerBean = new TalkBean(answer, false, imageId);
					mTalkList.add(answerBean);
					
					mAdapter.notifyDataSetChanged();
					lv_list.setSelection(mTalkList.size() - 1);
					
					startSpeak(answer);
				}
			}

			@Override
			public void onError(SpeechError arg0) {

			}
		});
		// 4.显示dialog，接收语音输入
		mDialog.show();
	}
	
	/**
	 * 读出回答
	 * @param answer
	 * @author kingLi 2016-7-6 下午4:21:52
	 */
	protected void startSpeak(String answer) {
		// 1.创建SpeechSynthesizer对象, 第二个参数：本地合成时传InitListener
		SpeechSynthesizer mTts = SpeechSynthesizer
				.createSynthesizer(this, null);
		// 2.合成参数设置，详见《MSC Reference Manual》SpeechSynthesizer 类
		// 设置发音人（更多在线发音人，用户可参见 附录13.2
		mTts.setParameter(SpeechConstant.VOICE_NAME, "xiaoqian"); // 设置发音人
		mTts.setParameter(SpeechConstant.SPEED, "50");// 设置语速
		mTts.setParameter(SpeechConstant.VOLUME, "80");// 设置音量，范围0~100
		mTts.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD); // 设置云端
		// 设置合成音频保存位置（可自定义保存位置），保存在“./sdcard/iflytek.pcm”
		// 保存在SD卡需要在AndroidManifest.xml添加写SD卡权限
		// 仅支持保存为pcm和wav格式，如果不需要保存合成音频，注释该行代码
		mTts.setParameter(SpeechConstant.TTS_AUDIO_PATH, "./sdcard/iflytek.pcm");
		// 3.开始合成
		mTts.startSpeaking(answer, mSynListener);
	}

	// 合成监听器
	private SynthesizerListener mSynListener = new SynthesizerListener() {
		// 会话结束回调接口，没有错误时，error为null
		public void onCompleted(SpeechError error) {}

		// 缓冲进度回调
		// percent为缓冲进度0~100，beginPos为缓冲音频在文本中开始位置，endPos表示缓冲音频在文本中结束位置，info为附加信息。
		public void onBufferProgress(int percent, int beginPos, int endPos,String info) {}

		// 开始播放
		public void onSpeakBegin() {}

		// 暂停播放
		public void onSpeakPaused() {}

		// 播放进度回调
		// percent为播放进度0~100,beginPos为播放音频在文本中开始位置，endPos表示播放音频在文本中结束位置.
		public void onSpeakProgress(int percent, int beginPos, int endPos) {}

		// 恢复播放回调接口
		public void onSpeakResumed() {}

		// 会话事件回调接口
		public void onEvent(int arg0, int arg1, int arg2, Bundle arg3) {}
	};
	
	class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return mTalkList.size();
		}

		@Override
		public TalkBean getItem(int position) {
			return mTalkList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			if (convertView == null) {
				convertView = View.inflate(MainActivity.this, R.layout.list_item, null);
				
				holder = new ViewHolder();
				holder.tv_ask = (TextView) convertView.findViewById(R.id.tv_ask);
				holder.ll_answer = (LinearLayout) convertView.findViewById(R.id.ll_answer);
				holder.tv_answer = (TextView) convertView.findViewById(R.id.tv_answer);
				holder.iv_pic = (ImageView) convertView.findViewById(R.id.iv_pic);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			
			TalkBean talkBean = getItem(position);
			if (talkBean.isAsk) {
				holder.tv_ask.setText(talkBean.text);
				holder.tv_ask.setVisibility(View.VISIBLE);
				holder.ll_answer.setVisibility(View.GONE);
			} else {
				holder.tv_answer.setText(talkBean.text);
				holder.tv_ask.setVisibility(View.GONE);
				holder.ll_answer.setVisibility(View.VISIBLE);
				
				if (talkBean.imageId > 0) {
					holder.iv_pic.setVisibility(View.VISIBLE);
					holder.iv_pic.setImageResource(talkBean.imageId);
				} else {
					holder.iv_pic.setVisibility(View.GONE);
				}
			}
			
			return convertView;
		}
		
	}
	
	class ViewHolder {
		TextView tv_ask;
		LinearLayout ll_answer;
		TextView tv_answer;
		ImageView iv_pic;
	}

	/**
	 * 解析json数据 
	 * @author kingLi 2016-7-6 下午3:58:47
	 */
	private String parseData(String result) {
		Gson gson = new Gson();
		VoiceBean voiceBean = gson.fromJson(result, VoiceBean.class);
		ArrayList<WSBean> ws = voiceBean.ws;
		
		StringBuffer sb = new StringBuffer();
		for (WSBean wsBean : ws) {
			String word = wsBean.cw.get(0).w;
			sb.append(word);
		}
		return sb.toString();
	}
}
