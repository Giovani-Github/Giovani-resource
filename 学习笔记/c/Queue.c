/*
* 循环队列
* @Author: Marte
* @Date:   2017-08-12 15:24:35
* @Last Modified by:   Marte
* @Last Modified time: 2017-08-12 15:57:22
*/

#include <stdio.h>
#include <malloc.h>

// 只存储n-1个值
typedef struct Queue
{
    int * pBase; // 数组
    int front; // 对头下标
    int rear; // 对尾下标，永远指向最后一个值的下一个位置。
} QUEUE, * PQUEUE;

void init(PQUEUE);
bool full_queue(PQUEUE); // 队列是否满
bool en_queue(PQUEUE, int); // 入队
bool empty_queue(PQUEUE); // 队列是否空
bool out_queue(PQUEUE, int *); // 出队
void traverse_queue(PQUEUE); // 遍历队列

int main()
{
    QUEUE q;
    init(&q);
    // en_queue(&q, 1);
    // en_queue(&q, 2);
    // en_queue(&q, 3);
    // en_queue(&q, 4);
    // en_queue(&q, 5);
    // en_queue(&q, 6);
    // en_queue(&q, 7);

    traverse_queue(&q);

    int val;
    out_queue(&q, &val);
    printf("%d\n", val);
    traverse_queue(&q);


    return 0;
}

void init(PQUEUE pQ)
{
    pQ->pBase = (int *)malloc(sizeof(int) * 6);

    // 队头队尾都指向0下标；
    pQ->front = 0;
    pQ->rear = 0;
}

bool full_queue(PQUEUE pQ)
{
    // 只存储n-1个值
    if ( (pQ->rear + 1) % 6 == pQ->front )
    {
        return true;
    }
    else
    {
        return false;
    }

}

bool en_queue(PQUEUE pQ, int val)
{
    if (full_queue(pQ))
    {
        return false;
    }
    else
    {
        pQ->pBase[pQ->rear] = val;
        pQ->rear = (pQ->rear + 1) % 6;
        return true;
    }

}

bool empty_queue(PQUEUE pQ)
{
    if (pQ->rear == pQ->front)
    {
        return true;
    }
    else
    {
        return false;
    }

}

bool out_queue(PQUEUE pQ, int * pVal)
{
    if (empty_queue(pQ))
    {
        return false;
    }
    else
    {
        *pVal = pQ->pBase[pQ->front];
        pQ->front = (pQ->front + 1) % 6;
        return true;
    }

}

void traverse_queue(PQUEUE pQ)
{
    int i = pQ->front;

    while(i != pQ->rear)
    {
        printf("%d ", pQ->pBase[i]);
        i = (i+1) % 6;
    }
    printf("\n");

}