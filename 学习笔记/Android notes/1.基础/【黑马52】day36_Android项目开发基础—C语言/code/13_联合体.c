#include <stdio.h>
#include <stdlib.h>

main(){
       //定义一个联合体 
       union{long long i; short s; char c} un;
       un.i = 3;
       printf("%d\n", un.i);
       //联合体的长度，由长度最长的那个变量类型决定 
       printf("联合体的长度%d\n", sizeof(un));
       system("pause"); 
}
