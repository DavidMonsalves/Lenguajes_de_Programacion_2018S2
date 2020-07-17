import re

'''-----------------COLORES----------------------'''
class colores:
    Azul = '\033[34m'
    Purpura = '\033[95m'
    Verde = '\033[92m'
    Amarillo = '\033[93m'
    Rojo = '\033[91m'
    Cyan = '\033[96m'
    Termino = '\033[0m'
    ERROR = '\033[41m'
'''-----------------PATRONES----------------------'''
operator = "((SUM|DIFF|PRODUKT|QUOSHUNT|MOD|BIGGR|SMALLR|BOTH|EITHER) OF|BOTH SAEM|DIFFRINT)"
operaciones = "(" + operator + "|NOT)" + " (((" + operator + " |[a-zA-Z]+\w*|[0-9]+)+ AN )+|NOT+ |(I HAS A )+|(I HAS A [a-zA-Z]+\w* ITZ )+|(VISIBLE )+|(GIMMEH )+|(R )+)+([a-zA-Z]+\w*|[0-9]+)"
expresion = "(((" + operator + " |[a-zA-Z]+\w*|[0-9]+|NOT+ |(I HAS A [a-zA-Z]+\w*)+|(I HAS A [a-zA-Z]+\w* ITZ )+|(VISIBLE )+|(GIMMEH )+|([a-zA-Z]+\w* R )+)+ AN )+|NOT+ |(I HAS A [a-zA-Z]+\w*)+|(I HAS A [a-zA-Z]+\w* ITZ )+|(VISIBLE )+|(GIMMEH )+|(R )+)+([a-zA-Z]+\w*|[0-9]+)?"
patron_variable = re.compile("^\ *I\ +HAS\ +A\ +[a-zA-Z]+\w*(\ +ITZ\ +([a-zA-Z]+\w*|[0-9]+|" + expresion + "))?$")
patron_asignacion = re.compile("^([a-zA-Z]+\w*) (R) ([a-zA-Z]+\w*|[0-9]+|" + expresion + ")$")
patron_operadorBina = re.compile("^"+ operaciones +"$")
patron_inicio = re.compile("^\ *HAI\ *$")
patron_final  = re.compile("^\ *KTHXBYE\ *$")
patronIni_ciclo = re.compile("^((IM IN YR ([a-zA-Z]+\w*) (NERFIN|UPPIN) YR [a-zA-Z]+\w* (TIL|WILE) (" + expresion + "|[a-zA-Z]+\w*|[0-9]+)))$")
patronFin_ciclo = re.compile("^IM OUTTA YR [a-zA-Z]+\w*$")
patron_nombre_ciclo = re.compile("IM IN YR ([a-zA-Z]+\w*)")
patron_estr_var = re.compile("^([a-zA-Z]+\w*|[0-9]+)$")
patron_entrada = re.compile("^(\ *GIMMEH\ +([a-zA-Z]+\w*))$")
patron_salida = re.compile("^(\ *VISIBLE)\ +([a-zA-Z]+\w*|[0-9]+|" + expresion + ")$")
patron_Inicondicion = re.compile("^(\ *O\ +RLY\?)$")
patron_YA = re.compile("^(\ *YA\ +RLY)$")
patron_NO = re.compile("^(\ *NO\ +WAI)$")
patron_Fincondicion = re.compile("^(\ *OIC)$")
buscador_variables = re.compile("\ *[a-zA-Z]\w*")

'''------------------------RECORRER ARCHIVO----------------------------'''
nombre = input("Ingrese el nombre del archivo: ")
arch = open(nombre)
# Lista que se usara para recorrer las lineas
lineas = []
 #Lista auxiliar que se utilizara en la funcion de cierre_ciclo_sintax()
lista_aux = []
pos = 0
for l in arch:
    l = l.replace("\n","")
    lineas.append(l)
    lista_aux.append(l)
largo = len(lineas)
arch.close()

