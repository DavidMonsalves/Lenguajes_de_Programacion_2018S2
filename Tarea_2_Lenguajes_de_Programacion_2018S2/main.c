#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include "genetico.h"

int main(){
	srand(time(NULL));
	genetico(mutacionRand,cruceMedio, 8, 4);
	return 0;
}
