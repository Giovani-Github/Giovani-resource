/*
* @Author: Marte
* @Date:   2017-08-13 13:12:14
* @Last Modified by:   Marte
* @Last Modified time: 2017-08-13 13:40:50
*/

#include <stdio.h>

void hannuota(int n, char a, char b, char c)
{
    if(1 == n)
    {
        printf("%d: %c -> %c\n", n, a, c);
    }
    else
    {
        hannuota(n-1, a, c, b);
        printf("%d: %c -> %c\n", n, a, c);
        hannuota(n-1, b, a, c);
    }
}

int main() {
    int n;
    printf("请输入要移动盘子的个数: ");
    scanf("%d", &n);

    hannuota(n, 'A', 'B', 'C');
    return 0;
}