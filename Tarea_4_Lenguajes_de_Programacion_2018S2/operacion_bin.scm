#lang scheme

#|
Funcion: operar
Descripcion: realiza la operacion binaria entre cada par de elementos respectivos de cada lista
Parametros:
func funcion
lista1 Lista
lista2 Lista
Retorno: retorna una lista con los resultados de las operaciones realizadas.
|#
(define ((operar func) lista1 lista2)
  (let ope((l1 lista1) (l2 lista2) (res '()))
    (if (null? l1) res
        (ope (cdr l1) (cdr l2) (append res (list (func (car l1) (car l2)))))
    )
   )
 )

