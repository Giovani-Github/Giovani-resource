#include <stdio.h>
#include <stdlib.h>

main(){
       int i = 3;
       //����һ������int�������ݵĵ�ַ��ָ����� ���ñ�����ֵ��һ���ڴ��ַ 
       int* p = &i;
       //����ָ����������pָ��������ڴ��ַ 
       int** q = &p;
      
       printf("i�ĵ�ַ%#x\n", p);
       
       //�õ�ָ���������ʾ�ĵ�ַ���ڴ�ռ��е�ֵ 
       printf("i��ֵΪ%d\n", *p);
       printf("i��ֵΪ%d\n", **q);
       system("pause"); 
}
