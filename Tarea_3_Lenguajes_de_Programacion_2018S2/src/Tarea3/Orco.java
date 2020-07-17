package tarea3;

//En la class Raza de comentan los metodos
public class Orco extends Raza{
  Orco(){
	  super();
  }
  void crearRaza(){
	  Fuerza = 2;
	  Destreza = 0;
	  Constitucion = 1;
	  Habilidad = "Atacante";
    TipRaza = "Orco";
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
    n+=2;
    System.out.println(Colores.Azul + "Gracias a ATACANTE, el dado se ha incrementado a " + n + Colores.Termino);
    return n;
  }
  public void ToString() {
	  super.ToString();
  }
}
