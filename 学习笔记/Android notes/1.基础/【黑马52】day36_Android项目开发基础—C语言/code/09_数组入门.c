#include <stdio.h>
#include <stdlib.h>

main(){
       char arr[] = "hello";
       int arri[] = {1,2,3,4}; 
       
       //&arr[0]����ӡ�����һ��Ԫ�صĵ�ַ 
       printf("%#x\n", &arr[0]);
       printf("%#x\n", &arr[1]);
       printf("%#x\n", &arr[2]);
       printf("%#x\n", &arr[3]);
       //��Ϊ������û�е�һֵ�ģ����Բ���&����ӡ��Ҳ�����ĵ�ַ 
       //����ĵ�ַ�����ǵ�һ��Ԫ�صĵ�ַ
       //����Ԫ�صĵ�ַ��������  
       printf("%#x\n", arr);
       //��Ϊ����ռ4���ֽڣ����Ե�ַ�Ǽ��4��
       printf("%#x\n", &arri[0]); 
       printf("%#x\n", &arri[1]); 
       printf("%#x\n", &arri[2]); 
       printf("%#x\n", &arri[3]); 
       
       //ȡ������Ԫ��
       printf("%#x\n", arr[0]);
       //��Ϊ������ڴ��ַ�������ģ������ڴ��ַ������Ӽ����������Ĳ��� 
       //Ҳ��������������ĵ�ַ�����ǵ�һ��Ԫ�صĵ�ַ 
       //+1,��ʾ����ƫ��һ����λ(char���͵��ֽڳ��ȣ�һ���ֽڣ�����ƫ��1) 
       char* p = &arr;
       printf("%c\n",*(p+0));
       printf("%c\n",*(p+1)); 
       printf("%c\n",*(p+2));
       printf("%c\n",*(p+3));
       printf("%c\n",*(p+4));   
       
       //+1��ʾƫ��һ����λ��int���͵��ֽڳ���, 4���ֽڣ�����ƫ��4�� 
       int* pp = &arri;
       printf("%d\n", *(pp+0));
       printf("%d\n", *(pp+1)); 
       printf("%d\n", *(pp+2));
       printf("%d\n", *(pp+3));
       system("pause"); 
}
