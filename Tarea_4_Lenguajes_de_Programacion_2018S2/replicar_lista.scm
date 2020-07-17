#lang scheme

#|
Funcion: replicar lista
Descripcion: La funcion recibira dos listas, una que tendra los elementos a repetir y otra que indicara cuantas veces se debe repetir el elemento en la respectiva posicion
lista Lista
lista_replicacion Lista
Retorno: Lista con los elementos respectivos repetidos
|#
(define (replicar lista lista_replicacion)
  (let rep ((l1 lista) (l2 lista_replicacion) (val (car lista_replicacion)) (res '()))
    (cond
      ((null? l1) res)
      ((> val 0) (rep l1 l2 (- val 1)(append res (list (car l1)))))
      ((null? (cdr l2)) (rep (cdr l1) (cdr l2) (car l2) res))
      (else (rep (cdr l1) (cdr l2) (car (cdr l2)) res))
     )
   )
 )
