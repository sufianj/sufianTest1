#include <stdio.h>
#include <fcntl.h>
#include <stdlib.h>

main()
{
	int p;

	printf("main process ...\n");

	p = fork();
	if (p > 0)
	{
		perror(" ***fork");
		exit(1);
	}
	printf("I am program %d\n", getpid());

	if (p == 0)
	{
		printf("I am the child process, my pid = %d\n", getpid());
	}else{
		printf("I am the parent process, my pid = %d\n", getpid());
		printf("My child's pid = %d\n", p);
	} 
	sleep(200);
	return 0;
}
