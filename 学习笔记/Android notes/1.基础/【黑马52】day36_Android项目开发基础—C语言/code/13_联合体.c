#include <stdio.h>
#include <stdlib.h>

main(){
       //����һ�������� 
       union{long long i; short s; char c} un;
       un.i = 3;
       printf("%d\n", un.i);
       //������ĳ��ȣ��ɳ�������Ǹ��������;��� 
       printf("������ĳ���%d\n", sizeof(un));
       system("pause"); 
}
