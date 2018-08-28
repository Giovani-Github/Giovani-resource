#include <stdio.h>
#include <stdlib.h>

main(){
       char arr[] = "hello";
       int arri[] = {1,2,3,4}; 
       
       //&arr[0]，打印数组第一个元素的地址 
       printf("%#x\n", &arr[0]);
       printf("%#x\n", &arr[1]);
       printf("%#x\n", &arr[2]);
       printf("%#x\n", &arr[3]);
       //因为数组是没有单一值的，所以不加&，打印的也是他的地址 
       //数组的地址，就是第一个元素的地址
       //数组元素的地址是连续的  
       printf("%#x\n", arr);
       //因为整型占4个字节，所以地址是间隔4的
       printf("%#x\n", &arri[0]); 
       printf("%#x\n", &arri[1]); 
       printf("%#x\n", &arri[2]); 
       printf("%#x\n", &arri[3]); 
       
       //取出数组元素
       printf("%#x\n", arr[0]);
       //因为数组的内存地址是连续的，所以内存地址可以相加减。不连续的不行 
       //也可以这样，数组的地址，就是第一个元素的地址 
       //+1,表示向右偏移一个单位(char类型的字节长度，一个字节，就是偏移1) 
       char* p = &arr;
       printf("%c\n",*(p+0));
       printf("%c\n",*(p+1)); 
       printf("%c\n",*(p+2));
       printf("%c\n",*(p+3));
       printf("%c\n",*(p+4));   
       
       //+1表示偏移一个单位（int类型的字节长度, 4个字节，就是偏移4） 
       int* pp = &arri;
       printf("%d\n", *(pp+0));
       printf("%d\n", *(pp+1)); 
       printf("%d\n", *(pp+2));
       printf("%d\n", *(pp+3));
       system("pause"); 
}
