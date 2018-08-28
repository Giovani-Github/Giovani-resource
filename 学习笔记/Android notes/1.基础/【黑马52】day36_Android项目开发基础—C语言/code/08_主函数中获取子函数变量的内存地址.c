#include <stdio.h>
#include <stdlib.h>
void function(int** p){ //接收一个指针变量的内存地址 
     int i = 3;
     printf("i的地址为%#x\n", &i);
     //对p中的值（这个值是mainp指针变量的地址），进行操作 
     *p = &i;
}
main(){
       int* mainp;
       
       //把指针变量的地址传递过去 
       function(&mainp);
       printf("主函数中获取i的地址为%#x\n", mainp);
       
       //数据幻影 
       printf("主函数中获取i的值为%d\n", *mainp);
       system("pause"); 
}
