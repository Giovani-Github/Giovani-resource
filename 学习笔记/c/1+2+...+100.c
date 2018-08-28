/*
* @Author: Marte
* @Date:   2017-08-13 14:03:00
* @Last Modified by:   Marte
* @Last Modified time: 2017-08-13 14:04:45
*/

#include <stdio.h>

int num(int n)
{
    if (1 == n)
    {
        return 1;
    }
    else
    {
        n = n + num(n-1);
    }

    return n;
}

int main()
{
    printf("%d\n", num(100));
    return 0;
}