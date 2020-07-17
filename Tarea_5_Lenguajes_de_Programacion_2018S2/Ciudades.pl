%INSTRUCCIONES:
%Escribir alcanzables(Combustible,Nodo_inicial,Respuesta) donde:
%	-Combustible es un entero que corresponde al combustible del que se dispone
%	-Nodo_inicial corresponde a un caracter (de la a-j) que representa la ciudad inicial
%	-Respuesta corresponde a una variable que entregara la lista con todas las ciudades alcanzables desde la inicial con el combustible dado (Respuesta se debe dejar tal cual para realizar la consulta sin asignar ningun valor) 
%Un ejemplo de una consulta seria alcanzables(8,a,Respuesta), donde se buscan todas las ciudades alzanzables desde "a" con un combustible de 8, lo que entregaria:
%Respuesta = [b, c, d, e].

%Se definen todos los caminos posibles de la forma (nodo_inicial,nodo_final,Peso)
c(a,b,4).
c(a,c,3).
c(b,c,6).
c(b,e,10).
c(c,d,5).
c(c,e,1).
c(c,f,10).
c(c,g,16).
c(d,a,8).
c(d,f,7).
c(e,f,8).
c(f,b,6).
c(f,g,3).
c(f,h,1).
c(f,j,9).
c(g,f,2).
c(g,h,2).
c(g,i,9).
c(h,g,5).
c(h,i,3).
c(i,j,6).
c(j,h,4).

%Se entrega el peso de todos los caminos posibles de un nodo X a un nodo Y
camino(X,Y,P):-camino(X,Y,P,[X]).
camino(X,Y,P,_):-c(X,Y,P).
camino(X,Y,P, Temp):-c(X,B,P1),not(member(B,Temp)),
    append(Temp,[B],Temp1),camino(B,Y,P2,Temp1), P is P1 + P2.

%Se entrega una lista con todos los pesos de los caminos posibles del nodo X a Y
todos_caminos(X, Y, List) :- todos_caminos(X, Y, [], List), !.
todos_caminos(X, Y, Temp, List) :- camino(X, Y, V), \+member(V, Temp), append(Temp, [V], Tempnew) ,todos_caminos(X, Y, Tempnew, List).
todos_caminos(_,_,Temp, Temp).

%Dada una lista de valores, entrega el minimo valor de la lista
minimo([X|L],R):- minimo(L,X,R).
minimo([],M,R):- R is M.
minimo([X|Y],C,R):- X < C, minimo(Y,X,R), !.
minimo([X|Y],C,R):- X > C, minimo(Y,C,R), !.

%Entrega una lista con los pesos minimo que hay desde un nodo inicial al resto de los nodos de la forma [[nodo1,peso],[nodo2,peso]...]
pesos_minimos(I,R,L):- delete(L,I,L2), pesos_minimos(I,L2,[],R).
pesos_minimos(_,[],G,R):- R = G.
pesos_minimos(I,[X|Y],G,R):- todos_caminos(I,X,L),minimo(L,M), append(G,[[X,M]],H), pesos_minimos(I,Y,H,R), !.

%Dado el combustible disponible y un nodo inicial, entrega todas las ciudades alcanzables con ese combustible
alcanzables(C,I,R):- pesos_minimos(I,L,[a,b,c,d,e,f,g,h,i,j]), alcanzables(C,L,[],R).
alcanzables(_,[],G,R):- R = G.
alcanzables(C,[X|Y],G,R):- [W,Z] = X, +(C,-Z) >= 0 , append(G,[W],H), alcanzables(C,Y,H,R), !.
alcanzables(C,[X|Y],G,R):- [_,Z] = X, +(C,-Z) < 0 , alcanzables(C,Y,G,R), !.





