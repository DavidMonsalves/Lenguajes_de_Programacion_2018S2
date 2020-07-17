#lang scheme

#| Funcion: buscar_vecinos
   Descripcion: busca los vecinos del nodo_actual en el grafo.
   Parametros:
   nodo_actual entero
   grafo list
   Retorno: lista de los vecinos del nodo_actual.
|#

(define (buscar_vecinos nodo_actual grafo)
  (let busc((res '()) (g grafo))
    (cond
      ((= nodo_actual (car (car g))) (car (cdr (car g))))
      (else (busc res (cdr g)))
      )
    )
  )                                    

#| Funcion: visit_vecinos
   Descripcion: realiza las visitas de los vecinos del nod_actual
   Parametros:
   visitados list
   graf list
   nod_actual entero
   vecinos list
   c entero
   Retorno: lista de los nodos visitados.
|#

(define  (visit_vecinos visitados graf nod_actual vecinos c)
  (let vis((v vecinos) (n nod_actual) (visit visitados))
    (cond
      ((= c 0) (visit_vecinos (append visit (list nod_actual)) graf n (buscar_vecinos n graf) 1))
      ((and (null? v) (not(equal? (member n visit) #f))) visit)
      ((and (equal? (member n visit) #f) (not(null? v))) (visit_vecinos (append visit (list n)) graf (car v) (append v (buscar_vecinos n graf)) 1))
      ((and (equal? (member n visit) #f) (null? v)) (visit_vecinos (append visit (list n)) graf n (append v (buscar_vecinos n graf)) 1))
      (else (visit_vecinos visit graf (car v) (cdr v) 1))
    )
   )
  )

#| Funcion: componentes
   Descripcion: busca las componentes conexas de un grafo
   Parametros:
   grafo list
   Retorno: numero de componentes conexas de un grafo.
|#

(define (componentes grafo)
  (let comp((g grafo) (cont 0) (visitados '()))
    (cond
      ((null? g) cont)
      ((equal? (member (car (car g)) visitados) #f) (comp (cdr g) (+ cont 1) (visit_vecinos visitados grafo (car (car g)) '() 0)))
      (else (comp (cdr g) cont visitados))
      )
    )
  )

(componentes '((1 (2)) (2 (1 3)) (3 (2 4)) (4 (3 5)) (5 (4 6)) (6 (5 7)) (7 (6 8)) (8 (7))))