
/*
* shm-client - client program to demonstrate shared memory.
*/
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/shm.h>
#include <stdio.h>
#include <stdlib.h>


#define SHMSZ 27

main()
{
	int shmid;
	key_t key;
//	char *shm, *s;
//	int i;
	int i, *shm;
	/*
	* We need to get the segment named
	* "5678", created by the server.
	*/
	key = 5678;

	/*
	* Locate the segment.
		*/
	if ((shmid = shmget(key, SHMSZ, 0666)) < 0)
	{
		perror("shmget");
		exit(1);
	}
	printf("Shared Mem Creation Successful with shmid = %d\n", shmid);
	/*
	* Now we attach the segment to our data space.
	*/
/*	if ((shm = shmat(shmid, NULL, 0)) == (char *) -1) 
	{
		perror("shmat");
		exit(1);
	}*/
	if ((shm = shmat(shmid, NULL, 0)) == (int *) -1) 
	{
		perror("shmat");
		exit(1);
	}
	printf("Shmat Successful, shm = %x\n",shm);
	/*
	* Now read what the server put in the memory.
	*/
	/* for (s = shm; *s != NULL; s++)
	putchar(*s);
	putchar('\n');
	*/
/*	for(i = 1; i <26; i++)
		putchar(*(shm +i));
*/
	for (i = 0; *(shm + i) != 0; i++)
		printf("%3d", *(shm+i));
	/*
	* Finally, change the first character of the
	* segment to '*', indicating we have read
	* the segment.
	*/
//	*shm = '*';

	exit(0);
}
