#include <jni.h>
#include "com_itheima_cplusplus_MainActivity.h"

JNIEXPORT jstring JNICALL Java_com_itheima_cplusplus_MainActivity_helloC
  (JNIEnv * env, jobject obj){
	char* cstr = "hello from c";
  //return (*env)->NewStringUTF(env, cstr);
	return env->NewStringUTF(cstr);
}
