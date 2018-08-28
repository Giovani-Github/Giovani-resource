/*
* @Author: Marte
* @Date:   2017-08-04 15:29:14
* @Last Modified by:   Marte
* @Last Modified time: 2017-08-06 09:14:06
*/

#include <stdio.h>
#include <malloc.h>

typedef struct Node
{
    int data; // 数据域
    struct Node * pNext; // 指针域
} NODE, *PNODE; //NODE等价于struct Node    PNODE等价于struct Node *

// 函数声明
PNODE create_list(void); // 创建链表
void traverse_list(PNODE pHead); // 遍历链表
bool is_empty(PNODE pHead); // 判断链表是否为空
int length_list(PNODE pHead); // 求链表长度
void sort_list(PNODE pHead); // 对链表进行排序
// 在pHead所指向链表的第pos个节点的前面插入一个新的结点，该节点的值是val，并且pos的值是从1开始
bool insert_list(PNODE pHead, int pos, int val);
// 删除链表第pos个节点，并将删除的结点的值存入pVal所指向的变量中, 并且pos的值是从1开始
bool delete_list(PNODE pHead, int pos, int * pVal);

int main()
{
    PNODE pHead = NULL; //等价于 struct Node * pHead = NULL;
    pHead = create_list();
    traverse_list(pHead);
    if (is_empty(pHead))
    {
        printf("%s\n", "list is empty");
    }
    else
    {
        printf("%s\n", "list is not empty");
    }

    printf("list length: %d\n", length_list(pHead));
    sort_list(pHead);
    traverse_list(pHead);

    insert_list(pHead, 2, 8);
    traverse_list(pHead);

    int val;
    if (delete_list(pHead, 5, &val) )
    {
        printf("delete success, delte in val: %d\n", val);
        traverse_list(pHead);
    }
    else
    {
        printf("%s\n", "delete defeated");
    }

    return 0;
}

PNODE create_list(void)
{
    int len; // 存放链表的结点有效个数（需要生成几个结点）
    int val; // 临时存放用户输入的结点值
    PNODE pTail; // 记录最后一个结点
    PNODE pHead = (PNODE)malloc(sizeof(NODE)); // 先生成头结点

    pTail = pHead; //初始尾结点
    pTail->pNext = NULL;

    if (NULL == pHead)
    {
        printf("%s\n", "malloc error");
        exit(-1);
    }

    printf("%s\n", "input list length");
    scanf("%d", &len);

    for (int i = 0; i < len; ++i)
    {
        printf("input %d node: \n", i+1);
        scanf("%d", &val);

        PNODE pNew = (PNODE)malloc(sizeof(NODE));
        if ( NULL == pNew )
        {
            printf("%s\n", "malloc error");
            exit(-1);
        }

        pNew->data = val;
        pTail->pNext = pNew;
        pNew->pNext = NULL;
        pTail = pNew;
    }

    return pHead;
}

void traverse_list(PNODE pHead)
{
    PNODE p = pHead->pNext;
    while (NULL != p)
    {
        printf("%d", p->data);
        p = p->pNext;
    }
    printf("\n");

    return;
}

bool is_empty(PNODE pHead)
{
    if(NULL == pHead->pNext)
    {
        return true;
    }
    else
    {
        return false;
    }
}

int length_list(PNODE pHead)
{
    int len = 0;
    PNODE p = pHead->pNext;

    while(NULL != p)
    {
        len++;
        p = p->pNext;
    }

    return len;
}

void sort_list(PNODE pHead)
{
    int len = length_list(pHead);
    PNODE p, q;
    int i, j, t;

    for (i = 0, p = pHead->pNext; i < len-1; ++i, p=p->pNext)
    {
        for (j = i+1, q=p->pNext; j < len; ++j, q=q->pNext)
        {
            if (p->data > q->data)
            {
                t = p->data;
                p->data = q->data;
                q->data = t;
            }
        }
    }

    return;
}

bool insert_list(PNODE pHead, int pos, int val)
{
    PNODE p = pHead;
    int i = 0;
    while (NULL != p && i < pos-1)
    {
        p = p->pNext;
        i++;
    }

    // 先决条件：一个链表：[head] -> [1] -> [22] -> [23] -> [34] -> [45]
    // 情况一: pHead != null, pos=2。上面while运行后。p指向[1], i=1。则下面if条件不成立。
    // 情况二：pHead = null, pos=2。上面while运行后。p指向NULL, i=0。则下面if条件成立。
    // 情况三：pHead != null, pos=7。上面while运行后, p指向NULL, i=6。则下面if条件成立。
    // 情况四：pHead !=null, pos=-1。上面while运行后，p!=null, i=0。则下面if条件成里。
    if (NULL == p || i > pos-1)
    {
        return false;
    }

    //如果程序能执行到这一行说明p已经指向了第pos-1个结点(pos前面的结点),但第pos个节点是否存在无所谓.pos从1开始
    //分配新的结点
    PNODE pNew = (PNODE)malloc(sizeof(NODE));
    if (NULL == pNew)
    {
        printf("%s\n", "malloc error");
        exit(-1);
    }

    //将新的结点存入p节点的后面
    pNew->data = val;
    PNODE q = p->pNext;
    p->pNext = pNew;
    pNew->pNext = q;

    return true;
}

bool delete_list(PNODE pHead, int pos, int * pVal)
{
    PNODE p = pHead;
    int i = 0;

    // NULL != p->pNext:判断下一个结点是否为null
    while (NULL != p->pNext && i < pos-1)
    {
        p = p->pNext;
        i++;
    }

    // 先决条件：一个链表：[head] -> [1] -> [22] -> [23] -> [34] -> [45]
    // 情况一: pHead != null, pos=2。上面while运行后。p->pNext指向[22], i=1。则下面if条件不成立。
    // 情况二：pHead = null, pos=2。上面while运行后。p->pNext指向NULL, i=0。则下面if条件成立。
    // 情况三：pHead != null, pos=7。上面while运行后, p->pNext指向NULL, i=5。则下面if条件成立。
    // 情况四：pHead != null, pos=6。上面while运行后, p->pNext指向NULL, i=5。则下面if条件成立。
    // 情况五：pHead !=null, pos=-1。上面while运行后，p!=null, i=0。则下面if条件成立。
    if (i>pos-1 || NULL == p->pNext)
    {
        return false;
    }

    // 如果程序能执行到这一行说明p已经指向了第pos-1个结点(pos前面的结点),但第pos个节点必须存在.pos从1开始

    // pVal = &(p->pNext->data); // 错误写法。如果把要删除结点的数据的地址给pval，那么当该节点被删除后释放内存后。该地址就是垃圾数据了
    *pVal = p->pNext->data; // 保存要删除结点的数据
    PNODE r = p->pNext; // r指向待删除结点
    p->pNext = p->pNext->pNext;
    free(r); // 释放被删除结点的内存

    return true;
}
