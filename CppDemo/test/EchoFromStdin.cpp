#include<unistd.h>
#include <cstdio>
using namespace std;
int main(int argc, char const *argv[])
{
	char buffer[1024];
	// FILE *fp = fdopen(1, "r");
	while(scanf("%s", buffer)!=EOF)puts(buffer);
	return 0;
}