#include <stdio.h>
#include <stdlib.h>
void swap(int* p, int* q){
     int temp = *p;
     *p = *q;
     *q = temp;
}
main(){
       int i = 3;
       int j = 5;
       printf("i=%d\n", i);
       printf("j=%d\n", j);
       //把内存地址的值传过去，对内存地址所在内容进行操作 
       //实际上，可以把要返回的值写入i，j两个变量中。然后直接拿到这两个变量的值 
       swap(&i, &j);
       printf("i=%d\n", i);
       printf("j=%d\n", j);
       system("pause"); 
}
