#lang scheme

#|
Funcion: factorial
Descripcion: entrega el facotrial de n
Parametros:
n entero
Retorno: Retorna el facotial de n
|#
(define (factorial n)
  (let fact ((i n) (res 1))
    (if (= i 0) res
        (fact (- i 1) (* res i)))
    )
)

#|
Funcion: Potencia
Descripcion: Multiplica el entero numero consigomismo cont veces
Parametros:
numero entero
cont entero
Retorno: Retorna el entero numero multiplicado consigomismo cont veces
|#
(define potencia
  (lambda (numero cont)
    (cond
      ((< cont 0) (potencia (/ 1 numero) (* -1 cont)))
      ((equal? cont 0) 1)
      ((equal? cont 1) numero)
      (else (* numero (potencia numero (- cont 1))))
      )
    )
)

#|
Funcion: rec_cos
Descripcion: Realia una aproximacion del coseno utilizando el teorema de Maclaurin de manera recursiva
Parametros:
valor entero
iter entero
Retorno: Entrega una aproximacion del coseno evaluado en valor
|#
(define (rec_cos valor iter)
  (let rec ((it iter) (sum 0))
    (if (< it 0) sum
        (rec (- it 1) (+ sum (/ (* (potencia -1 it) (potencia valor (* 2 it))) (factorial (* 2 it)))))
       )
    )
  )

#|
Funcion: iter_cos
Descripcion: Realia una aproximacion del coseno utilizando el teorema de Maclaurin de manera iterativa
Parametros:
valor entero
iter entero
Retorno: Entrega una aproximacion del coseno evaluado en valor
|#
(define (iter_cos valor iter)
  (do ((n 0 (+ n 1)) (sum 0 (+ sum (/ (* (potencia -1 n) (potencia valor (* 2 n))) (factorial (* 2 n))))))
      ((= n (+ iter 1)) sum)
  )
)