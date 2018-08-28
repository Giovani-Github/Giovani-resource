#include <jni.h>

/*
 * 要实现的本地方法，返回的是一个int类型的数
 * 在jni.h中，规定了，java中的int，在c中用jint表示
 * 不过，其实int是可以通用的，java中的int在c中也可以用int表示，因为他们都是四个字节长度
 * 不过，还是用jint表示，比较规范
 *
 * 实现的本地方法有两个参数，加上必须的两个参数，一共四个参数
 */
jint Java_com_example_hello3_MainActivity_add(JNIEnv* env, jobject obj, jint j, jint i) {
	//因为int可以通用，所以不用转换了
	return j+i;
}
