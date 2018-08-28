#include <jni.h>
#include <string.h>

//java字符串，转换为c字符串
char*   Jstring2CStr(JNIEnv*   env,   jstring   jstr)
{
	//这个报错不用管，最后部署时删掉错误
	 char*   rtn   =   NULL;
	 jclass   clsstring   =   (*env)->FindClass(env,"java/lang/String");
	 jstring   strencode   =   (*env)->NewStringUTF(env,"GB2312");
	 jmethodID   mid   =   (*env)->GetMethodID(env,clsstring,   "getBytes",   "(Ljava/lang/String;)[B");
	 jbyteArray   barr=   (jbyteArray)(*env)->CallObjectMethod(env,jstr,mid,strencode); // String .getByte("GB2312");
	 jsize   alen   =   (*env)->GetArrayLength(env,barr);
	 jbyte*   ba   =   (*env)->GetByteArrayElements(env,barr,JNI_FALSE);
	 if(alen   >   0)
	 {
	  rtn   =   (char*)malloc(alen+1);         //"\0"
	  memcpy(rtn,ba,alen);
	  rtn[alen]=0;
	 }
	 (*env)->ReleaseByteArrayElements(env,barr,ba,0);  //
	 return rtn;
}

//加密
JNIEXPORT jstring JNICALL Java_com_example_charencoder_MainActivity_encoder
  (JNIEnv* env, jobject j, jstring text, jint length) {
	//把java的字符串，转换为c的字符串
	char* str = Jstring2CStr(env, text);

	//遍历这个字符数据，拿出每个元素，进行+1(因为是字符，所以他是把阿斯克码+1)
	int i;
	for(i=0; i<length; i++) {
		*(str + i) += 1;
	}
	//把c的字符串转换为java的字符串返回
	return (*env)->NewStringUTF(env, str);
}

//解密
JNIEXPORT jstring JNICALL Java_com_example_charencoder_MainActivity_decoder
  (JNIEnv* env, jobject j, jstring text, jint length) {
	//把java的字符串，转换为c的字符串
	char* str = Jstring2CStr(env, text);

	//遍历这个字符数据，拿出每个元素，进行+1(因为是字符，所以他是把阿斯克码-1)
	int i;
	for(i=0; i<length; i++) {
		*(str + i) -= 1;
	}
	//把c的字符串转换为java的字符串返回
	return (*env)->NewStringUTF(env, str);
}
