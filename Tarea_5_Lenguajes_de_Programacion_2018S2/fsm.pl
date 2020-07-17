%INSTRUCCIONES:
%Para realizar la consulta de si la palabra es aceptada o no por el automata se debe escribir:
% consuta([Palabra],Estado) donde:
% 	-[Palabra] corresponde a una lista formada por los caracteres de la palabra que se desea revisar su pertenencia al lenguaje.
% 	-Estado es una variable que entregara el estado de aceptacion en caso de aceptar la palabra.
%Si la palabra es aceptada, se imprimira en pantalla que fue aceptada y el estado en que se detuvo, en caso contrario, se imprimira que no fue aceptada y tambien se inidicara el estado.
%Un ejemplo seria consulta([b,b,c],Estado), lo que entregaria:
%	La palabra SI pertenece al lenguaje deteniendose en el estado:
%	Estado = 5
%Otro ejemplo que no acepta seria consulta([b,b,b,b],Estado) entregando:
%	La palabra NO pertenece al lenguaje deteniendose en el estado:
%	Estado = 3

%Se definen todas las transiciones de la forma (Nodo_inicial,simbolo,Nodo_final)
nodo(0,a,0).
nodo(0,b,1).
nodo(1,a,1).
nodo(1,b,2).
nodo(2,a,2).
nodo(2,b,3).
nodo(2,c,5).
nodo(3,a,4).
nodo(3,c,1).
nodo(4,a,7).
nodo(4,b,6).
nodo(6,a,6).
nodo(7,a,9).
nodo(7,c,8).
nodo(8,a,8).
nodo(9,a,9).

%Se define el alfabeto a utilizar por el automata
alfabeto(a).
alfabeto(b).
alfabeto(c).


consulta(L, Estado) :- consulta(L, 0, Estado).
%Si se llega al final de la lista y el estado es el 5 o el 8 (estados de aceptacion), entonces se acepta la palabra
consulta([], 5, Estado):- Estado is 5, write('La palabra SI pertenece al lenguaje deteniendose en el estado: ').
consulta([],8, Estado):- Estado is 8, write('La palabra SI pertenece al lenguaje deteniendose en el estado: ').
%Si se llega al final de la lista y el estado NO es el 5 o el 8 (estados de aceptacion), entonces NO se acepta la palabra
consulta([], 0, Estado):- Estado is 0, write('La palabra NO pertenece al lenguaje deteniendose en el estado: ').
consulta([], 1, Estado):- Estado is 1, write('La palabra NO pertenece al lenguaje deteniendose en el estado: ').
consulta([], 2, Estado):- Estado is 2, write('La palabra NO pertenece al lenguaje deteniendose en el estado: ').
consulta([], 3, Estado):- Estado is 3, write('La palabra NO pertenece al lenguaje deteniendose en el estado: ').
consulta([], 4, Estado):- Estado is 4, write('La palabra NO pertenece al lenguaje deteniendose en el estado: ').
consulta([], 6, Estado):- Estado is 6, write('La palabra NO pertenece al lenguaje deteniendose en el estado: ').
consulta([], 7, Estado):- Estado is 7, write('La palabra NO pertenece al lenguaje deteniendose en el estado: ').
consulta([], 9, Estado):- Estado is 9, write('La palabra NO pertenece al lenguaje deteniendose en el estado: ').
%Se reocorre la lista de simbolos, avanzando por el automata gracias a las transiciones nodo definidas anteriormente. En caso de que exista un simbolo que no se encuentre en el alfabeto o una transicion no este definida, se retorna falso pues no se logra el calce.
consulta([X|Y], N, Estado) :- alfabeto(X), nodo(N, X, N1), consulta(Y, N1, Estado), !.
%Si un simbolo no es del alfabeto, o no existe una transicion definida para el simbolo, automaticamente la palabra no es aceptada
consulta([X|_], N, Estado):-not(alfabeto(X)), Estado is N, write('La palabra NO pertenece al lenguaje deteniendose en el estado: '), !.
consulta([X|_], N, Estado):-not(nodo(N, X, _)), Estado is N, write('La palabra NO pertenece al lenguaje deteniendose en el estado: '), !.

