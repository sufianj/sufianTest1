
#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>


int spawn (char* program, char** arg_list)
{
  pid_t child_pid;

  /* Duplicate this process.  */
  child_pid = fork ();
  if (child_pid != 0)
    /* This is the parent process.  */
    return child_pid;
  else {
    /* Now execute PROGRAM, searching for it in the path.  */
    execvp (program, arg_list);
    /* The execvp function returns only if an error occurs.  */
    fprintf (stderr, "an error occurred in execvp\n");
    abort ();
  }
}

int main (int argc, char **argv)
{
  /* The argument list to pass to the "ls" command.  */
  char* arg_list[] = {
    "ls",     /* argv[0], the name of the program.  */
    "-l", 
    "/",
    NULL      /* The argument list must end with a NULL.  */
  };

  /* Spawn a child process running the "ls" command.  Ignore the
     returned child process id.  */
/*	char ch[10];
	int i,j;
	if (0 == strcmp("ls", argv[1]))
		spawn("ls", arg_list);
	else
	{
		for (i = 1; i != 3; ++i)
		{
			if ( 0 == strcmp(argv[1], arg_list[i]))
			{
				strcpy(ch, "ls ");
				for(j = 0; arglist[i][j] != '\0'; j++)
					ch[j + 3] = arglist[i][j];
			}
		}
		spawn(ch, arg_list):
	}*/
  spawn ("ls", arg_list); 

  printf ("done with main program\n");

  return 0;
}
