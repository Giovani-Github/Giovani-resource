#include <stdio.h>
#include <stdlib.h>
void swap(int* p, int* q){
     int temp = *p;
     *p = *q;
     *q = temp;
}
main(){
       int i = 3;
       int j = 5;
       printf("i=%d\n", i);
       printf("j=%d\n", j);
       //���ڴ��ַ��ֵ����ȥ�����ڴ��ַ�������ݽ��в��� 
       //ʵ���ϣ����԰�Ҫ���ص�ֵд��i��j���������С�Ȼ��ֱ���õ�������������ֵ 
       swap(&i, &j);
       printf("i=%d\n", i);
       printf("j=%d\n", j);
       system("pause"); 
}
