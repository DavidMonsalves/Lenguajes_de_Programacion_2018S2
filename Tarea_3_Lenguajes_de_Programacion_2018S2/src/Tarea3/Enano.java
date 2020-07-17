package tarea3;

//En la class Raza de comentan los metodos
public class Enano extends Raza{
  Enano(){
	  super();
  }
  void crearRaza(){
	  Fuerza = 1;
	  Destreza = 0;
	  Constitucion = 2;
	  Habilidad = "Resistencia";
    TipRaza = "Enano";
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
    raza.setPS(n);
    return 1;
  }
  public void ToString() {
	  super.ToString();
  }
}
