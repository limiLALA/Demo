#include<iostream>
#include<map> 
#include<vector>

using namespace std;

int main(){
//	short int a = 0x8000;
	short int n = 2;
	unsigned char a = 0x3F;
	unsigned char b = (a<<(8-n) | a>>n);
	printf("%02X", b);
	return 0;
}
