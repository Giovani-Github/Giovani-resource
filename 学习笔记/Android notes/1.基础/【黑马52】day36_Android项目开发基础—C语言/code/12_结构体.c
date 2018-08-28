#include <stdio.h>
#include <stdlib.h>
void study(){
     printf("吃饭睡觉打李志\n");
}
//定义一个结构体 
struct student{
       int age;
       int height;
       char sex;
       
       //结构体中不能定义函数，但可以定义函数指针
       void (*studyP)(); 
}

main(){
       //定义结构体的变量 
       struct student st = {20, 180, 'm', study};
       printf("%d\n", st.age);
       //为了方便位移，结构体的长度，会自动补齐。把三个变量的长度，变成每个都一样长 
       printf("结构体的长度%d\n", sizeof(st));
       
       //调用函数指针有三种写法 
       st.studyP();
       
       struct student* stp = &st;
       (*stp).studyP();
       
       stp->studyP();
       system("pause"); 
}