''' ------------------ FUNCIONES QUE REVISAN SINTAXIS ------------------------ '''
# def operaciones_sintax(linea)
# La funcion recibe una linea y revisa si esta pertenece a una operacion
# Retorna True si la linea es una operacion y si ademas la sintaxis es correcta, en cualquier otro caso retorna False
def operaciones_sintax(linea):
    match1 = patron_operadorBina.search(linea)
    if match1:
        l = match1.group(0)
        suma_operaciones = l.count("SUM OF") + l.count("DIFF OF") + l.count("PRODUKT OF") + l.count("QUOSHUNT OF") + l.count("MOD OF") + l.count("BIGGR OF") + l.count("SMALLR OF") + l.count("BOTH OF") + l.count("EITHER OF")
        suma_operaciones = suma_operaciones + l.count("BOTH SAEM") + l.count("DIFFRINT") #Se suma la cantidad de todos los operadores dentro del string
        suma_AN = l.count("AN") - l.count("AN AN") - l.count("AN AN AN") #Se suma la cantidad de AN dentro del string, y ademas, no se consideran aquellas variables llamadas AN
        #LA cantidad de operadores debe ser igual a la cantidad de AN, ademas de cumplirse el patron anterior anterior (match1), si no es asi, entonces la sintaxis se encuentra mala
        if suma_AN == suma_operaciones:
            return True
    return False


# def expresion_sintax(linea)
# La funcion recibe una linea que es una expresion, y revisa a que tipo de expresion pertenece (asignacion, operacion, entrada, salida, variable)
# Retorna True si la sintaxis de la expresion encontrada es correcta, en cualquier otro caso retorna False
def expresion_sintax(linea):
    match_op = patron_operadorBina.search(linea)
    match_asg = patron_asignacion.search(linea)
    match_var = patron_variable.search(linea)
    match_ent = patron_entrada.search(linea)
    match_sal = patron_salida.search(linea)
    match_estr = patron_estr_var.search(linea)
    if match_op:
        if operaciones_sintax(match_op.group(0)):
            return True
    elif match_estr:
        return True
    elif match_asg:
        return True
    elif match_var:
        return True
    elif match_ent:
        return True
    elif match_sal:
        return True
    else:
        return False

# def estr_var_sintax(linea)
# La funcion recibe una linea, y revisa si cumple con la estructura de una variable
# Retorna True si la sintaxis de la variable es correcta, en cualquier otro caso retorna False
def estr_var_sintax(linea):
    match = patron_estr_var.search(linea)
    if match:
        return True
    return False

# def inicio_sintax(linea,p)
# La funcion recibe la lista lineas y una posicion p que representa la posicion de la linea que se desea analizar
# Retorna True si la sintaxis asignación ("HAI") está correcta, en caso contrario, retorna False
def inicio_sintax(lineas,p):
    match = patron_inicio.search(lineas[p])
    if match:
        if p < len(lineas) - 1:
            p+=1
        while p != len(lineas):
            match_final = patron_final.search(lineas[p])
            match2 = patron_inicio.search(lineas[p])
            #Si se encuentra un HAI antes de de un KTHXBYE, entonces el HAI actual es incorrecto
            if match2:
                return False
            #Si se encuentra un KTHXBYE y ademas la sintaxis del HAI es correcta, entonces el comienzo del programa es correcto
            if match_final:
                return True
            p+=1
    return False

# def final_sintax(l)
# La funcion recibe una linea de la lista lineas
# Retorna True si la sintaxis asignación ("KTHXBYE") está correcta, en caso contrario, retorna False
def final_sintax(l):
    match_final = patron_final.search(l)
    if match_final:
        return True
    return False

# def var_sintax(l)
# La funcion recibe una linea de la lista lineas
# Retorna True si la sintaxis de la definicion de una variable está correcta, en caso contrario, retorna False
def var_sintax(linea):
    match1 = patron_variable.search(linea)
    if match1:
        l = match1.group(0)
        #Si hay un ITZ en el match, significa que luego de ese ITZ hay una expresion, la que se debe revisar su sintaxis
        if "ITZ" in l:
            li = l.split(" ITZ ")
            if expresion_sintax(li[1]):
                return True
            else:
                return False
        else:
            return True
    return False

