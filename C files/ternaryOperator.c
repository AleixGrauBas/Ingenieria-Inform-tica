#include <stdio.h>

int findMax(int x,  int y){
    return (x > y) ? x : y; //si true devuelve lo primero, si false lo segundo
}


int main(){
    //(condition) ? value if true : value if false

    int max = findMax(3,4);
    printf("%d", max);
}