#include <stdio.h>
#include <stdlib.h>

main(){
       int i = 3;
       //定义一个保存int类型数据的地址的指针变量 ，该变量的值是一个内存地址 
       int* p = &i;
       //二级指针变量，存放p指针变量的内存地址 
       int** q = &p;
      
       printf("i的地址%#x\n", p);
       
       //拿到指针变量所表示的地址的内存空间中的值 
       printf("i的值为%d\n", *p);
       printf("i的值为%d\n", **q);
       system("pause"); 
}