# def asig_sintax(linea)
# La funcion recibe una linea de la lista lineas
# Retorna True si la sintaxis asignación ("<variable> R <expresion>") está correcta, en caso contrario, retorna False
def asig_sintax(linea):
    match1 = patron_asignacion.search(linea)
    if match1:
        l = match1.group(0)
        if "R" in l:
            #El string del match se divide en 2, donde la segunda parte corresponde a la expresion la cual se debe revistar si su sintaxis es correcta
            li = l.split(" R ")
            if expresion_sintax(li[1]):
                return True
            else:
                return False
        return True
    return False

# def inicio_ciclo_sintax(linea, pos)
# La funcion recibe la lista lineas y una posicion pos que representa la posicion de la linea que se desea analizar
# Retorna True si la sintaxis del ciclo es correcta y False en caso contrario
def incio_ciclo_sintax(lineas, pos):
    match_inicio = patronIni_ciclo.search(lineas[pos])
    if match_inicio:
        cierre_ciclo = False
        l = match_inicio.group(0)
        name = l.split(" ")[3]
        pos += 1
        while pos < len(lineas):
            #Se busca si mas adelante en el archivo se cierra el ciclo con el mismo nombre con el que se abrio
            match1 = patronFin_ciclo.search(lineas[pos])
            if match1:
                name2 = match1.group(0).split(" ")[3]
                #Si el nombre con el que se arbio es igual al del cierre, entonces se cierra mas adelante
                if name == name2:
                    cierre_ciclo = True
            pos+=1
        #Ahora se debe revisar la sintaxis de la <expresion>
        if "TIL" in l:
            li = l.split(" TIL ")
            #Si la sintaxis de la expresion es correcta y ademas se cierra correctamente el ciclo, entonces la sintaxis total es correcta
            if expresion_sintax(li[1]) and cierre_ciclo:
                return True
        elif "WILE" in l:
            li = l.split(" WILE ")
            if expresion_sintax(li[1]) and cierre_ciclo:
                return True
    return False

# def fin_ciclo_sintax2(linea)
# La funcion recibe una linea de la lista de lineas
# Retorna True si se cierran correctamente los ciclos
def fin_ciclo_sintax2(linea):
    match_fin = patronFin_ciclo.search(linea)
    if match_fin:
        pos = lista_aux.index(linea)
        del lista_aux[pos]
        l = match_fin.group(0)
        name = l.split(" ")[3]
        pos -=1
        #Se recorre el archivo desde el cierre del ciclo hacia atras
        while pos > -1:
            match_inicio = patron_nombre_ciclo.search(lista_aux[pos])
            if match_inicio:
                name2 = match_inicio.group(0).split(" ")[3]
                #Si se encuentra la inicializacion de un ciclo, y ademas el nombre es el mismo, entonces se cierra correctamente el cilco
                if name == name2:
                    del lista_aux[pos]
                    return True
                else:
                    del lista_aux[pos]
                    return False
            pos-=1
    return False

# def entrada_sintax(linea)
# La funcion recibe una linea de la lista lineas
# Retorna True si la sintaxis de salida (GIMMEH <nombre_variable>) está correcta, en caso contrario, retorna False
def entrada_sintax(linea):
    match = patron_entrada.search(linea)
    if match:
        return True
    return False

# def salida_sintax(linea)
# La funcion recibe una linea de la lista lineas
# Retorna True si la sintaxis de salida (VISIBLE <expresion>) está correcta, en caso contrario, retorna False
def salida_sintax(linea):
    match = patron_salida.search(linea)
    if match:
        l = match.group(0)
        match2 = re.compile("\ *VISIBLE\ +").findall(linea)
        exp = l.replace(match2[0],"")
        if expresion_sintax(exp):
            #Se revisa si la parte de <expresion> tiene una sintaxis correcta
            return True
    return False

