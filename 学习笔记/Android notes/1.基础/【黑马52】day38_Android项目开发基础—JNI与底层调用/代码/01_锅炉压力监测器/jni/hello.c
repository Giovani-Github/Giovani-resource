#include <jni.h>
#include <stdio.h>
#include <stdlib.h>

int getPressure(){
	//……监测电压……运算得到压力值
	return rand() % 101;
}

int monitor;
JNIEXPORT void JNICALL Java_com_itheima_monitor_MainActivity_startMonitor
  (JNIEnv * env, jobject obj){
	monitor = 1;
	while(monitor){
		//获取锅炉压力
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
