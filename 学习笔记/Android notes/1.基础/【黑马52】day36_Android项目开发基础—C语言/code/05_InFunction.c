#include <stdio.h>
#include <stdlib.h>

main() {
       printf("������༶����:\n");
       int number;
       char arr[20];
       /*
         %d:��������
         &: ȡ��number���ڴ��ַ
         �ѽ��յ������ͣ��ŵ�����ڴ��ַ��ȥ
         �൱�ڣ����û������ֵ������number 
       */ 
       scanf("%d", &number); 
       printf("�༶������:");
       scanf("%s", &arr);
       printf("�༶����Ϊ:%d\n�༶����:%s\n", number, arr);
       system("pause");
}