# def crear_lista_cond(lineas)
# La función recibe la lista lineas
# Retorna una lista con cada condicional encontrado dentro del archivo.
def crear_lista_cond(lineas):
    p = 0
    lista = []
    while p < len(lineas):
        match1 = patron_Inicondicion.match(lineas[p])
        match2 = patron_YA.match(lineas[p])
        match3 = patron_NO.match(lineas[p])
        match4 = patron_Fincondicion.match(lineas[p])
        if match1:
            lista.append(lineas[p])
            p += 1
        elif match2:
            lista.append(lineas[p])
            p += 1
        elif match3:
            lista.append(lineas[p])
            p += 1
        elif match4:
            lista.append(lineas[p])
            p += 1
        else:
            p += 1
    return lista

# def cond_sintax(R)
# La función recibe la lista auxiliar H que tiene los mismos valores de lista_cond
# Retorna una lista con True en caso que un condicional esté bien ubicado sintácticamente, en caso contrario, se asigna False.
# Dicha posición del booleano asignado coincide con la posicion del condicional dentro de lista_cond.
def cond_sintax(R):
    i = 0
    while i+3 < len(R) :
        match1 = patron_Inicondicion.match(R[i])
        match2 = patron_YA.match(R[i+1])
        match3 = patron_NO.match(R[i+2])
        match4 = patron_Fincondicion.match(R[i+3])
        if match1 and match2 and match3 and match4:
            R[i] = "True"
            R[i+1] = "True"
            R[i+2] = "True"
            R[i+3] = "True"
            i = 0
        i+= 1
    i = 0
    j = len(R)-1
    if R.count("True") == len(R):
        return R
    else:
        while i < len(R):
            match1 = patron_Inicondicion.match(R[i])
            if match1:
                P_OR = i
                match2 = patron_YA.match(R[i+1])
                if match2:
                    P_YR = i + 1
                    while j > -1:
                        match4 = patron_Fincondicion.match(R[j])
                        if match4:
                            P_O = j
                            k = j - 1
                            while k > -1:
                                match3 = patron_NO.match(R[k])
                                if match3:
                                    R[P_OR] = "True"
                                    R[P_YR] = "True"
                                    R[k] = "True"
                                    R[j] = "True"
                                k -= 1
                        j -= 1
                i += 1
            i += 1
        if R.count("True") == len(R):
            return R
        else:
            i = 0
            while i < len(R):
                if R[i] != "True":
                    R[i] = "False"
                i += 1
            return R

''' -----------------FUNCIONES QUE PINTAN-----------------------------------'''

# def pintar_operacion(linea)
# La funcion recibe una linea de la lista de lineas
# Si anteriormente se cumplio la sintaxis de la operacion, entonce se imrpime la linea entregada con los colores respectivos de la operacion
def pintar_operacion(linea):
    match1 = patron_operadorBina.search(linea)
    if match1:
        l = match1.group(0)
        li = l.split(' ')
        op = ["NOT", "SUM", "DIFF", "PRODUKT","QUOSHUNT", "MOD", "BIGGR", "SMALLR", "BOTH", "SAEM", "EITHER", "OF", "AN"]
        var = ["I", "HAS", "A", "ITZ"]
        asg = ["R", "VISIBLE", "GIMMEH"]
        cont = len(li)
        for j in li:
            if j in op:
                print(colores.Azul + j + colores.Termino, end=" ")
            elif j in var:
                print(colores.Amarillo + j + colores.Termino, end = " ")
            elif j in asg:
                print(colores.Rojo + j + colores.Termino, end = " ")
            elif cont != 1:
                print(j, end=" ")
            else:
                print(j)
            cont-=1

# def pintar_var(linea)
# La funcion recibe una linea de la lista de lineas
# Si anteriormente se cumplio la sintaxis de la inicializacion de una variable, entonce se imrpime la linea entregada con los colores respectivos de la inicializacion de variables
def pintar_var(linea):
    match1 = patron_variable.search(linea)
    if match1:
        match2 = buscador_variables.findall(linea)
        l = match1.group(0)
        if "ITZ" in l:
            li = l.split(" ITZ ")
            match2 = buscador_variables.findall(li[0])
            print(colores.Amarillo + "I HAS A " + colores.Termino + match2[-1] + colores.Amarillo + " ITZ " + colores.Termino , end = "")
            if estr_var_sintax(li[1]):
                print(li[1])
            elif operaciones_sintax(li[1]):
                pintar_operacion(li[1])
            elif entrada_sintax(li[1]):
                pintar_entrada(li[1])
            elif salida_sintax(li[1]):
                pintar_salida(li[1])
            elif var_sintax(li[1]):
                pintar_var(li[1])
        else:
            print(colores.Amarillo + "I HAS A " + colores.Termino + match2[-1])


