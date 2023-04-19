#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <unistd.h>

int main(int argc, char *argv[])
{
  int tub1[2], tub2[2];                            // Tuberias para comunicar y sincronizar
  int i, n1, n2;
  int pid, dato;
  time_t t;

  srandom(time(&t)); 

  pipe(tub1);                                      // Creamos tuberias para que las vean los hijos
  pipe(tub2); 
  pid = fork();
  if (pid) {
    pid = fork();
    if (pid){                                     // Padre
       close(tub1[1]);
       close(tub2[1]);

       do {
         n1=read(tub1[0],&dato,sizeof(int));
         if (n1!=0) printf("P: Dato leido de tub1: %d\n",dato); 
         n2=read(tub2[0],&dato,sizeof(int));
         if (n2!=0) printf("P: Dato leido de tub2: %d\n",dato);
       } while((n1!=0) || (n2 !=0));
       close(tub1[0]);
       close(tub2[0]);
    }
    else {                                        // H2 envia a traves de tub2
      close(tub1[0]);
      close(tub1[1]);
      close(tub2[0]);
      for (i=0;i<atoi(argv[2]);i++)
      {
        dato = 10 + (rand() % 10);
        write(tub2[1],&dato,sizeof(dato));
//      printf("    H2: Dato dejado en tub2: %d\n",dato);
      }
      close(tub2[1]);
    }
  } else  {                                      // H1 envia a traves de tub1
      close(tub1[0]);
      close(tub2[0]);
      close(tub2[1]);
      for (i=0;i<atoi(argv[1]);i++)
      {
        dato = rand() % 10;
        write(tub1[1],&dato,sizeof(dato));
//      printf("  H1:   Dato dejado en tub1: %d\n",dato);
      }
      close(tub1[1]);
  }
  exit(0);
}
