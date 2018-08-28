#include <jni.h>
#include <stdio.h>
#include <stdlib.h>

int getPressure(){
	//��������ѹ��������õ�ѹ��ֵ
	return rand() % 101;
}

int monitor;
JNIEXPORT void JNICALL Java_com_itheima_monitor_MainActivity_startMonitor
  (JNIEnv * env, jobject obj){
	monitor = 1;
	while(monitor){
		//��ȡ��¯ѹ��
		int pressure = getPressure();

		//jclass      (*FindClass)(JNIEnv*, const char*);
		jclass clazz = (*env)->FindClass(env, "com/itheima/monitor/MainActivity");
		//jmethodID   (*GetMethodID)(JNIEnv*, jclass, const char*, const char*);
		jmethodID methodId = (*env)->GetMethodID(env, clazz, "show", "(I)V");
		//void        (*CallVoidMethod)(JNIEnv*, jobject, jmethodID, ...);
		(*env)->CallVoidMethod(env, obj, methodId, pressure);
		sleep(1);
	}

}


JNIEXPORT void JNICALL Java_com_itheima_monitor_MainActivity_stopMonitor
 (JNIEnv * env, jobject obj){
	monitor = 0;
}
