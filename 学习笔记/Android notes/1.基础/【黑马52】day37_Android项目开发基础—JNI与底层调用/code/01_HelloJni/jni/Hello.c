#include <stdio.h>
#include <stdlib.h>
#include <jni.h>

/*
 * 要实现的方法返回什么类型，就是什么类型
 * 	> 这里，要是实现的方法是String类型
 * 	> 在jni.h中明确定义了，c语言中，使用jstring来表示java中的String
 *
 * 要实现的方法的方法名：Java_包名（点要变成_）_类名_方法名
 *
 * 参数：这里的参数不是传过来的参数，不过还是需要有两个参数
 * 	> env: 运行环境的指针（这是个一级指针），就是java虚拟机所在的内存地址。把这个一级指针，传递个env这个二级指针（存放了运行环境的指针）
 * 		JNIEnv在jni.h中定义了typedef const struct JNINativeInterface* JNIEnv;
 * 		JNIEnv是一个结构体的指针，env是一个二级指针
 *
 * 	>obj:这个函数是哪个对象调用的，就把哪个对象传进来
 * 		java的对象用jobject表示
 */
jstring Java_com_example_hellojni_MainActivity_helloC(JNIEnv* env, jobject obj) {
	//c语言的字符串
	char* cstr = "hello from c";
	/*
	 * 把c语言的字符串，转换成java语言的字符串
	 * jstring     (*NewStringUTF)(JNIEnv*, const char*);
	 * 这是一个函数指针，在jni.h中的JNINativeInterface（运行环境）中，有大量的这种函数指针，是Google提供给开发者使用的
	 * 具体这个指针所指的函数在哪里，就不用管了。直接调用函数指针，使用函数就可以了
	 */
	//有两种写法，调用这个函数指针
	//拿到运行环境的结构体（JNINativeInterface），调用NewStringUTF函数
	//jstring jstr = (*(*env)).NewStringUTF(env, cstr);
	//jni中，最常用的写法是，拿到运行环境结构体的指针，调用NewStringUTF
	jstring jstr = (*env)->NewStringUTF(env, cstr);
	//返回一个java的字符串
	return jstr;
}
