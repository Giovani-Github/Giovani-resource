#include <stdio.h>

enum WeekDay
{
//�����ֵ�������ӣ�11,12,13..
//���û��д10��Ĭ�ϴ�0��ʼ 
Monday,Tuesday,Wednesday,Thursday,Friday,Saturday,Sunday
};

int main(void)
{

//ֵֻ���Ƕ���õ�ֵ������һ�� 
  enum WeekDay day = Sunday;
  printf("%d\n",day);
  system("pause");
  return 0;
}
