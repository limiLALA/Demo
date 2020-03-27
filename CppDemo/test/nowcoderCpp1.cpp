#include<iostream>

using namespace std;

struct A{
	void f(){
		
	}
}aaa;

int main(){
	int a, b;
	a=1;
	b=(a=2*a,++a,3*a);
	cout<<b;
	return 0;
} 
