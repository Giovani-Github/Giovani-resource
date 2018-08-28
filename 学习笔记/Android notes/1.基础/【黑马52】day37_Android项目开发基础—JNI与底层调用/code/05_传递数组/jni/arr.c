#include <jni.h>

//java的int数组，在c中用jintArray表示，jintArray其实是jarray的别名，jni.h中有定义
JNIEXPORT void JNICALL Java_com_example_array_MainActivity_arrEncode
  (JNIEnv * env, jobject j, jintArray arr){

	//拿到数组的长度以及第0个元素的地址，就相当于把java的数组当做c数组操作
	/*
	 * 拿到数组的长度，只要是数组，都能得到。因为其他类型数组也只是jarray的别名
	 *jsize       (*GetArrayLength)(JNIEnv*, jarray);
	 *jsize是jint的别名，jint是int的别名，所以直接用int接收就行
	 */
	int length = (*env)->GetArrayLength(env, arr);

	/*
	 * 拿到数组第0个元素的地址
	 *jint*       (*GetIntArrayElements)(JNIEnv*, jintArray, jboolean*);
	 *jboolean直接传0，google也没有明确在说明文档中说明干什么用，反正就这么写
	 */
	int* arrp = (*env)->GetIntArrayElements(env, arr, 0);

	//取出每个元素，进行加密
	int i;
	for(i=0; i<length; i++) {
		*(arrp + i) += 10;
	}
}
