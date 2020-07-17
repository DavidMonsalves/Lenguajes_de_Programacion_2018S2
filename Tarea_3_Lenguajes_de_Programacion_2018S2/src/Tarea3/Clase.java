package tarea3;

abstract class Clase{
  protected int Armadura;
  protected String Atk;
  protected char Tipo;
  protected char Estado;
  Clase(){
	  this.Armadura = 0;
	  this.Atk = "";
	  this.Tipo = 'A' ;
	  this.Estado = 'A';
  }

  protected int getArmadura() {
	return Armadura;
  }

  protected void setArmadura(int armadura) {
	Armadura = armadura;
  }

  /*****
  * protected String getAtk()
  ******
  * Entrega el tipo de ataque que realiza el personaje
  ******
  * Input:
  * 
  ******
  * Returns:
  *  String, retorna el tipo de ataque que realiza el personaje (Destreza, Fuerza, Constitucion)
  *****/
  protected String getAtk() {
	return Atk;
  }

  /*****
  * protected void setAtk(String atk)
  ******
  * Define el tipo de ataque que realiza el personaje
  ******
  * Input:
  * char estado: Indica el tipo de ataque que realizara el personaje (Destreza, Fuerza, Constitucion)
  ******
  * Returns:
  *  void
  *****/
  protected void setAtk(String atk) {
	   Atk = atk;
  }

  /*****
  * protected char getTipo()
  ******
  * Entrega el tipo de atacante que es el personaje
  ******
  * Input:
  *
  ******
  * Returns:
  *  char, retorna el tipo del personaje ('F'=Fisico, 'M' = Magico)
  *****/
  protected char getTipo() {
	   return Tipo;
  }

  /*****
  * protected void setTipo(char tipo)
  ******
  * Define el tipo de atacnte que sera el personaje
  ******
  * Input:
  * char estado: Indica el tipo al que correspondera el personaje ('F'=Fisico, 'M' = Magico)
  ******
  * Returns:
  *  void
  *****/
  protected void setTipo(char tipo) {
	   Tipo = tipo;
  }

  /*****
  * protected char getEstado(char estado)
  ******
  * Entrega el estado del personaje
  ******
  * Input:
  *
  ******
  * Returns:
  *  char, retorna el estado actual del personaje ('A'=ataque, 'D' = Defensa)
  *****/
  protected char getEstado() {
	   return Estado;
  }

  /*****
  * protected void setEstado(char estado)
  ******
  * Cambia el estado del personaje dependiendo del caracter entregado
  ******
  * Input:
  * char estado: Indica el estado al cual se cambiara ('A'=ataque, 'D' = Defensa)
  ******
  * Returns:
  *  void
  *****/
  protected void setEstado(char estado) {
	   Estado = estado;
  }
  /*****
  * protected void ToString()
  ******
  * Imprime por pantalla toda la informacion de la clase
  ******
  * Input:
  *
  ******
  * Returns:
  *  void
  *****/
  protected void ToString() {
	  System.out.println("Armadura: "+Armadura+" Atk: "+Atk+" Tipo: "+Tipo+" Estado: "+Estado);
  }

  /*****
  * abstract void crearClase()
  ******
  * Se encarga de crear la clase definiendo las estadisticas necesarias dependiendo del tipo de clase
  ******
  * Input:
  *
  ******
  * Returns:
  *  void
  *****/
  abstract void crearClase();

  /*****
  * void ataque(Personaje atacado, Personaje atacante)
  ******
  * Realiza el ataque del atacante sobre el atacado considerando las estadisticas de ambos personajes y sus habilidades
  ******
  * Input:
  *   Personaje atacado : Corresponde al personaje que sera atacado
  *   Personaje atacante : Corresponde al personaje que atacara
  ******
  * Returns:
  *  void
  *****/
  abstract void ataque(Personaje atacado, Personaje atacante);

  /*****
  * void defender()
  ******
  * Cambia el personaje a estado de defensa
  ******
  * Input:
  ******
  * Returns:
  *  void
  *****/
  abstract void defender();
}
