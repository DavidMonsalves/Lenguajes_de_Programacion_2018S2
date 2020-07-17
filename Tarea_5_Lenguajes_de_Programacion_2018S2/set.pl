%El diccionario fue implementado como una lista de listas de la siguiente manera: [[LLave1,Valor1],[Llave2,Valor2],...,[[LlaveN,ValorN]]]
%INSTRUCCIONES:
%Comenzar con la consulta "nuevo_dict." para un nuevo diccionario.
% Se desplegara un menu con las opciones de operaciones a realizar en el
% diccionario. Ingresar el numero correspondiente a la operacion a
% realizar y seguir las instrucciones del menu. Si no quiere seguir
% consultando, ingrese la palabra de termino "stop".
% CONSIDERACION: Si se esta utilizando la aplicacion de prolog fuera del
% navegador, luego de seleccionar una opcion del menu se debe colocar un
% "." al final.

%Crea un nuevo diccionario, agregando la primera llave y valor.
nuevo_dict:-
	proceso([],1).

%Imprime el diccionario actual y lee una nueva operación a realizar.
agregar(D):-
    write(D), nl,
    write('Ingrese operacion: '), nl,
    write('1. Agregar'), nl,
    write('2. Obtener valor'), nl,
    write('3. Obtener llaves'), nl,
    write('4. Obtener todos los valores'), nl,
    write('5. Obtener largo'),
	read(X),
	proceso(D,X).

%Imprime el valor actual de B.
%B puede ser una lista o un numero dependiendo de la consulta.
%Lee una nueva operacion a consultar.
obt_algo(D,B):-
    write(B), nl,
    write('Ingrese operacion: '), nl,
    write('1. Agregar'), nl,
    write('2. Obtener valor'), nl,
    write('3. Obtener llaves'), nl,
    write('4. Obtener todos los valores'), nl,
    write('5. Obtener largo'),
	read(X),
	proceso(D,X).

%Deriva a la operación a realizar.
proceso(_,stop):- !.
proceso(D,1):- write('Ingrese [LLAVE,VALOR]'), read(X), agregar(D,X),!.
proceso(D,2):- write('Ingrese llave'), read(X), obt_valor(D,D,X),!.
proceso(D,3):- obt_llaves(D,D,[]),!.
proceso(D,4):- obt_valores(D,D,[]),!.
proceso(D,5):- largo(D,D,0),!.

%Agrega al diccionario una nueva [LLAVE,VALOR]
agregar(D,X):- [LLAVE|VALOR] = X,  not(member([LLAVE|_],D)), [W|_] = VALOR, append(D,[[LLAVE,W]],Result), agregar(Result).
agregar(D,X):- [LLAVE|VALOR] = X, delete(D,[LLAVE|_],D2), [W|_] = VALOR, append(D2,[[LLAVE,W]],Result), agregar(Result).

%Obtiene el valor correspondiente a la llave.
obt_valor(D,[X|_],LLAVE):- [A|B] = X, A == LLAVE, [W|_] = B, obt_algo(D,W).
obt_valor(D,[_|C],LLAVE):- obt_valor(D,C,LLAVE).

%Obtiene todas las llaves del diccionario.
obt_llaves(D,[],L):- obt_algo(D,L).
obt_llaves(D,[D1|D2],L):- [A|_] = D1, append(L,[A],H), obt_llaves(D,D2,H).

%Obtiene todos los valores del diccionario.
obt_valores(D,[],L):- obt_algo(D,L).
obt_valores(D,[D1|D2],L):- [_|A] = D1, append(L,A,H), obt_valores(D,D2,H).

%Obtiene el largo del diccionario.
largo(D,[],L):- obt_algo(D,L).
largo(D,[_|Y],L):- H is L + 1, largo(D,Y,H).
