#include <stdio.h>

int main(){
    char grade;

    printf("\nEnter a letter grade: ");
    scanf("%c", &grade);

    switch(grade){ // Si no ponemos grade se ejecutan todos los de abajo
        case 'A':
            printf("perfect\n");
            break;
        case 'B':
            printf("You did good\n");
            break;
        default:
            printf("Enter only valid grades");
        
    }
    return 0;
}