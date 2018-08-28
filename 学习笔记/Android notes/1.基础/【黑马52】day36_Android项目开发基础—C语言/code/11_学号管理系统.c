#include <stdio.h>
#include <stdlib.h>
#include <malloc.h>

main() {
       printf("请输入学生人数：");
       int count;
       
       //把输入的人数，保存到这个内存地址中 
       scanf("%d", &count);
       
       //申请保存学号的堆内存空间，学号是个整型，有多好个学号，就多少个整型
       //会把申请到的内存空间的地址，赋值给指针变量p 
       int* p = malloc(sizeof(int) * count); 
       
       //有多少个学生，就循环多少次 
       int i;
       for(i=0; i<count; i++) {
                printf("请输入第%d个学生的学号：", i);
                //把输入的学号，保存到这个内存地址所指向的内存空间中去 
                scanf("%d", p+i);
       } 
       
       //新增学生人数
       printf("请输入新郑的学生人数：");
       int newCount;
       scanf("%d", &newCount);
       //在p内存地址后面，继续申请堆内存
       p = realloc(p, sizeof(int)*newCount); 
       
       for(i=count; i<newCount+count; i++) {
                printf("请输入第%d个学生的学号：", i);
                scanf("%d", p+i);
       } 

       for(i=0; i<count+newCount; i++) {
                //把这个内存地址中的值拿出来 
             printf("第%d个学生的学号是：%d\n", i, *(p+i));
       }
       system("pause");
        
}
