package com.example.voicechat.bean;

import java.util.ArrayList;

/**
 * 语音对象封装
 * @author kingLi 2016-7-6 下午3:42:10
 */
public class VoiceBean {
	public ArrayList<WSBean> ws;
	
	public class WSBean {
		public ArrayList<CWBean> cw;
	}
	
	public class CWBean {
		public String w;

		@Override
		public String toString() {
			return "CWBean [w=" + w + "]";
		}
	}
}
