#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <string.h>

	//一个父进程退出，而它的一个或多个子进程还在运行，
	//那么那些子进程将成为孤儿进程。孤儿进程将被init进程(进程号为1)所收养，
	//并由init进程对它们完成状态收集工作。孤儿进程是没有父进程的进程，
   //孤儿进程这个重任就落到了init进程身上，init进程就好像是一个民政局，
	//专门负责处理孤儿进程的善后工作。
int main(int argc, const char* argv[])
{
    pid_t pid;

    pid = fork();
    if(pid == -1)
    {
        perror("fork error");
        exit(1);
    }

    if(pid > 0)
    {
        printf("i am parent process, pid = %d, ppid = %d\n", getpid(), getppid());
    }
    else if(pid == 0)
    {
        printf("i am child process, pid = %d, ppid = %d\n", getpid(), getppid());
        sleep(100);
    }

    return 0;
}

