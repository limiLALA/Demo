#include<iostream>
#include<stdio.h>
using namespace std;

int Ackmann(int m,int n)
{
	if(m==0)return n+1;
	else if(m>0 && n==0)return Ackmann(m-1,1);
	else return Ackmann(m-1,Ackmann(m,n-1));
}

int main()
{
	int m,n;
	printf("输入m和n：");
	scanf("%d %d",&m,&n);
	printf("结果是：%d",Ackmann(m,n));
//	system("pause");
	return 0;
}
