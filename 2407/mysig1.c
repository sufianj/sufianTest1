#include<signal.h>
#include<sys/types.h>
#include<stdio.h>
#include<stdlib.h>

void myhandler(int signum)
{
	printf("catch signal id=%d\n",signum);
	printf("I will not die!!\n");
}

main()
{
	signal(SIGINT, myhandler);
	signal(SIGINT, myhandler);
	while(1);
}
