#lang scheme

#|
Funcion: cruzar
Descripcion: intercambia el primer trozo de tamanio n entre las listas 1 y 2
Parametros:
lista1 Lista
lista2 Lista
Retorno: Lista con ambas listas realizado el cruce
|#
(define (cruzar lista1 lista2 n)
  (let cru((l1 lista1) (l2 lista2) (res1 '()) (res2 '()) (num n))
    (cond
      ((= num 0) (list (append res1 l1) (append res2 l2)))
      (else (cru (cdr l1) (cdr l2) (append res1 (list (car l2))) (append res2 (list (car l1))) (- num 1)))
     )
    )
  )