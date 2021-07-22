//匿名函数lambda
//[capture](parameters)->return-type{body}
// [](int x, int y) -> int { int z = x + y; return z; }
// [](int x, int y) { return x + y; } // 隐式返回类型
// [](int& x) { ++x; }   // 没有return语句 -> lambda 函数的返回类型是'void'
// []() { ++global_x; }  // 没有参数,仅访问某个全局变量
// []{ ++global_x; }     // 与上一个相同,省略了()
#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

int main(){
	vector<int> v;
	for(int i = 0; i < 10; i++){
		v.push(rand() % 100);
	}
	sort(v.begin(), v.end(),[](int a, int b)->bool{
		return a >= b;
	})
	return 0;
} 
