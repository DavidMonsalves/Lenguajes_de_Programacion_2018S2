package tarea3;

//En la class Raza de comentan los metodos
public class Humano extends Raza{
  Humano(){
	  super();
  }
  void crearRaza(){
	  Fuerza = 1;
	  Destreza = 1;
	  Constitucion = 1;
	  Habilidad = "Suerte";
    TipRaza = "Humano";
  }
  public int getFuerza(){
    return super.getFuerza();
  }
  public int getDestreza(){
    return super.getDestreza();
  }
  public int getConstitucion(){
    return super.getConstitucion();
  }
  public String getHabilidad(){
    return super.getHabilidad();
  }
  public String getTipRaza(){
    return super.getTipRaza();
  }
  public int Habilidad(Personaje raza, int n){
    Juego aux = new Juego();
    while(n == 1) {
      System.out.println(Colores.Azul + "Gracias a SUERTE, se debe lanzar nuevamente el dado" + Colores.Termino);
      n = aux.lanzarDados("d20");
    }
    return n;
  }
  public void ToString() {
	  super.ToString();
  }
}
