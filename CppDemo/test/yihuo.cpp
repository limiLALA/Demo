#include <iostream>
#include <math.h>
using namespace std;

int main(){
	int a = 1, b = 0;
	int x1 = a&b;
	int y1 = a|b;
	int c = x1^y1;
	cout<<c;
	return 0;
}
