#include <stdio.h>
#include <stdlib.h>

main() {
       int i = 3;
       char c = 'a';
       long long l = 12325465;       
       
       //用站位符 %d表示要打印整型数字 
       printf("i的值为: %d\n", i);
       //sizeof(), 看某个变量或结构体的长度（字节） 
       printf("int的长度：%d\n", sizeof(int)); 
       system("pause");
}
