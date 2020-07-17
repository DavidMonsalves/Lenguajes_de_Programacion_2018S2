#lang scheme

#|
Funcion: calculo
Descripcion:se calcula el costo de una expresion expr utilizando un conjunto de costos costs. Si el elemento no se encuentra en el conjunto se asume un costo 0
Parametros:
costs List
expr List
Retorno: Suma de todos los costos de cada expresion en expr
|#
(define (calculo costs expr)
  (let calc((l1 expr) (sum 0))
    (cond
      ((null? l1) sum)
      ((equal? (assq (car l1) costs) #f) (calc (cdr l1) sum))
      (else (calc (cdr l1) (+ sum (cdr (assq (car l1) costs)))))
     )
    )
  )
                                       
