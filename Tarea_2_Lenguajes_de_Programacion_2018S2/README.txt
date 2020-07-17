Nombre: Gianni Benninati ROL: 201773508-2
Nombre: David Monsalves ROL:201773547-3



- Consideraciones:
	*Escribir en la primera linea del main Srand(time(NULL)) para proveer una semilla de aleatoriedad cada vez que se ejecuta e incluir la libreria time.h colocando en la primera linea del codigo principal: #include <time.h>.
	*Se considera que el codigo principal se encuentra en el archivo main.c.
	*Se considera que la funcion de evaluacion de calidad se encuentra en el archivo fun.c.
	*Se considera que existe un archivo fun.h en donde se encuentra la declararacion de la funcion fun.
	*Se considera que en el archivo fun.c se define el struct tNodo de la forma:
				typedef struct tNodo{
					struct tNodo* siguiente;
					void* dato;
					char tipo;
				}tNodo;

- Ejecucion de terminal:
	*Cambiar al directorio en que se encuentra los archivos con el comando cd <Direccion del directorio>.
	*Una vez en el directorio utilizar el comando make.
	*Luego escribir la linea .\main
