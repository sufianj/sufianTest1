#include <stdio.h>
#include <fcntl.h>
#include <stdlib.h>

main()
{
int p;
while (1)
{
p = fork();
if (p > 0)
{
perror(" ***fork");
exit(1);
}
printf(" my pid = %d\n", getpid());
}
return 0;
}
