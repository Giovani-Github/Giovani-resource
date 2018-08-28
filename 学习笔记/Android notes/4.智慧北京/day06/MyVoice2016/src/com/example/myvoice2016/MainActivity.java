package com.example.myvoice2016;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechRecognizer;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.SynthesizerListener;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		SpeechUtility.createUtility(this, SpeechConstant.APPID + "=577ca79e");
	}

	public void startVoiceUI(View v) {
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
				System.out.println(arg0.getResultString());
			}

			@Override
			public void onError(SpeechError arg0) {

			}
		});
		// 4.显示dialog，接收语音输入
		mDialog.show();
	}

	public void startVoiceHeCheng(View v) {
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
		mTts.startSpeaking("哎呀妈呀，放开我，我要吃了它", mSynListener);

	}

	// 合成监听器
	private SynthesizerListener mSynListener = new SynthesizerListener() {
		// 会话结束回调接口，没有错误时，error为null
		public void onCompleted(SpeechError error) {
		}

		// 缓冲进度回调
		// percent为缓冲进度0~100，beginPos为缓冲音频在文本中开始位置，endPos表示缓冲音频在文本中结束位置，info为附加信息。
		public void onBufferProgress(int percent, int beginPos, int endPos,
				String info) {
		}

		// 开始播放
		public void onSpeakBegin() {
		}

		// 暂停播放
		public void onSpeakPaused() {
		}

		// 播放进度回调
		// percent为播放进度0~100,beginPos为播放音频在文本中开始位置，endPos表示播放音频在文本中结束位置.
		public void onSpeakProgress(int percent, int beginPos, int endPos) {
		}

		// 恢复播放回调接口
		public void onSpeakResumed() {
		}

		// 会话事件回调接口
		public void onEvent(int arg0, int arg1, int arg2, Bundle arg3) {
		}
	};

	public void startVoice(View v) {
		// 1.创建SpeechRecognizer对象，第二个参数：本地识别时传InitListener
		SpeechRecognizer mIat = SpeechRecognizer.createRecognizer(this, null);
		// 2.设置听写参数，详见《MSC Reference Manual》SpeechConstant类
		mIat.setParameter(SpeechConstant.DOMAIN, "iat");
		mIat.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
		mIat.setParameter(SpeechConstant.ACCENT, "mandarin ");
		// 3.开始听写
		mIat.startListening(mRecoListener);
	}

	// 听写监听器
	private RecognizerListener mRecoListener = new RecognizerListener() {
		// 听写结果回调接口(返回Json格式结果，用户可参见附录13.1)；
		// 一般情况下会通过onResults接口多次返回结果，完整的识别内容是多次结果的累加
		// 关于解析Json的代码可参见Demo中JsonParser类；
		// isLast等于true时会话结束。
		public void onResult(RecognizerResult results, boolean isLast) {
			Log.d("VOICE", "result:" + results.getResultString());
		}

		// 会话发生错误回调接口
		public void onError(SpeechError error) {
			// 打印错误码描述
			Log.d("VOICE", "error:" + error.getPlainDescription(true));
		}

		// 开始录音
		public void onBeginOfSpeech() {
		}

		// volume音量值0~30，data音频数据
		public void onVolumeChanged(int volume, byte[] data) {
		}

		// 结束录音
		public void onEndOfSpeech() {
		}

		// 扩展用接口
		public void onEvent(int eventType, int arg1, int arg2, Bundle obj) {
		}
	};

}
