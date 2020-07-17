#lang scheme

#|
Funcion: triangular_cola
Descripcion: Indica si el numero dado es triangular o no utlilizando recursuvidad de cola
Parametros:
num entero
Retorno: Verdadero o falso dependiendo si el numero es triangular o no
|#
(define (triangular_cola numero)
  (let tri((x numero) (n 1))
    (cond
      ((and (= x 0) (= n 1)) #f)
      ((< x 0) #f)
      ((= x 0) #t)
      (else (tri (- x n) (+ n 1)))
     )
   )
 )

#|
Funcion: triangular_simple
Descripcion: Indica si el numero dado es triangular o no utlilizando recursuvidad simple
Parametros:
numero entero
Retorno: Verdadero o falso dependiendo si el numero es triangular o no
|#
(define (triangular_simple numero)
  (let tri((x numero) (n 1))
    (cond
      ((and (= x 0) (= n 1)) #f)
      ((< x 0) #f)
      ((= x 0) #t)
      ((equal? (tri (- x n) (+ n 1)) #t) #t)
      (else #f)
     )
   )
 )

    
