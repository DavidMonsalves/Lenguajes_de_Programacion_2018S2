%INSTRUCCIONES:
%Escribir sub_suma(W,M,Respuesta) donde:
%	-W corresponde a una lista con los elementos del conjunto
%	-M corresponde al entero al que se debe igualar la suma de los elementos de los subconjuntos
%	-Respuesta es una variable que entregara una lista con todos los subconjuntos que cumplan que la suma de sus elementos sea M (se debe dejar tal cual para realizar la consulta).
%Un ejemplo para la ejecucion de sub_suma seria: sub_suma([1,2,3,4,5],6,Respuesta), lo que entregaria:
%Respuesta = [[1, 2, 3], [1, 5], [2, 4]]



%Entrega la suma de todos los elementos de la lista
suma([],0).
suma([X|Y],R):-suma(Y,H), R is +(X,H).

%Se obtienen una lista con todos los subconjuntos posibles del conjunto W
subcon([],[]).
subcon([X|L1],[X|L2]) :- subcon(L1,L2).
subcon(L1,[_|L2]) :- subcon(L1,L2).
subconjuntos(W,R):- findall(X,subcon(X,W),R).

%Se revisa que subconjunto cumple con que la suma de sus elementos sea M.
sub_suma(W,M,R):- sub_suma(W,M,R,[],[]).
sub_suma(W,M,R,L,_):- L == [], subconjuntos(W,G), append(L,G,H), sub_suma(W,M,R,H,[]),!.
sub_suma(_,M,R,[L],Z):- suma(L,X), M is X, append(Z,[L],R), !.
sub_suma(_,M,R,[L],Z):- suma(L,X), not(M is X), R = Z, !.
sub_suma(_,M,R,[L|Y],Z):- suma(L,X), M is X, append(Z,[L],U), sub_suma(_,M,R,Y,U), !.
sub_suma(_,M,R,[L|Y],Z):- suma(L,X), not(M is X), sub_suma(_,M,R,Y,Z), !.