package tarea3;

abstract class Raza{
  protected int Fuerza;
  protected int Destreza;
  protected int Constitucion;
  protected String Habilidad;
  protected String TipRaza;
  Raza(){
	  this.Fuerza = 0;
	  this.Destreza = 0;
	  this.Constitucion = 0;
	  this.Habilidad = "";
  }

  //Entrega la Fuerza del Personaje
  protected int getFuerza() {
	return Fuerza;
  }

  //Define la Fuerza del Personaje
  protected void setFuerza(int fuerza) {
	Fuerza = fuerza;
  }

  //Entrega la Destreza del Personaje
  protected int getDestreza() {
	return Destreza;
  }

  //Define la Destreza del Personaje
  protected void setDestreza(int destreza) {
	 Destreza = destreza;
  }

  //Entrega la Constitucion del Personaje
  protected int getConstitucion() {
	 return Constitucion;
  }

  //Define la Constitucion del Personaje
  protected void setConstitucion(int constitucion) {
	Constitucion = constitucion;
  }

  //Entrega la Habilidad del Personaje
  protected String getHabilidad() {
	 return Habilidad;
  }

  //Define la Habilidad del Personaje
  protected void setHabilidad(String habilidad) {
	 Habilidad = habilidad;
  }

  //Entrega el Tipo de Raza del Personaje
  protected String getTipRaza(){
    return TipRaza;
  }
  
  protected void setTipRaza(String TipRaza) {
	  this.TipRaza = TipRaza;
  }

  //Imprime en pantalla la informacion acerca de la raza del personaje
  protected void ToString() {
	  System.out.println("Fuerza: "+Fuerza+" Destreza: "+Destreza+" Constitucion: "+Constitucion+
			  " Habilidad: "+Habilidad);
  }

  //Crea una raza que definira los atributos dependiendo del tipo de raza del que se trate
  abstract void crearRaza();

  /*****
  * int Habilidad(Personaje raza, int n)
  ******
  * Define la habilidad de cada raza y la aplica dependieno del tipo de raza
  ******
  * Input:
  *   Personaje reza : Corresponde al personaje al cual se le aplicara la habilidad
  *   int n: Corresponde al valor del dado obtenido, pues dependiendo de la habilidad este debe aumentar o no
  ******
  * Returns:
  *  int, retorna el nuevo valor del dado, en caso del enano, retorna 1
  *****/
  abstract int Habilidad(Personaje raza, int n);
}
