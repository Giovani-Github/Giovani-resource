#include <stdio.h>
#include <stdlib.h>
void study(){
     printf("�Է�˯������־\n");
}
//����һ���ṹ�� 
struct student{
       int age;
       int height;
       char sex;
       
       //�ṹ���в��ܶ��庯���������Զ��庯��ָ��
       void (*studyP)(); 
}

main(){
       //����ṹ��ı��� 
       struct student st = {20, 180, 'm', study};
       printf("%d\n", st.age);
       //Ϊ�˷���λ�ƣ��ṹ��ĳ��ȣ����Զ����롣�����������ĳ��ȣ����ÿ����һ���� 
       printf("�ṹ��ĳ���%d\n", sizeof(st));
       
       //���ú���ָ��������д�� 
       st.studyP();
       
       struct student* stp = &st;
       (*stp).studyP();
       
       stp->studyP();
       system("pause"); 
}