# def pintar_asig(linea)
# La funcion recibe una linea de la lista de lineas
# Si anteriormente se cumplio la sintaxis de asignacion, entonces se imrpime la linea entregada con los colores respectivos de la asignacion
def pintar_asig(linea):
    match1 = patron_asignacion.search(linea)
    if match1:
        l = match1.group(0)
        li = l.split(" R ")
        print(li[0] + colores.Rojo + " R " + colores.Termino, end = "")
        if estr_var_sintax(li[1]):
            print(li[1])
        elif operaciones_sintax(li[1]):
            pintar_operacion(li[1])
        elif entrada_sintax(li[1]):
            pintar_entrada(li[1])
        elif salida_sintax(li[1]):
            pintar_salida(li[1])
        elif var_sintax(li[1]):
            pintar_var(li[1])

# def pintar_inicio_ciclo(linea, pos)
# La funcion recibe una linea de la lista de lineas y una posicion pos que indica la posicion de la linea a imprimir
# Si anteriormente se cumplio la sintaxis de la inicializacion de un ciclo, entonce se imrpime la linea entregada con los colores respectivos de la inicializacion de ciclo
def pintar_inicio_ciclo(lineas, pos):
    match_inicio = patronIni_ciclo.search(lineas[pos])
    if match_inicio:
        l = match_inicio.group(0)
        name = l.split(" ")[3]
        print(colores.Purpura + "IM IN YR " + colores.Termino + name, end = " ")
        if "UPPIN" in l:
            print(colores.Purpura + "UPPIN YR " + colores.Termino + (l.split(" ")[6]), end = " ")
        elif "NERFIN" in l:
            print(colores.Purpura + "NERFIN YR " + colores.Termino + (l.split(" ")[6]), end = " ")
        if "TIL" in l:
            print(colores.Purpura + "TIL" + colores.Termino, end = " ")
            li = l.split(" TIL ")
            if estr_var_sintax(li[1]):
                print(li[1])
            elif operaciones_sintax(li[1]):
                pintar_operacion(li[1])
            elif entrada_sintax(li[1]):
                pintar_entrada(li[1])
            elif salida_sintax(li[1]):
                pintar_salida(li[1])
            elif var_sintax(li[1]):
                pintar_var(li[1])
        elif "WILE" in l:
            print(colores.Purpura + "WILE" + colores.Termino, end = " ")
            li = l.split(" WILE ")
            if estr_var_sintax(li[1]):
                print(li[1])
            elif operaciones_sintax(li[1]):
                pintar_operacion(li[1])
            elif entrada_sintax(li[1]):
                pintar_entrada(li[1])
            elif salida_sintax(li[1]):
                pintar_salida(li[1])
            elif var_sintax(li[1]):
                pintar_var(li[1])

# def pintar_fin_ciclo(linea)
# La funcion recibe una linea de la lista lineas
# Pinta al fin del ciclo del color que corresponde
def pintar_fin_ciclo(linea):
    match_fin = patronFin_ciclo.search(linea)
    if match_fin:
        li = match_fin.group(0)
        name = li.split(" ")[3]
        print(colores.Purpura + "IM OUTTA YR " + colores.Termino + name)

# def pintar_entrada(linea)
# La funcion recibe una linea de la lista lineas
# Pinta a GIMMEH del color que corresponde
def pintar_entrada(linea):
    match = patron_entrada.search(linea)
    if match:
        l = match.group(0)
        match2 = buscador_variables.findall(linea)
        print(colores.Rojo + match2[0] + colores.Termino + match2[1])

