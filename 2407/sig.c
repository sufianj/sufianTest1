#include<string.h>
#include<time.h>
#include<sys/ipc.h>
#include<sys/msg.h>
#include<sys/wait.h>
#include<sys/errno.h>
#include <stdio.h>


#define MSGTXTLEN 128

struct msg_buf {
  long mtype;
  char mtext[MSGTXTLEN];
} msg;

main() {
	int msgqid;
	int r;
	msgqid = msgget(4000, IPC_CREAT|0666);

	if (msgqid < 0) {
		perror("		***msgget");
		exit(1);
	} else {
	printf("Successful creation of msgQueue key=4000, id=%d\n", msgqid);

	}

	msg.mtype = 1; // set the type of message
  	sprintf (msg.mtext, "%s\n", "This is my first msg from server...");



	/* Send he message to the Queue */
	r =  msgsnd(msgqid, &msg, sizeof(msg.mtext), 0);

	if (r < 0) {
		perror("	***msgsnd");
		exit(2);
	} else {
	 printf("Post a message to the Queue, sucessful operation\n");
	}


	
	msg.mtype = 2; // set the type of message
  	sprintf (msg.mtext, "%s\n", "This is my 2nd msg from server type=2...");


	/* Send he message to the Queue */
	r =  msgsnd(msgqid, &msg, sizeof(msg.mtext), 0);

	if (r < 0) {
		perror("	***msgsnd");
		exit(2);
	} else {
	 printf("Post a message to the Queue, sucessful operation\n");
	}

}
