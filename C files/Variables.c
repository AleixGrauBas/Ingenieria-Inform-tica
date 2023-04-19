#include <stdio.h>

int main(){
    //Variables = Allocated space in memory to store a value
    int x; // declaración
    x  = 123; //inicializacion
    int y = 321; //las dos cosas a la vez
    
    int age = 21; 
    float nota = 2.05; //numero flotante
    char grade = 'C'; //un solo caracter
    char name[] = "Aleix"; //array of characters
    
    printf("Tienes %d años\n", age);
    printf("Hola %s\n", name);
    printf("Tu nota media es %c\n", grade);
    printf("tu notas es %f\n",nota);

    return 0;
}