# def pintar_salida(linea)
# La funcion recibe una linea de la lista lineas
# Pinta  a VISIBLE del color que corresponde
def pintar_salida(linea):
    match = patron_salida.search(linea)
    if match:
        l = match.group(0)
        match2 = re.compile("\ *VISIBLE\ +").findall(linea)
        print(colores.Rojo + match2[0] + colores.Termino, end = " ")
        exp = l.replace(match2[0],"")
        if estr_var_sintax(exp):
            print(exp)
        if operaciones_sintax(exp):
            pintar_operacion(exp)
        elif entrada_sintax(exp):
            pintar_entrada(exp)
        elif salida_sintax(exp):
            pintar_salida(exp)
        elif var_sintax(exp):
            pintar_var(exp)

'''-------------------CODIGO PRINCIPAL--------------------------------'''
# Bandera que se utiliza para saber si se inicia y cierra correctamente el codigo con el HAI y KTHXBYE
NOhay_error = False
flag = True
lista_cond = crear_lista_cond(lineas)
i= 0
H= []
while i < len(lista_cond):
    #Lista auxiliar que tiene los mismos valores que lista_cond.
    H.append(lista_cond[i])
    i += 1
aux = cond_sintax(H)
i = 0
# Flag necesario para verificar si una expresión está mal sintacticamente despues de un O RLY? y antes de un YA RLY
flag2 = True
#Se recorren todas las lineas y se pregunta si se inicia correctamente el codigo (sintaxis del HAI)
for l in lineas:
    if inicio_sintax(lineas, pos) and flag:
        NOhay_error = True
        flag = False
        print(colores.Verde + l + colores.Termino)
    #SI el codigo se inicia correctamente con el HAI se pregunta a que sintaxis corresponde la linea a analizar, y en caso de que la sintaxis este correcta se imrpime con el color respectivo
    elif NOhay_error:
        match1 = patron_Inicondicion.match(l)
        match2 = patron_YA.match(l)
        match3 = patron_NO.match(l)
        match4 = patron_Fincondicion.match(l)
        if final_sintax(l) and flag2:
            print(colores.Verde + l + colores.Termino)
            NOhay_error = False
        elif operaciones_sintax(l) and flag2:
            pintar_operacion(l)
        elif var_sintax(l) and flag2:
            pintar_var(l)
        elif asig_sintax(l) and flag2:
            pintar_asig(l)
        elif incio_ciclo_sintax(lineas, pos):
            pintar_inicio_ciclo(lineas, pos)
        elif fin_ciclo_sintax2(l) and flag2:
            pintar_fin_ciclo(l)
        elif entrada_sintax(l) and flag2:
            pintar_entrada(l)
        elif salida_sintax(l) and flag2:
            pintar_salida(l)
        elif match1 != None:
            if aux[i] == "True":
                flag2 = False
                print (colores.Cyan + lista_cond[0] +  colores.Termino)
            else:
                print (colores.ERROR + lista_cond[0]+ colores.Termino)
            del(lista_cond[0])
            i += 1
        elif match2 != None:
            if aux[i] == "True":
                flag2 = True
                print (colores.Cyan + lista_cond[0] +  colores.Termino)
            else:
                print (colores.ERROR + lista_cond[0]+ colores.Termino)
            del(lista_cond[0])
            i += 1
        elif match3 != None:
            if aux[i] == "True":
                print (colores.Cyan + lista_cond[0] +  colores.Termino)
            else:
                print (colores.ERROR + lista_cond[0]+ colores.Termino)
            del(lista_cond[0])
            i += 1
        elif match4 != None:
            if aux[i] == "True":
                print (colores.Cyan + lista_cond[0] +  colores.Termino)
            else:
                print (colores.ERROR + lista_cond[0]+ colores.Termino)
            del(lista_cond[0])
            i += 1
        else:
            #SI no se cumple ninguna de las sintaxis, entonces la linea esta incorrecta y se debe imprimir como incorrecta
            print(colores.ERROR + l + colores.Termino)
    else:
        #SI no se cumple ninguna de las sintaxis, entonces la linea esta incorrecta y se debe imprimir como incorrecta
        print(colores.ERROR + l + colores.Termino)
    pos+=1
