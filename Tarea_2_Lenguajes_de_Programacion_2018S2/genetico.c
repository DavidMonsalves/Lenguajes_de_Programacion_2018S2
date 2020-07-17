#include <time.h>
#include <stdlib.h>
#include <stdio.h>
#include "genetico.h"
#include "fun.h"

//Se define el tipo Nodo que se utilizara para crear una lista simplemente enlazada
typedef struct tNodo{
	struct tNodo* siguiente;
	void* dato;
	char tipo;
}tNodo;

char letras[6] = {'A','B','C','D','E','F'};

/*****
* void* generarSolucion
******
* Crea una lista enlazada de largo n con elementos al azar (pueden se entero, caracter o booleano)
******
* Input:
*   int n : Largo de la lista a crear
******
* Returns:
*   void*, retorna el nodo cabecera de la lista
*****/
void* generarSolucion(int n){
	int i, type;
	i = 0;
	tNodo* primer = NULL;
	tNodo* ultimo = NULL;
	while (i++ < n) {
		tNodo* nuevo_nodo = (tNodo*)malloc(sizeof(tNodo));
		type = (rand()%3);
		// Se considera los siguientes valores para type: 0 = entero, 1 = carater, 2 = booleano
		if (type == 0){
			//Se crea un entero en el heap para evitar que se libere al salir de la funcion
			int* num1 = (int*)malloc(sizeof(int));
			num1[0] = rand()%10;
			nuevo_nodo->dato = &(num1[0]);
			nuevo_nodo->tipo = 'i';
		}
		if(type == 1){
			//Se crea un caracter en el heap para evitar que se libere al salir de la funcion
			char* letra = (char*)malloc(sizeof(char));
			letra[0] = letras[rand()%6];
			nuevo_nodo->dato = &(letra[0]);
			nuevo_nodo->tipo = 'c';
		}
		if(type == 2){
			int* num2 = (int*)malloc(sizeof(int));
			num2[0] = rand()%2;
			nuevo_nodo->dato = &(num2[0]);
			nuevo_nodo->tipo = 'b';
		}
		nuevo_nodo->siguiente = NULL;
		if(primer==NULL){
			primer = nuevo_nodo;
			ultimo = nuevo_nodo;
		}
		else{
			ultimo->siguiente = nuevo_nodo;
			ultimo = nuevo_nodo;
		}
	}
	return primer;
}

/*****
* void imprimirSolucion()
******
* Imprime en pantalla una lista dada
******
* Input:
*   void* Lista : Recibe la cabecera de una lista
******
* Returns:
*   void
*****/
void imprimirSolucion(void* Lista){
	while((tNodo*)Lista != NULL){
		//Se realizan los casteos respectivos para poder imprimir la solicion
		if (((tNodo*)Lista)->tipo=='c'){
			printf("(%c,%c)\n", *((char*)(((tNodo*)Lista)->dato)),((tNodo*)Lista)->tipo);
		}
		if (((tNodo*)Lista)->tipo == 'i'){
			printf("(%d,%c)\n", *((int*)(((tNodo*)Lista)->dato)),((tNodo*)Lista)->tipo);
		}
		if (((tNodo*)Lista)->tipo == 'b'){
			printf("(%d,%c)\n", *((int*)(((tNodo*)Lista)->dato)),((tNodo*)Lista)->tipo);
		}
		Lista = ((tNodo*)Lista)->siguiente;
	}
}

