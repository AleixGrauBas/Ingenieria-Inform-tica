#include <stdio.h>
#include <math.h>

int main(){

    double A;
    double B;
    double C;

    printf("Cuanto mide el lado A?");
    scanf("%lf", &A);

    printf("Cuanto mide el lado B?");
    scanf("%lf", &B);

    C = sqrt(A*A + B*B);

    printf("Lado C: %lf", C);
    
    return 0;
}