#include <jni.h>
#include <android/log.h>
#define LOG_TAG "System.out"
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG, LOG_TAG, __VA_ARGS__)
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__)


JNIEXPORT void JNICALL Java_com_example_hello_MainActivity_llo
  (JNIEnv * env, jobject obj) {
	LOGD("kd");
	LOGI("AD");
}