/*****
* void mutacionRand()
******
* Selecciona un elemento aleatoriamente y lo reemplaza por otro valor al azar
******
* Input:
*   void* Lista : Recibe la cabecera de una lista
******
* Returns:
*   void
*****/
void mutacionRand(void* Lista){
	int largo = 0, i = 0, random,type;
	tNodo* aux = (tNodo*)Lista;
	//Se obtiene el largo de la lista para obtener una posicion random entre 0 y el largo
	while(aux != NULL){
		largo++;
		aux = aux->siguiente;
	}
	random = rand()%largo;
	//Se avanza en la lista hasta llegar a la posicion random
	while(i != random){
		i++;
		Lista = ((tNodo*)Lista)->siguiente;
	}
	//Se escoge un tipo al azar con las siguientes consideraciones: 0 = entero, 1 = carater, 2 = booleano
	type = rand()%3;
	if (type == 0){
		int* num1 = (int*)malloc(sizeof(int));
		num1[0] = rand()%10;
		//Si el tipo al azar coincide con el del nodo, se debe ver que el numero al azar que reemplazara el original no sean iguales, pues de lo contrario no habria cambios
		if (((tNodo*)Lista)->tipo == 'i'){
			while(*((int*)(((tNodo*)Lista)->dato)) == num1[0]){
				num1[0] = rand()%10;
			}
		}
		//Al guardarse el dato en el heap, antes de reemplazarlo, se debe liberar
		free(((tNodo*)Lista)->dato);
		((tNodo*)Lista)->dato = &(num1[0]);
		((tNodo*)Lista)->tipo = 'i';
	}
	if (type == 1){
		char* car = (char*)malloc(sizeof(char));
		car[0] = letras[rand()%6];
		//Si el tipo al azar coincide con el del nodo, se debe ver que el caracter al azar que reemplazara el original no sean iguales, pues de lo contrario no habria cambios
		if (((tNodo*)Lista)->tipo == 'c'){
			while(*((char*)(((tNodo*)Lista)->dato)) == car[0]){
				car[0] = letras[rand()%6];
			}
		}
		free(((tNodo*)Lista)->dato);
		((tNodo*)Lista)->dato = &(car[0]);
		((tNodo*)Lista)->tipo = 'c';
	}
	if (type == 2){
		int* num2 = (int*)malloc(sizeof(int));
		if (((tNodo*)Lista)->tipo == 'b'){
			if(*((int*)(((tNodo*)Lista)->dato)) == 1){
				num2[0] = 0;
			}else{
				num2[0] = 1;
			}
		}
		free(((tNodo*)Lista)->dato);
		((tNodo*)Lista)->dato = &(num2[0]);
		((tNodo*)Lista)->tipo = 'b';
	}
}

/*****
* void mutacionTipo()
******
* Selecciona un elemento aleatoriamente y lo reemplaza por otro valor del mismo tipo
******
* Input:
*   void* Lista : Recibe la cabecera de una lista
******
* Returns:
*   void
*****/
void mutacionTipo(void* Lista){
	int largo = 0, i = 0, random;
	tNodo* aux = (tNodo*)Lista;
	while(aux != NULL){
		largo++;
		aux = aux->siguiente;
	}
	random = rand()%largo;
	while(i != random){
		i++;
		Lista = ((tNodo*)Lista)->siguiente;
	}
	if (((tNodo*)Lista)->tipo == 'i'){
		int* num1 = (int*)malloc(sizeof(int));
		num1[0] = rand()%10;
		while(*((int*)(((tNodo*)Lista)->dato)) == num1[0]){
			num1[0] = rand()%10;
		}
		free(((tNodo*)Lista)->dato);
		((tNodo*)Lista)->dato = &(num1[0]);
	}
	if (((tNodo*)Lista)->tipo == 'c'){
		char* car = (char*)malloc(sizeof(char));
		car[0] = letras[rand()%6];
		while(*((char*)(((tNodo*)Lista)->dato)) == car[0]){
			car[0] = letras[rand()%6];
		}
		free(((tNodo*)Lista)->dato);
		((tNodo*)Lista)->dato = &(car[0]);
	}
	if (((tNodo*)Lista)->tipo == 'b'){
		int* num2 = (int*)malloc(sizeof(int));
		if(*((int*)(((tNodo*)Lista)->dato)) == 1){
			num2[0] = 0;
		}else{
			num2[0] = 1;
		}
		free(((tNodo*)Lista)->dato);
		((tNodo*)Lista)->dato = &(num2[0]);
	}
}

