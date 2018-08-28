#include <stdio.h>
#include <stdlib.h>

main() {
       printf("请输入班级人数:\n");
       int number;
       char arr[20];
       /*
         %d:接收整型
         &: 取出number的内存地址
         把接收到的整型，放到这个内存地址中去
         相当于，把用户输入的值，赋给number 
       */ 
       scanf("%d", &number); 
       printf("班级名名称:");
       scanf("%s", &arr);
       printf("班级人数为:%d\n班级名称:%s\n", number, arr);
       system("pause");
}
