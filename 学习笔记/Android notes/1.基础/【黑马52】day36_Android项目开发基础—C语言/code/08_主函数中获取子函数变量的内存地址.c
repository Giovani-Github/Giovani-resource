#include <stdio.h>
#include <stdlib.h>
void function(int** p){ //����һ��ָ��������ڴ��ַ 
     int i = 3;
     printf("i�ĵ�ַΪ%#x\n", &i);
     //��p�е�ֵ�����ֵ��mainpָ������ĵ�ַ�������в��� 
     *p = &i;
}
main(){
       int* mainp;
       
       //��ָ������ĵ�ַ���ݹ�ȥ 
       function(&mainp);
       printf("�������л�ȡi�ĵ�ַΪ%#x\n", mainp);
       
       //���ݻ�Ӱ 
       printf("�������л�ȡi��ֵΪ%d\n", *mainp);
       system("pause"); 
}