/*****
* void cruceIntercalado()
******
* Intercambia los elementos que se encuentren en posiciones pares entre las listas.
******
* Input:
*   void* Lista1 : Recibe la cabecera de una lista
*   void* Lista2 : Recibe la cabecera de una segunda lista
******
* Returns:
*   void
*****/
void cruceIntercalado(void * Lista1, void * Lista2){
	int i = 0;
	tNodo * aux;
	void* temp1;
	char temp2;
	aux = ((tNodo*)Lista1);
	temp1 = ((tNodo*)Lista1)->dato;
	temp2 = ((tNodo*)Lista1)->tipo;
	((tNodo*)Lista1)->dato = ((tNodo*)Lista2)->dato;
	((tNodo*)Lista1)->tipo = ((tNodo*)Lista2)->tipo;
	((tNodo*)Lista2)->dato = temp1;
	((tNodo*)Lista2)->tipo = temp2;
	while(aux != NULL){
		if(i > 0){
			tNodo *aux2 = ((tNodo*)Lista1)->siguiente;
			((tNodo*)Lista1)->siguiente = ((tNodo*)Lista2)->siguiente;
			((tNodo*)Lista2)->siguiente = aux2;
		}
		aux = aux->siguiente;
		Lista1 = ((tNodo*)Lista1)->siguiente;
		Lista2 = ((tNodo*)Lista2)->siguiente;
		i++;
	}
}

/*****
* void * copiar()
******
* Copia los valores de una lista y los guarda en una nueva
******
* Input:
*   void* Lista : Recibe la cabecera de una lista a copiar
******
* Returns:
*   void *, Retorna la cabecera de la nueva lista copiada
*****/
void* copiar(void* Lista){
	tNodo* aux = (tNodo*)Lista;
	tNodo* primer = NULL;
	tNodo* ultimo = NULL;
	while (aux != NULL) {
		tNodo* nuevo_nodo = (tNodo*)malloc(sizeof(tNodo));
		if(aux->tipo == 'i'){
			int * num = (int*)malloc(sizeof(int));
			num[0] = *(int*)aux->dato;
			nuevo_nodo->dato = &(num[0]);
			nuevo_nodo->tipo = 'i';
		}
		if(aux->tipo == 'c'){
			char * car = (char*)malloc(sizeof(char));
			car[0] = *(char*)aux->dato;
			nuevo_nodo->dato = &(car[0]);
			nuevo_nodo->tipo = 'c';
		}
		if(aux->tipo == 'b'){
			int * num2 = (int*)malloc(sizeof(int));
			num2[0] = *(int*)aux->dato;
			nuevo_nodo->dato = &(num2[0]);
			nuevo_nodo->tipo = 'b';
		}
		nuevo_nodo->siguiente = NULL;
		if(primer==NULL){
			primer = nuevo_nodo;
			ultimo = nuevo_nodo;
		}
		else{
			ultimo->siguiente = nuevo_nodo;
			ultimo = nuevo_nodo;
		}
		aux = aux->siguiente;
	}
	return primer;
}

/*****
* void cruceMedio()
******
* Intercambia los primeros n/2 elementos entre dos listas de largo n
******
* Input:
*   void* Lista1 : Recibe la cabecera de una lista
*   void* Lista2 : Recibe la cabecera de una segunda lista
******
* Returns:
*   void
*****/
void cruceMedio(void* Lista1, void* Lista2){
	int largo = 0, i = 0;
	tNodo* aux =(tNodo*)Lista1;
	while(aux != NULL){
		largo++;
		aux = aux->siguiente;
	}
	while(i != (largo/2)){
		char tipo_aux = ((tNodo*)Lista1)->tipo;
		void* valor_aux = ((tNodo*)Lista1)->dato;
		((tNodo*)Lista1)->dato = ((tNodo*)Lista2)->dato;
		((tNodo*)Lista1)->tipo =((tNodo*)Lista2)->tipo ;
		((tNodo*)Lista2)->dato = valor_aux;
		((tNodo*)Lista2)->tipo = tipo_aux;
		i++;
		Lista1 = ((tNodo*)Lista1)->siguiente;
		Lista2 = ((tNodo*)Lista2)->siguiente;
	}
}

