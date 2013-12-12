#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>

int account = 0;
int done = 1;
pthread_mutex_t mutex1 = PTHREAD_MUTEX_INITIALIZER;

void readAccount(void);
void updateAccount(int *);

void updateAccount(int *u)
{
int i;
printf("Thread1 started processing ... mytid = %u\n", pthread_self());
/*THIS IS MY CRITICAL SECTION*/
pthread_mutex_lock(&mutex1);
done = 0;
account += *u;
for (i = 0; i < 100000000; i++);
done = 1;
pthread_mutex_unlock(&mutex1);
/*END OF MY CRITICAL SECTION*/
printf("Thread1 finished processing ... mytid = %u\n", pthread_self());
}

void readAccount(void)
{
printf("Thread2 started processing ... mytid = %u\n", pthread_self());
/*THIS IS MY CRITICAL SECTION*/
pthread_mutex_lock(&mutex1);
if (0 == done)
{
printf("fatal error ! risk of data corruption ... \n");
return; 
}
else
printf("data integrity ok, account = %d\n", account);
pthread_mutex_unlock(&mutex1);
/*END OF MY CRITICAL SECTION*/
printf("Thread2 finished processing ... mytid = %u\n", pthread_self());
}

main()
{
pthread_t thread1, thread2, thread3, thread4;
int a = 99;
int b, c;
if (pthread_create( &thread1, NULL, updateAccount, (void*) &a) < 0)
{
perror(" ****thread1 creation fails");
exit(1);
}
b = -13;
if (pthread_create( &thread3, NULL, updateAccount, (void*) &b) < 0)
{
perror(" ****thread3 creation fails");
exit(1);
}
c = 70;
if (pthread_create( &thread4, NULL, updateAccount, (void*) &c) < 0)
{
perror(" ****thread4 creation fails");
exit(1);
}
if (pthread_create( &thread2, NULL, readAccount, NULL) < 0)
{
perror(" ****thread2 creation fails");
exit(1);
}

printf("Thread1 creation OK ... tid = %u\n", thread1);
printf("Thread2 creation OK ... tid = %u\n", thread2);
printf("Thread3 creation OK ... tid = %u\n", thread3);
printf("Thread4 creation OK ... tid = %u\n", thread4);

pthread_join(thread1, NULL);
printf("parent thread has been notified thread1 exited...\n");
pthread_join(thread2, NULL);
printf("parent thread has been notified thread2 exited...\n");
pthread_join(thread3, NULL);
printf("parent thread has been notified thread3 exited...\n");
pthread_join(thread4, NULL);
printf("parent thread has been notified thread4 exited...\n");

printf("main thread exited...\n");
}
