#include <jni.h>
#include <android/log.h>
#define LOG_TAG "System.out"
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG, LOG_TAG, __VA_ARGS__)
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__)


void Java_com_itheima_fork_MainActivity_callC(JNIEnv * env, jobject obj){

	//分支出c进程
	int pid = fork();
	//如果为0，分支成功
	if(pid == 0){
		while(1){
				LOGI("hello xiaozhi");
				sleep(1);
		}
	}

}
