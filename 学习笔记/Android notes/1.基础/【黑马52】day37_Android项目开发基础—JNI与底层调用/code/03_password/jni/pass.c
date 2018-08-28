#include <jni.h>

JNIEXPORT jint JNICALL Java_com_example_password_MainActivity_passjia
  (JNIEnv* evn, jobject j, jint pass) {
	return pass +2;
}
