#include <stdio.h>
#include <stdlib.h>
#include <malloc.h>

main() {
       printf("������ѧ��������");
       int count;
       
       //����������������浽����ڴ��ַ�� 
       scanf("%d", &count);
       
       //���뱣��ѧ�ŵĶ��ڴ�ռ䣬ѧ���Ǹ����ͣ��ж�ø�ѧ�ţ��Ͷ��ٸ�����
       //������뵽���ڴ�ռ�ĵ�ַ����ֵ��ָ�����p 
       int* p = malloc(sizeof(int) * count); 
       
       //�ж��ٸ�ѧ������ѭ�����ٴ� 
       int i;
       for(i=0; i<count; i++) {
                printf("�������%d��ѧ����ѧ�ţ�", i);
                //�������ѧ�ţ����浽����ڴ��ַ��ָ����ڴ�ռ���ȥ 
                scanf("%d", p+i);
       } 
       
       //����ѧ������
       printf("��������֣��ѧ��������");
       int newCount;
       scanf("%d", &newCount);
       //��p�ڴ��ַ���棬����������ڴ�
       p = realloc(p, sizeof(int)*newCount); 
       
       for(i=count; i<newCount+count; i++) {
                printf("�������%d��ѧ����ѧ�ţ�", i);
                scanf("%d", p+i);
       } 

       for(i=0; i<count+newCount; i++) {
                //������ڴ��ַ�е�ֵ�ó��� 
             printf("��%d��ѧ����ѧ���ǣ�%d\n", i, *(p+i));
       }
       system("pause");
        
}
