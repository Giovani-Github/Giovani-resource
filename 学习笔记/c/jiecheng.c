/*
* @Author: Marte
* @Date:   2017-08-13 13:58:50
* @Last Modified by:   Marte
* @Last Modified time: 2017-08-13 14:02:27
*/

#include <stdio.h>

int jiecheng(int n)
{
    if(1 == n)
    {
        return 1;
    }
    else
    {
        n = jiecheng(n-1) * n;
    }

    return n;
}


int main()
{
    printf("%d\n", jiecheng(2));
    return 0;
}