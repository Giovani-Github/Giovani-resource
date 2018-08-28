/*
* @Author: Marte
* @Date:   2017-07-30 14:27:52
* @Last Modified by:   Marte
* @Last Modified time: 2017-07-31 13:34:56
*/

#include <stdio.h>
# include <malloc.h>  //包含了malloc函数
# include <stdlib.h>  //包含了exit函数

struct Arr
{
    int * pBase; // 存储数组第一个元素的地址
    int cnt; // 数组当前有效个数
    int length; // 数组长度
};

void initArr(struct Arr * pArr, int len);
bool isEmpty(struct Arr * pArr);
bool isFull(struct Arr * pArr);
void showArr(struct Arr * pArr);
bool appendArr(struct Arr * pArr, int val);
bool insertArr(struct Arr * pArr, int pos, int val); // pos从1开始
bool deleteArr(struct Arr * pArr, int pos, int * pVal);
int get(struct Arr * pArr, int pos);
void sortArrr(struct Arr * pArr);
void inversionArr(struct Arr * pArr);

int main() {

    struct Arr arr;
    initArr(&arr, 6);
    showArr(&arr);
    appendArr(&arr, 1);
    appendArr(&arr, 3);
    appendArr(&arr, 4);
    appendArr(&arr, 5);
    appendArr(&arr, 0);
    appendArr(&arr, 5);
    appendArr(&arr, 5);
    appendArr(&arr, 5);
    showArr(&arr);

    insertArr(&arr, 1, 8);
    showArr(&arr);

    int val;
    deleteArr(&arr, 2, &val);
    showArr(&arr);

    printf("%d\n",  get(&arr, 4));

    sortArrr(&arr);
    showArr(&arr);

    inversionArr(&arr);
    showArr(&arr);
    return 0;
}

void initArr(struct Arr * pArr, int len) {
    pArr->pBase = (int *)malloc(sizeof(int) * len);
    if(NULL == pArr->pBase) {
        printf("%s\n", "dong tai nei cun fen pei shi bai");
        exit(-1);
    } else {
        pArr->length = len;
        pArr->cnt = 0;
    }

    return;
}

bool isEmpty(struct Arr * pArr) {
    if (pArr->cnt == 0) {
        return true;
    } else {
        return false;
    }
}

bool isFull(struct Arr * pArr) {
    if (pArr->cnt == pArr->length) {
        return true;
    } else {
        return false;
    }
}

void showArr(struct Arr * pArr) {
    if( isEmpty(pArr) ) {
        printf("%s\n", "arr is null");
    } else {
        for (int i = 0; i < pArr->cnt; ++i)
        {
            printf("%d", pArr->pBase[i]);
        }
        printf("\n");
    }
}

bool appendArr(struct Arr * pArr, int val) {
    if ( isFull(pArr) ) {
        return false;
    }

    pArr->pBase[pArr->cnt] = val;
    pArr->cnt ++;

    return true;
}

bool insertArr(struct Arr * pArr, int pos, int val) {
    if ( isFull(pArr) ) {
        return false;
    }

    if (pos < 1 || pos > pArr->cnt+1) {
        return false;
    }

    for (int i = pArr->cnt-1; i >= pos-1; --i) {
        pArr->pBase[i+1] = pArr->pBase[i];
    }

    pArr->pBase[pos-1] = val;
    pArr->cnt++;
    return true;
}

bool deleteArr(struct Arr * pArr, int pos, int * pVal) {
    if ( isEmpty(pArr) ) {
        return false;
    }

    if ( pos < 1 || pos > pArr->cnt) {
        return false;
    }

    *pVal = pArr->pBase[pos-1];
    for (int i = pos-1; i < pArr->cnt-1; ++i)
    {
        pArr->pBase[i] = pArr->pBase[i+1];
    }
/*
    for (i=pos; i<pArr->cnt; ++i)
    {
        pArr->pBase[i-1] = pArr->pBase[i];
    }
    另一种遍历方式
 */
    pArr->cnt--;
    return true;
}

int get(struct Arr * pArr, int pos) {
    if ( isEmpty(pArr) ) {
        return NULL;
    }

    if (pos < 1 || pos > pArr->cnt) {
        return NULL;
    }

    return pArr->pBase[pos-1];
}


void sortArrr(struct Arr * pArr ) {
    for (int i = 0; i <= pArr->cnt-1; ++i) {
        for (int j = i+1; j <= pArr->cnt-1; ++j) {
            if (pArr->pBase[i] > pArr->pBase[j]) {
                pArr->pBase[i] = pArr->pBase[i] + pArr->pBase[j] ;
                pArr->pBase[j] = pArr->pBase[i] - pArr->pBase[j];
                pArr->pBase[i] = pArr->pBase[i] - pArr->pBase[j];
            }
        }
    }

    return;
}

void inversionArr(struct Arr * pArr) {
    int i = 0;
    int j = pArr->cnt-1;
    int t;

    while (i < j) {
        t = pArr->pBase[i];
        pArr->pBase[i] = pArr->pBase[j];
        pArr->pBase[j] = t;
        i++;
        j--;
    }

    return;
}