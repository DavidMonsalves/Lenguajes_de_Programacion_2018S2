#lang scheme

#|
Funcion: suma_arbol
Descripcion: suma los nodos de un arbol hasta encontrar el entero numero
Parametros:
arbol Lista
numero entero
Retorno: Retorna la suma de los nodos visitados hasta enontrar el entero numero. En caso de no ncontrarlo retorna null
|#
(define (suma_arbol arbol numero)
  (let arb((a arbol) (sum 0))
    (cond
      ((null? a) null)
      ((= (car a) numero) (+ sum (car a)))
      ((> (car a) numero) (arb (car (cdr a)) (+ sum (car a))))
      (else (arb (car (cdr (cdr a))) (+ sum (car a))))
     )
   )
 )
