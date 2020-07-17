#lang scheme

#| Funcion: largo
   Descripcion: obtiene el largo de una lista
   Parametros:
   lista list
   Retorno: retorna el largo de la lista.
|#

(define (largo lista)
  (let larg((l lista) (suma 0))
    (if (null? l) suma
        (larg (cdr l) (+ suma 1))
        )
    )
  )

#| Funcion: larg_1
   Descripcion: convierte en listas cada elemento de lista
   Parametros:
   lista list
   Retorno: lista de listas de largo 1.
|#

(define (larg_1 lista)
  (let lar1((l lista) (result '()))
    (if (null? l) result
        (lar1 (cdr l) (append result (list(list(car l)))))
        )
    )
  )

#| Funcion: anadir
   Descripcion: anade cada elemento de lista1 a la lista2 
   Parametros:
   lista1 list
   lista2 list
   Retorno: lista de listas cada elemento de lista1 anadido a la lista2.
|#

(define (anadir lista1 lista2)
  (let anad((l1 lista1) (res '()))
    (cond
      ((null? l1) res)
      ((equal? (member (car(car l1)) lista2) #f) (anad (cdr l1) (append (list (append (car l1) lista2)) res)))
      (else (anad (cdr l1) res))
      )
    )
  )

#| Funcion: producto_cruz
   Descripcion: realiza producto cruz entre una lista1 y lista2 (No incluye aquellas listas en la que se repite un numero)
   Parametros:
   lista1 list
   lista2 list
   Retorno: lista de listas, las cuales son el producto cruz entre la lista1 y lista2.
|#

(define (producto_cruz lista1 lista2)
  (let prod_cruz((l1 lista1) (l2 lista2) (res '()))
    (cond
      ((null? l2) res)
      ((member (car l2) res) (prod_cruz l1 (cdr l2) res))
      (else(prod_cruz l1 (cdr l2) (append res (anadir l1 (car l2))))
           )
      )
    )
  )

#| Funcion: lista_largo_n
   Descripcion: entrega las listas de largo n
   Parametros:
   lista list
   n entero
   Retorno: lista de listas de largo n.
|#

(define (lista_largo_n lista n)
  (let lln((l1 lista) (res '()))
    (cond
      ((null? l1) res)
      ((= (largo (car l1)) n) (lln (cdr l1)(append res (list (car l1)))))
      (else(lln (cdr l1) res))
      )
    )
  )
      
#| Funcion: permutaciones
   Descripcion: realiza las permutaciones dada una lista
   Parametros:
   lista list
   Retorno: lista de listas de cada combinacion existente.
|#

(define (permutaciones lista)
  (let perm((l1 (larg_1 lista)) (res '()) (it 0))
    (cond
      ((= (largo lista) 1) (list lista))
      ((= it (largo lista)) (lista_largo_n res (largo lista)))
      ((= it 1) (perm l1 (append res (producto_cruz l1 l1)) (+ it 1)))
      (else(perm l1 (append (producto_cruz l1 res) res) (+ it 1)))
      )
    )
  )               
                           
(permutaciones '(1 2 3))