/*
  include:相当于java的import
  stdio.h: standard input output，标准输入输出头文件 
  stdlib.h: 标准支持类库头文件
  .h：头文件
  
  这两个头文件，是c运行需要的最基本的文件 
*/ 
#include <stdio.h>
#include <stdlib.h> 

//程序的入口函数 
main() {
       //输出语句，换行就加\n 
       printf("hello wrod\n");
       //运行一个windows指令
       //让程序暂停，否则看不到输出的语句 
       system("pause");
}
