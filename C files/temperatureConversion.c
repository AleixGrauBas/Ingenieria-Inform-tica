#include <stdio.h>
#include <ctype.h>

int main(){

    char unit;
    float temp;

    printf("Is the temperature in F o C?\n");
    scanf("%c", &unit);

    unit = toupper(unit); //Lo pasamos a mayuscula

    if (unit == 'C'){
        printf("Indica la temperatura en C");
        scanf("%f", &temp);
        temp = (temp * 9 / 5) +32;
        printf("La temperatura es %1.f en F", temp);
    }
    else if (unit == 'F'){
        printf("Indica la temperatura en F");
        scanf("%f", &temp);
        temp = ((temp -32) * 5) / 9;
        printf("La temperatura es %1.f en C", temp);
    }
    else {
        printf("No es un formato valido");
    }

    return 0;
}