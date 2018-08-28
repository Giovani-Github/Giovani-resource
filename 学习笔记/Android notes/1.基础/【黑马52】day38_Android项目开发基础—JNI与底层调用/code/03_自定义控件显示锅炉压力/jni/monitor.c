#include <jni.h>
#include <stdio.h>
#include <stdlib.h>

int getPressure(){
	//……监测电压……运算得到压力值。0-100的随机数
	return rand() % 101;
}

int monitor;
JNIEXPORT void JNICALL Java_com_example_monitor_MainActivity_startMonitor
  (JNIEnv * env, jobject obj){
	//c中没有boolean，所以1就是true，0就是false
	monitor = 1;

	//每秒循环一次
	while(monitor){
		//获取锅炉压力
		int pressure = getPressure();

		//调用java的show方法
		//jclass      (*FindClass)(JNIEnv*, const char*);
		jclass clazz = (*env)->FindClass(env, "com/example/monitor/MainActivity");
		//jmethodID   (*GetMethodID)(JNIEnv*, jclass, const char*, const char*);
		jmethodID methodId = (*env)->GetMethodID(env, clazz, "show", "(I)V");
		//void        (*CallVoidMethod)(JNIEnv*, jobject, jmethodID, ...);
		(*env)->CallVoidMethod(env, obj, methodId, pressure);

		//睡眠一秒
		sleep(1);
	}

}


JNIEXPORT void JNICALL Java_com_example_monitor_MainActivity_stopMonitor
 (JNIEnv * env, jobject obj){
	//停止循环监测
	monitor = 0;
}
