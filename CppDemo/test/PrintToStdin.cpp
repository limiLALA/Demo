#include<unistd.h>
#include <cstdio>
using namespace std;
int main(int argc, char const *argv[])
{
	char buffer[1024] = "asdfghjk";
	char in[1024];
	FILE *fp = stdin;
	for(int i = 0; i < 10; i++)
		fprintf(fp, "%s", buffer);
	printf("%s",gets(in));
	return 0;
}