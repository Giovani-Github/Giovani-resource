/*
* @Author: Marte
* @Date:   2017-08-07 15:58:00
* @Last Modified by:   Marte
* @Last Modified time: 2017-08-08 09:12:08
*/

#include <stdio.h>
#include <malloc.h>
#include <stdlib.h>

typedef struct Node
{
    int data;
    struct Node * pNext;
} NODE, * PNODE;

typedef struct Stack
{
    PNODE pTop; // 指向栈顶
    PNODE pBottom; // 指向栈底
} STACK, * PSTACK;

void init(PSTACK); // 初始化栈
void push(PSTACK, int); // 压栈
void traverse(PSTACK); // 遍历栈
bool isEmpty(PSTACK);
bool pop(PSTACK, int *); // 出栈
void clear(PSTACK); // 清空栈

int main()
{
    STACK s; // 此时这个栈不算创建出来，因为栈的指针都指向垃圾数据
    init(&s);
    push(&s, 2);
    push(&s, 3);
    push(&s, -1);
    push(&s, 1);
    traverse(&s);
    int val;
    if (pop(&s, &val))
    {
        printf("pop a value: %d\n", val);
        traverse(&s);
    }
    else
    {
        printf("%s\n", "stack is null");
    }

    clear(&s);
    traverse(&s);

    return 0;
}


void init(PSTACK pS)
{
    // 创建一个没有实际意义的结点
    PNODE pNew = (PNODE)malloc(sizeof(NODE));
    if (NULL == pNew)
    {
        printf("%s\n", "malloc error");
        exit(-1);
    }

    // 让栈顶和栈底指针都指向这个没有实际意义的结点
    pS->pTop = pNew;
    pS->pBottom = pNew;
    pS->pTop->pNext = NULL; //pS->Bottom->pNext = NULL;

    return;
}

void push(PSTACK pS, int val)
{
    PNODE pNew = (PNODE)malloc(sizeof(NODE));
    if (NULL == pNew)
    {
        printf("%s\n", "malloc error");
        exit(-1);
    }

    pNew->data = val;
    pNew->pNext = pS->pTop; //pS->Top不能改成pS->Bottom
    pS->pTop = pNew;

    return;
}

bool isEmpty(PSTACK pS)
{
    if(pS->pTop == pS->pBottom)
    {
        return true;
    }
    else
    {
        return false;
    }
}

void traverse(PSTACK pS)
{

    PNODE p = pS->pTop;
    while (p != pS->pBottom)
    {
        printf("%d ", p->data);
        p = p->pNext;
    }

    printf("\n");


    return;
}


bool pop(PSTACK pS, int * pVal)
{
    if (isEmpty(pS))
    {
        return false;
    }
    else
    {
        PNODE r = pS->pTop;
        *pVal = r->data;
        pS->pTop = pS->pTop->pNext;
        free(r);
        r = NULL;

        return true;
    }
}

void clear(PSTACK pS)
{
    if (isEmpty(pS))
    {
        return;
    }
    else
    {
        PNODE p, q;
        p = pS->pTop;
        q = p->pNext;
        while (p != pS->pBottom)
        {
            free(p);
            p = q;
            q = q->pNext;
        }

        pS->pTop = pS->pBottom;
        p = NULL;
        q = NULL;

        /*
            效率更高，更省内存的写法

            PNODE p = pS->pTop;
            PNODE q = NULL;

            while (p != pS->pBottom)
            {
                q = p->pNext;
                free(p);
                p = q;
            }
            pS->pTop = pS->pBottom;

         */
    }

    return;
}



