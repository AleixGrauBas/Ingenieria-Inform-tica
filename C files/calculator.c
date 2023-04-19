#include <stdio.h>

int main(){

    char operador;
    double num1;
    double num2;
    double result;

    printf("Operador (+ - * /): \n");
    scanf("%c", &operador);

    printf("Enter num1: \n");
    scanf("%lf", &num1);

    
    printf("Enter num2: \n");
    scanf("%lf", &num2);

    switch (operador)
    {
    case '+':
        result = num2 + num1;
        printf("resultado: %lf", result);
        break;
    
    case '-':
        result = num1 - num2;
        printf("resultado: %lf", result);
        break;
    
    case '*':
        result = num2 * num1;
        printf("resultado: %lf", result);
        break;
    
    case '/':
        result = num1 / num2;
        printf("resultado: %.2lf", result);
        break;
    default:
        printf("El operador no es valido");
        break;
    }  
    return 0;
}