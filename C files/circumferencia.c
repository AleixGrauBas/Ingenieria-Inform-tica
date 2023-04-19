#include <stdio.h>

int main(){

    const double PI = 3.14159;
    double radio;
    double circumferencia;
    double area;

    printf("Cual es el radio de la circumferencia");
    scanf("%lf", &radio);
    
    area = PI * radio * radio;
    circumferencia = 2 * PI * radio;

    printf("La circumferencia es: %.2lf\n", circumferencia);
    printf("Area: %.2lf", area);

    return 0;
}