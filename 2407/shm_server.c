#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/shm.h>
#include <stdio.h>
#include <stdlib.h>

#define SHMSZ 27
main()
{
	char c;
	int shmid;
	key_t key;

//	char *shm = NULL, *s = NULL;
	int i, j, *p, *shm;
	/*
	* We'll name our shared memory segment
	* "5678".
	*/
	key = 5678;

	/*
	* Create the segment.
	*/
	if ((shmid = shmget(key, SHMSZ, IPC_CREAT | 0666)) < 0)
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
	* Now put some things into the memory for the
	* other process to read.
	*/
//	s = shm;
//	for(c = 'a'; c <= 'z'; ++c)
//		*s++ = c;
//	*s = NULL;

	p = shm;
	*p++ = 2;
	*p++ = 3;
	*p++ = 5;
	*p++ = 7;
	for (i = 11; i < 100; i += 2)
		if (i % 3 && i % 5  && i % 7 )	
			*p++ = i;	
	*p = 0;

	return 0;
}
