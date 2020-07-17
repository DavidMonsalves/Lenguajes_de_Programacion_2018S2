package tarea3;
import java.util.Scanner;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;



public class Juego{
	/*****
	* int lanzarDados(String dado)
	******
	* Entrega un entero al azar dependiendo del dado que se desee lanzar
	******
	* Input:
	*   String dado : Corresponde al tipo de dado que se desea lanzar (d6,d8 o d20)
	******
	* Returns:
	*   int, retorna un entero al azar dentro del intervalo del correspondiente dado
	*****/
	public static int lanzarDados(String dado) {
			int resultado;
			if(dado.equals("d6")){
				resultado = getRandom(1,6);
				System.out.println(Colores.Magenta + "Al lanzar un "+ Colores.Termino +  Colores.Verde + "D6" + Colores.Termino + Colores.Magenta + ", se obtuvo: "+resultado + Colores.Termino);
			  return resultado;
		  }
		  else if(dado.equals("d8")){
				resultado = getRandom(1,8);
				System.out.println(Colores.Magenta + "Al lanzar un "+ Colores.Termino +  Colores.Verde + "D8" + Colores.Termino + Colores.Magenta + ", se obtuvo: "+resultado + Colores.Termino);
			  return resultado;
		  }
			resultado = getRandom(1,20);
			System.out.println(Colores.Magenta + "Al lanzar un "+ Colores.Termino +  Colores.Verde + "D20" + Colores.Termino + Colores.Magenta + ", se obtuvo: "+resultado + Colores.Termino);
			return resultado;
	  }

		/*****
		* Personaje crearEnemigo(String name)
		******
		* Crea la instancia de un enemigo y asigna el tipo de raza, tipo de clase, nombre y vida
		******
		* Input:
		*   String name : Recibe el nombre del enemigo que se desea crear, para que en base a este nombre, se asignen las caracterisaticas anteriormente mencionadas (Clase, raza, nombre, vida)
		******
		* Returns:
		*   Personaje, retorna el enemigo creado con todos sus campos definidos
		*****/
  public static Personaje crearEnemigo(String name){
    Personaje Adversario = new Enemigo();
    if(name.equals("Klrak")){
      Adversario.asignarRaza(3);
      Adversario.asignarClase(1);
      Adversario.asignarVida();
      Adversario.asignarNombre("Klrak");
    }
    if(name.equals("Adran")){
      Adversario.asignarRaza(2);
      Adversario.asignarClase(2);
      Adversario.asignarVida();
      Adversario.asignarNombre("Adran");
    }
    if(name.equals("Isaac")){
      Adversario.asignarRaza(1);
      Adversario.asignarClase(4);
      Adversario.asignarVida();
      Adversario.asignarNombre("Isaac");
    }
    if(name.equals("Elysium")){
      Adversario.asignarRaza(2);
      Adversario.asignarClase(3);
      Adversario.asignarVida();
      Adversario.asignarNombre("Elysium");
    }
    if(name.equals("Krrogh")){
      Adversario.asignarRaza(4);
      Adversario.asignarClase(1);
      Adversario.asignarVida();
      Adversario.asignarNombre("Krrogh");
    }
    if(name.equals("Jenkins")){
      Adversario.asignarRaza(1);
      Adversario.asignarClase(3);
      Adversario.asignarVida();
      Adversario.asignarNombre("Jenkins");
    }
    return Adversario;
  }

	/*****
	* int getrandom(double min, double max)
	******
	* Retorna un numero al azar entre un intervalo dado
	******
	* Input:
	*   int min : Corresponde al limite inferior del intervalo del cual se desea obtener el numero al azar
	*   int max : Corresponde al limite superior del intervalo del cual se desea obtener el numero al azar
	******
	* Returns:
	*   int, retorna un entero al azar dentro del intervalo del correspondiente dado
	*****/
  public static int getRandom(double min, double max){
    double x = (int)(Math.random()*((max-min)+1))+min;
    return (int)x;
  }

