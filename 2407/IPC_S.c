#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/shm.h>
#include <sys/sem.h>
 
#define FIFO_Size 10 //not used anywhere
#define NUM_REPEAT 600  // 6000000 takes wayyyyy too long.
 
#define SHMKEY     ((key_t) 8080)   /* The shared memory ID                 */
#define SEMKEY_1   ((key_t) 8081L)  /* The semaphore ID for MUTEX           */
#define PERMS      0666             /* Access permission codes             */
 
/* Prototypes ------------------------------------------------------------- */
void insert_delay();
void insert_delay_long();
int sem_create(key_t sem_key, int init_val); /* Creating a semaphore    */
void sem_rm(int id);                         /* Remove a semaphore      */
int randomint(void);                        // return a random number from 1-200
//note that 0 values indicate empty locations.
 
/* Global Variables ------------------------------------------------------- */
static struct sembuf op_down[1] = {
    0, -1, 0
};
static struct sembuf op_up[1] = {
    0, 1, 0
};
union {
    int val;
    struct semid_ds *buf;
    ushort *array;
} semctl_arg;
 
/* The Main --------------------------------------------------------------- */
int main(void) {
    int pid; /* Process ID                          */
    int cpid; /* Child process ID                    */
    long int i; /* Loop counter                        */
    int status; /* The exit code from a child process  */
 
    int shmid; /* Shared memory ID                    */
    int mutex; /* Semaphore ID for "MUTEX" semaphore  */
    int shared_int[1];
    shared_int[1] = 0;
    int * shm_pointer = &shared_int[0]; /* Pointer to the shared memory        */
 
    /* Create a shared memory ---------------------------------------------- */
    shmid = shmget(SHMKEY, sizeof (shared_int), PERMS | IPC_CREAT);
 
    /* Attach the shared memory -------------------------------------------- */
    shm_pointer = (int *) shmat(shmid, (int *) 0, 0);
 
    /* Create a semaphore -------------------------------------------------- */
    mutex = sem_create(SEMKEY_1, 1);
 
    /* Create the writer process ------------------------------------------- */
    pid = fork();
 
    /* If pid is not zero, this must be the parent process --- */
    if (pid != 0) {
        /* The parent process creates the reader process --- */
        pid = fork();
 
        /* If pid is not zero, this must be the parent process --- */
        if (pid != 0) {
            //printf("\nThe parent process is now waiting for the two processes to be completed ... \n");
 
            cpid = wait(&status);
            //printf("\nA child process %d with process ID = %d has terminated ... \n", (status / 256), cpid);
 
            cpid = wait(&status);
            //printf("A child process %d with process ID = %d has terminated ... \n", (status / 256), cpid);
 
            //printf("\nBoth processes have completed and the parent process is terminating ...\n");
 
            /* Detaching the shared memory --------------------------------- */
            shmdt(shm_pointer);
 
            /* Deleting the shared memory ---------------------------------- */
            shmctl(shmid, IPC_RMID, (struct shmid_ds *) 0);
 
            /* Deleting the MUTEX semaphore -------------------------------- */
            sem_rm(mutex);
 
            //printf("\nThe parent process is now terminating ... \n\n");
            exit(0);
            // END OF PARENT PROCESS
 
        }/* If pid is zero, this is the writer process --- */
        else {
 
            long int prodsum = 0;
            int rand = 0;
            i = 0;
 
            /* Producer algorithm --- */
            while (i < NUM_REPEAT) {
 
                /* ENTER THE CRITICAL SECTION --- */
                status = semop(mutex, &op_down[0], 1); /* DOWN Operation --- */
                if (status < 0) {
                    printf("DOWN Operation ERROR (WRITER) .... \n");
                }
 
                /* CRITICAL SECTION --- */
 
                if(shm_pointer[1] == 0){
                   shm_pointer[0] = randomint();
                    prodsum = prodsum + shm_pointer[0];
                    shm_pointer[1] = 1;
                    i++;
                }
 
                /* LEAVE THE CRITICAL SECTION --- */
                status = semop(mutex, &op_up[0], 1); /* UP Operation --- */
                if (status < 0) {
                    printf("UP Operation ERROR (WRITER) .... \n");
                }
            }
 
            printf("PRODUCER SUM: %d.\n", prodsum);
 
            /* Terminate the producer process and send a signal to the parent process --- */
            exit(1);
        }
    }/* If pid is zero, this is the consumer process --- */
    else {
        int temp;
        long int conssum = 0;
        i = 0;
 
        /* consumer algorithm --- */
        while (i < NUM_REPEAT) {
 
            /* ENTER THE CRITICAL SECTION --- */
            status = semop(mutex, &op_down[0], 1); /* DOWN Operation --- */
            if (status < 0) {
                printf("DOWN Operation ERROR (READER) ... \n");
            }
 
            /* CRITICAL SECTION --- */
 
                if(shm_pointer[1] == 1){
                    conssum = conssum + shm_pointer[0];
                    shm_pointer[1] = 0;
                    i++;
                }
 
            /* LEAVING THE CRITICAL SECTION --- */
            status = semop(mutex, &op_up[0], 1); /* UP Operation --- */
            if (status < 0) {
                printf("UP Operation ERROR (READER) ... \n");
            }
        }
        printf("CONSUMER SUM: %d.\n", conssum);
        exit(2);
    }
}
 
/* create a semaphore ------------------------------------------------------- */
int sem_create(key_t key, int init_val) {
    register int id, semval;
    int status;
 
    if (key == IPC_PRIVATE) {
        printf("key == IP_PRIVATE ERROR ... \n");
        return (-1);
    } else if (key == (key_t) - 1) {
        printf("key == -1 ERROR ... \n");
        return (-1);
    }
 
    id = semget(key, 1, IPC_CREAT | PERMS);
    if (id < 0) {
        printf("sem_get ERROR ... \n");
        return (-1);
    }
 
    semctl_arg.val = 0;
    semval = semctl(id, 0, GETVAL, semctl_arg);
    if (semval < 0) {
        printf("semctl ERROR ... \n");
    }
 
    if (semval == 0) {
        semctl_arg.val = init_val;
        status = semctl(id, 0, SETVAL, semctl_arg);
        if (status < 0) {
            printf("Can't SETVAL ... ERROR ... \n");
        }
    }
 
    return (id);
}
 
/* insert_delay ------------------------------------------------------------- */
void insert_delay() {
    long int i, j, k;
 
    for (i = 0; i < 1000000; i++) {
        for (j = 0; j < 5; j++) {
            k = k + 1;
        }
    }
}
 
/* insert_delay_long -------------------------------------------------------- */
void insert_delay_long() {
    long int i, j, k;
 
    for (i = 0; i < 1000000; i++) {
        for (j = 0; j < 50; j++) {
            k = k + 1;
        }
    }
}
 
/* sem_rm ------------------------------------------------------------------- */
void sem_rm(int id) {
    int status;
 
    semctl_arg.val = 0;
    status = semctl(id, 0, IPC_RMID, semctl_arg);
    if (status < 0) {
        printf("ERROR in sen_rm ... \n");
    }
}
 
int randomint(void) {
    int lowerlimit = 1;
    int upperlimit = 200;
    srand(time(NULL));
    int random_number = lowerlimit + (rand() % (upperlimit - lowerlimit));
    if (random_number == 0)//make absolutely sure 0 is NEVER returned
        return 1;
    return lowerlimit + (rand() % (upperlimit - lowerlimit));
}

