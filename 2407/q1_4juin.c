#include <stdio.h>
#include <fcntl.h>

main() {

FILE *fp;
char  buffer[128];
int r;

fp = open(“/etc/hosts”, O_RDONLY);
if (fp == NULL) {
	perror(“	***open”);
	exit(1);
}
while ((r = read(fp, buffer, 128)) > 0) {
	printf(“s\n”, buffer);
}
}