	/*****
	* String escogerEnemigo()
	******
	* Escoge un enemigo al azar entre los 6 posibles
	******
	* Input:
	*
	******
	* Returns:
	*   String, retorna el nombre del enemigo escogido al azar
	*****/
  public static String escogerEnemigo(){
    String[] nombres = {"Klrak","Isaac", "Adran", "Elysium", "Krrogh", "Jenkins"};
    int pos = getRandom(0,5);
    return nombres[pos];
  }

	/*****
	* void main(String[] args)
	******
	* Clase principal del juego, se encarga de mostrar todo lo ocurrido durante la partida por pantalla y de realizar los enfrentamientos con los enemigos
	******
	* Input:
	*
	******
	* Returns:
	*   void
	*****/
  public static void main(String[] args) {
		Personaje Player = new Jugador();
    int i = 0;
		//Se crea una lista de tipo Personaje, que guardara los enemigos
    List<Personaje> lista = new ArrayList<Personaje>();
		//Se rellena la lista con enemigos al azar
    while (i < 3){
      Personaje enemigo = new Enemigo();
      String nomb = escogerEnemigo();
      enemigo = crearEnemigo(nomb);
      lista.add(i,enemigo);
      i++;
    }
    i = 0;

		boolean salir = false;
		int opcion;
		System.out.println("Cree su Personaje:");

		while(!salir){
			Scanner RazaJug = new Scanner(System.in);
			System.out.println("Escoja su " + Colores.Azul + "Raza" + Colores.Termino + ":(El numero)");
	    System.out.println("\t1. Humano");
	    System.out.println("\t2. Elfo");
	    System.out.println("\t3. Enano");
	    System.out.println("\t4. Orco");

			try{
				opcion = RazaJug.nextInt();
				switch(opcion){
	               case 1:
	                   System.out.println("Has seleccionado la opcion 1");
										 Player.asignarRaza(opcion);
										 salir = true;
	                   break;
	               case 2:
	                   System.out.println("Has seleccionado la opcion 2");
										 Player.asignarRaza(opcion);
										 salir = true;
	                   break;
	                case 3:
	                   System.out.println("Has seleccionado la opcion 3");
										 Player.asignarRaza(opcion);
										 salir = true;
	                   break;
	                case 4:
										System.out.println("Has seleccionado la opcion 4");
										Player.asignarRaza(opcion);
										salir = true;
										break;
	                default:
	                   System.out.println(Colores.Rojo + "SELECCIONA UNA OPCION ENTRE 1 Y 4"+ Colores.Termino);
	           }
			}catch(Exception e){
				System.out.println(Colores.Rojo + "ASEGURATE DE INGRESAR UN ENTERO" + Colores.Termino);
			}
		}



		salir = false;

		while(!salir){
			Scanner ClaseJug = new Scanner(System.in);
			System.out.println("Escoja su " + Colores.Purpura + "Clase" + Colores.Termino + ":(El numero)");
	    System.out.println("\t1. Barbaro");
	    System.out.println("\t2. Picaro");
	    System.out.println("\t3. Mago");
	    System.out.println("\t4. Clerigo");
			try{
				opcion = ClaseJug.nextInt();
				switch(opcion){
	               case 1:
	                   System.out.println("Has seleccionado la opcion 1");
										 Player.asignarClase(opcion);
										 salir = true;
	                   break;
	               case 2:
	                   System.out.println("Has seleccionado la opcion 2");
										 Player.asignarClase(opcion);
										 salir = true;
	                   break;
	                case 3:
	                   System.out.println("Has seleccionado la opcion 3");
										 Player.asignarClase(opcion);
										 salir = true;
	                   break;
	                case 4:
										System.out.println("Has seleccionado la opcion 4");
										Player.asignarClase(opcion);
										salir = true;
										break;
	                default:
	                   System.out.println(Colores.Rojo + "SELECCIONA UNA OPCION ENTRE 1 Y 4"+ Colores.Termino);
	           }

			}catch(Exception e){
				System.out.println(Colores.Rojo + "ASEGURATE DE INGRESAR UN ENTERO" + Colores.Termino);
			}
		}


    System.out.println("Escoja su Nombre: ");

    Scanner NombJug = new Scanner(System.in);

    Player.asignarNombre(NombJug.nextLine());
    Player.asignarVida();

		System.out.println();
		System.out.println();
		System.out.println("#################### INICIO DEL JUEGO #####################");
		System.out.println("alpha v1.2.0");
		System.out.println();
		System.out.println("Utilizaras el siguiente personaje:");
		Player.ToString();
		System.out.println();

		int turno = 1;
		//Mientras no se haya llegado al final de la lista, y se tenga vida, el juego continua(si se llega al final de la lista significa que todos los enemigos fueron derrotados)
		while( (i < lista.size()) && (Player.getPS() > 0) ){
      Personaje En = lista.get(i);
			System.out.println(Colores.Verde + "Pelearas con " + En.getNombre() + " el " + En.getRaza().getTipRaza() + Colores.Termino);
			//TURNO
			//Mientras la vida del enemigo o la vida del jugador sean mayores a 0, el combate continua
			while( (En.getPS() > 0) && (Player.getPS() > 0) ){
				System.out.println("TURNO "+turno);
				System.out.println(Colores.Amarillo + "La vida del enemigo es " + En.getPS() + " y tu vida es " + Player.getPS()+ Colores.Termino);
				if(Player.getRaza().getTipRaza().equals("Enano") && (Player.getRaza().getConstitucion()+10) > Player.getPS()){
					Player.getRaza().Habilidad(Player,1);
					System.out.println(Colores.Azul + "Gracias a RESISTENCIA tu vida es "+Player.getPS() + Colores.Termino);
				}

				int p;
				boolean exit = false;

				while(!exit){
					Scanner Opcion = new Scanner(System.in);
					System.out.println("Que quieres hacer:(El numero)");
					System.out.println("\t1. Atacar");
					System.out.println("\t2. Defender");
					try{
						p = Opcion.nextInt();
						if(p == 1){
							System.out.println("Decidiste"+ Colores.Amarillo + " ATACAR"+ Colores.Termino);
							Player.getClase().ataque(En, Player);
							exit = true;
						}
					  else if(p == 2) {
							System.out.println("Decidiste" + Colores.Amarillo + " DEFENDER"+ Colores.Termino);
							Player.getClase().defender();
							exit = true;
						}
						else if(!exit) System.out.println(Colores.Rojo + "SELECCIONA UNA OPCION ENTRE 1 Y 2"+ Colores.Termino);
					}catch(Exception e){
						System.out.println(Colores.Rojo + "ASEGURATE DE INGRESAR UN ENTERO" + Colores.Termino);
					}
				}

				// TURNO DEL ENEMIGO
				if(En.getPS() > 0){
					System.out.println("Es turno de "+ En.getNombre());
					if(En.getRaza().getTipRaza().equals("Enano") && (En.getRaza().getConstitucion()+8) > En.getPS()){
						En.getRaza().Habilidad(En,1);
						System.out.println(Colores.Azul + "Gracias a RESISTENCIA la vida del enemigo es "+ En.getPS() + Colores.Termino);
					}
					int opcion_contrincante = getRandom(1,2);
					if(opcion_contrincante == 1){
						System.out.println("El enemigo decidio" + Colores.Amarillo + " ATACAR"+ Colores.Termino);
						En.getClase().ataque(Player, En);
						System.out.println();
					}
					else if(opcion_contrincante == 2) {
						System.out.println("El enemigo decidio" + Colores.Amarillo + " DEFENDER"+ Colores.Termino);
						En.getClase().defender();
						System.out.println();
					}
				}
				turno++;
			}
			if(Player.getPS() > 0) System.out.println(Colores.Rojo + "Venciste a " + En.getNombre() + Colores.Termino + "\n");
			//Si se vence al enemigo, se debe avanzar en la lista un posicion
      i++;
    }
		if(Player.getPS() <= 0){
			System.out.println(Colores.Amarillo + Colores.ERROR + "PERDISTE, TU VIDA HA LLEGADO A 0" + Colores.Termino + Colores.Termino);
		}
		else if ( Player.getPS() > 0){
			System.out.println(Colores.Amarillo + Colores.Light_Cyan + "VICTORIA MAGISTRAL!" + Colores.Termino + Colores.Termino);
		}
  }
}
