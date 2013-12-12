#include <pthread.h>
#include <stdio.h>
#include <fcntl.h>
#include <stdlib.h>

void doit()
{
int i;

printf("child thread getting inside doit...my ID = %d\n", pthread_self());

for (i = 0; i < 2000000000; i++)
if (i == 50)
printf("child thread found 50!\n");

printf("child thread exiting from doit...\n");
}

main()
{
int j;
pthread_t tid;

for (j = 0; j < 10; j++)
pthread_create(&tid, NULL, doit, NULL);

pthread_create(&tid, NULL, doit, NULL);
pthread_join(tid, NULL);

printf("main thread exiting ... \n");
}

