#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int numero;

int main()
{
    srand(time(0));
    int random = (rand() % 100) + 1;

    printf("Adivina el numero entre el 1 y el 100: \n");
    scanf("%d", &numero);

    while (numero != random)
    {
        
        if (numero > random)
        {
            printf("Demasiado Alto!\n");
        } else if (numero < random)
        {
            printf("Demasiado bajo!\n");
        }
        
        printf("Inserta otro numero: \n");
        scanf("%d", &numero);
    }
    
    printf("EL NUMERO ES CORRECTO!!");


    return 0;
}