#include <stdio.h>
#include <stdlib.h>
//需要导入这个头文件 
#include <malloc.h>

main(){
       int i = 3; // 他们都是在栈中的，静态
       int arr[10]; //他也是静态
       
       //申请10个堆内存空间，会把这个内存空间的内存地址给p，可以存放10个整型数据
       //即40个字节的堆内存，每个字节都有一个内存地址 
       //同一个malloc申请的堆内存地址，是连续的 
       int* p = malloc(sizeof(int) * 10);
       
       //占用了四个字节，还剩下36个字节，就还能存九个整型 
       *p = 10;
       *(p+1) = 20;
       printf("%#x\n", p);
       printf("%d\n", *p);
       printf("%d\n", *(p+1));
       
       //释放掉p所申请的堆内存空间，这样p的地址也会没有，变成野指针 
       free(p);
       system("pause"); 
} 