/*****
* void borrar()
******
* Borra todos los nodos de una lista y libera las respectivas memorias
******
* Input:
*   void* Lista : Recibe la cabecera de una lista a borrar y liberar
******
* Returns:
*   void
*****/
void borrar(void* Lista){
	tNodo* aux = (tNodo*)Lista;
	while((tNodo*)Lista != NULL){
		free(((tNodo*)Lista)->dato);
		Lista = ((tNodo*)Lista)->siguiente;
		free(aux);
		aux = (tNodo*)Lista;
	}
}

/*****
* int evaluacionLista()
******
* Evalua la calidad de una lista segun la funcion entregada
******
* Input:
*   int (*fun)(void*) : Recibe una funcion que decidira la calidad de la lista
*   void* Lista : Recibe la cabecera de una lista a copiar
******
* Returns:
*   int, Retorna el puntaje de la lista segun la calidad evaluada
*****/
int evaluacionLista(int (*fun)(void*), void* Lista){
	int suma = 0;
  while( (tNodo*)Lista != NULL){
    suma = suma + fun(Lista);
		Lista = ((tNodo*)Lista)->siguiente;
  }
	return suma;
}

/*****
* void genetico()
******
* Se crean 2 listas padres y se realizan las siguientes operaaciones "iteraciones" veces:
		-Se evalua la calidad de cada padre
		-Se realica el cruce entre ambas listas padres para obtener dos listas hijas, las cuales son evaluadas. Si la evaluación de ambos hijos es mayor a la de los padres, los hijos reemplazan a sus respectivos padres.
		-Se aplica la mutación sobre los hijos obtenidos del cruzamiento, los cuales son ser nuevamente evaluados y si alguno de los hijos mutados mejoró su calidad con la mutación, reemplaza a su padre.
	Una vez realizadas las operaciones se imprimen las listas resultantes con sus respectivos puntajes.
******
* Input:
*   void (*muta)(void*): Recibe la funcion que realizara la mutacion
*   void (*cruce)(void*,void*): Recibe la funcion que realizara el cruce
*   int n: Recibe el largo n de las listas
*   int iteraciones: Recibe la cantidad de veces que se realizaran las operaciones
******
* Returns:
*   void
*****/
void genetico(void (*muta)(void*), void (*cruce)(void*,void*), int n, int iteraciones){
	void* padre1, *padre2, *copia1, *copia2;
	int puntaje1, puntaje2, puntaje_hijo1, puntaje_hijo2, i = 0;
	padre1 = generarSolucion(n);
	padre2 = generarSolucion(n);
	while(i != iteraciones){
		puntaje1 = evaluacionLista(fun,padre1);
		puntaje2 = evaluacionLista(fun,padre2);
		copia1 = copiar(padre1);
		copia2 = copiar(padre2);
		cruce(copia1,copia2);
		puntaje_hijo1 = evaluacionLista(fun,copia1);
		puntaje_hijo2 = evaluacionLista(fun,copia2);
		if(puntaje_hijo1 > puntaje1 && puntaje_hijo2 > puntaje2){
			borrar(padre1);
			borrar(padre2);
			padre1 = copiar(copia1);
			padre2 = copiar(copia2);
		}
		puntaje1 = evaluacionLista(fun,padre1);
		puntaje2 = evaluacionLista(fun,padre2);
		muta(copia1);
		muta(copia2);
		puntaje_hijo1 = evaluacionLista(fun,copia1);
		puntaje_hijo2 = evaluacionLista(fun,copia2);
		if(puntaje_hijo1 > puntaje1){
			borrar(padre1);
			padre1 = copiar(copia1);
		}
		if(puntaje_hijo2 > puntaje2){
			borrar(padre2);
			padre2 = copiar(copia2);
		}
		borrar(copia1);
		borrar(copia2);
		i++;
	}
	puntaje1 = evaluacionLista(fun,padre1);
	puntaje2 = evaluacionLista(fun,padre2);
	printf("LISTA 1\n");
	imprimirSolucion(padre1);
	printf("El puntaje de la lista es: %d\n",puntaje1);
	printf("LISTA 2\n");
	imprimirSolucion(padre2);
	printf("El puntaje de la lista es: %d\n",puntaje2);
	borrar(padre1);
	borrar(padre2);
}
