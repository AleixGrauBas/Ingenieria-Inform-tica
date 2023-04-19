#include <stdio.h>
#include <unistd.h>
#include <time.h>
#include <fcntl.h>
#include <sys/stat.h>
#include <dirent.h>
#include <stdlib.h>
#include <sys/types.h>
#include <string.h>

void *funcion (char *arg) {
  char *str;
  struct dirent *atributo;
  struct stat atributo1;
  DIR *directorio = opendir(arg);
  while((atributo = readdir(directorio)) != NULL) {
    str = malloc(strlen(arg)+strlen(atributo->d_name)+2);
    sprintf(str, "%s/%s", arg, atributo->d_name);
    lstat(str,&atributo1);
    switch(atributo1.st_mode & S_IFMT) {
      case S_IFREG:
        printf("R %s\n", str);
        break;
      case S_IFLNK:
        char *s = malloc(atributo1.st_size + 1);
        readlink(str,s,atributo1.st_size);
        s[atributo1.st_size ]='\0';
        printf("L %s -> %s\n", str, s);
        break;
      case S_IFDIR:
        printf("D %s\n", str);
        break;
    }
    free(str);
  }
  closedir(directorio);
}

int main (int argc, char *argv[]) {
  if (argc != 2) {
    printf("Numero incorrecto de argumentos\n");
    exit(-1);
  }
  struct stat atributo;
  if (lstat(argv[1],&atributo) == 0) { 
    switch(atributo.st_mode & S_IFMT) {
      case S_IFREG:
        printf("Se ha pasado un fichero regular\n");
        exit(-1);
      case S_IFLNK:
        printf("Se ha pasado un enlace simbolico\n");
        exit(-1);
      case S_IFDIR:
        funcion(argv[1]);
        exit(0);
    }
  } else {
    if ((mkdir(argv[1], 0777)) != 0) {
      printf("Eror al crear el directorio %s\n", argv[1]);
      exit(-1);
    } else {
      printf("Se crea el directorio %s\n", argv[1]);
    }
  }
  exit(0);
}
