#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/stat.h>
#include <sys/wait.h>
#include <dirent.h>
#include <unistd.h>
#include <time.h>

int main(int argc, char *argv[])
{
    DIR *d;
    struct dirent *entrada;
    struct stat datos;
    char *ruta;
    pid_t nuevo;

    // Comprobamos numero de argumentos
    if (argc!=2)
    {        printf("Error: Debes especificar un directorio\n");
             exit(-1);
    }

    /* Obtener atributos del  directorio a comprimir */
    if (stat(argv[1],&datos) != 0)
    {       printf("Error. Error al obtener atributos del directorio %s\n", argv[1]);
            exit(1);
    }

    /* Comprobar si es un directorio */
    if (!(S_ISDIR(datos.st_mode)))
    {     printf("Error. %s No es un directorio\n", argv[1]);
          exit(1);
    }

    /* Apertura del directorio para leerlo */
    d= opendir(argv[1]);
    if (d==NULL)
    {     printf("Error al abrir el directorio %s\n",argv[1]);
          exit(-1);
    }
    entrada= readdir(d);
    while (entrada!=NULL)
    { // Si no es . y ..
          if ((strcmp(entrada->d_name,".")) && (strcmp(entrada->d_name,".."))) 
          {
            /* Montar nombre del fichero relativo al directorio de trabajo */
            ruta= malloc(strlen(argv[1])+strlen(entrada->d_name)+2);
            sprintf(ruta,"%s/%s",argv[1],entrada->d_name);
            /* Obtener atributos del fichero */
            if (lstat(ruta,&datos) != 0) {
                printf("Error. Error al obtener atributos del directorio o fichero %s\n",ruta);
                exit(-1);
            }
            if ((S_ISREG(datos.st_mode)&& (getuid()==datos.st_uid)))
            {
                  /* Si es un fichero regular y es del usuario */
                 nuevo= fork();
                 if (nuevo==0) {
                     printf("Anyadiendo %s\n",ruta);
                     execlp("zip","zip","./segur.zip","-g",ruta,NULL);
                     printf("Error al ejecutar zip\n");
                     exit(-1);
                 } else 
                     wait(NULL);
           }
           /*Liberar espacio ocupado por el nombre del fichero */
           free(ruta);
         }
         /* Leer siguiente entrada del directorio */
         entrada= readdir(d);
   }
   closedir(d);
   exit(0);
}
