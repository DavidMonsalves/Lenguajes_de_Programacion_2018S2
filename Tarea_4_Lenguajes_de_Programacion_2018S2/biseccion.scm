#lang scheme


#|
Funcion: biseccion
Descripcion: Utiliza el metodo de biseccion para encontrar una aproximacion de la raiz de un polinomio dado un intervalo
Parametros:
funcion Lambda(x)
intervalo Lista
iter entero
Retorno: Retorna la raiz del polinomio, o una aproximacion de ella. En caso de que el intervalo sea invalido retorna null
|#
(define ((biseccion funcion) intervalo iter)
  (let bis((sup (car (cdr intervalo))) (inf (car intervalo)) (it iter))
    (cond
      ((= (funcion sup) 0) sup)
      ((= (funcion inf) 0) inf)
      ((= it 0) (/ (+ sup inf) 2))
      ((> (* (funcion sup) (funcion inf)) 0) null)
      ((< (* (funcion (/ (+ sup inf) 2)) (funcion sup)) 0) (bis sup (/ (+ sup inf) 2) (- it 1)))
      ((< (* (funcion (/ (+ sup inf) 2)) (funcion inf)) 0) (bis (/ (+ sup inf) 2) inf (- it 1)))
      (else (/ (+ sup inf) 2))
     )
    )
  )

