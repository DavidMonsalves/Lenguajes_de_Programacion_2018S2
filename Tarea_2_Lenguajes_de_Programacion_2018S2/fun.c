#include <stdlib.h>
#include <stdio.h>
#include "fun.h"

typedef struct tNodo{
	struct tNodo* siguiente;
	void* dato;
	char tipo;
}tNodo;

int fun(void *Lista){
	if(((tNodo*)Lista)->tipo == 'i') return 1;
	if(((tNodo*)Lista)->tipo == 'c') return 2;
	if(((tNodo*)Lista)->tipo == 'b') return 3;
	return 0;
}
