#include <stdio.h>

enum WeekDay
{
//后面的值依次增加，11,12,13..
//入股没有写10，默认从0开始 
Monday,Tuesday,Wednesday,Thursday,Friday,Saturday,Sunday
};

int main(void)
{

//值只能是定义好的值的其中一个 
  enum WeekDay day = Sunday;
  printf("%d\n",day);
  system("pause");
  return 0;
}
