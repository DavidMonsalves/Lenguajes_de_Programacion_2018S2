package tarea3;

public class Clerigo extends Clase{
  Clerigo(){
	  super();
  }
  public void crearClase(){
    Armadura = 15;
    Atk = "Constitucion";
    Tipo = 'M';
  }
  //En la class Clase se encuentran los comentarios de los metodos
  public void ataque(Personaje atacado, Personaje atacante){
    atacante.getClase().setEstado('A');
    Juego aux = new Juego();
    char estado_atacado = atacado.getClase().getEstado();
    int dado = aux.lanzarDados("d20");
    boolean critico = false;
    if(dado == 20) critico = true;
    if(atacado.getRaza().getTipRaza().equals("Elfo")) dado = atacado.getRaza().Habilidad(atacado,dado);
    if(atacado.getRaza().getTipRaza().equals("Humano")) dado = atacado.getRaza().Habilidad(atacado,dado);
    if(estado_atacado == 'D'){
      System.out.println(atacado.getNombre() + " se encuentra defendiendo, por lo tanto se debe lanzar nuevamente el D20");
      int dado_aux = aux.lanzarDados("d20");
      if(dado_aux == 20) critico = true;
      if(atacado.getRaza().getTipRaza().equals("Elfo")) dado_aux = atacado.getRaza().Habilidad(atacado,dado_aux);
      if(atacado.getRaza().getTipRaza().equals("Humano")) dado_aux = atacado.getRaza().Habilidad(atacado,dado_aux);
      if(dado_aux > dado) dado = dado_aux;
      System.out.println("Se utilizara el mayor valor correspondiente a "+dado);
    }
    dado += atacante.getRaza().getConstitucion();
    if(dado >= 13 && !critico ){
      System.out.println("Se obtuvo un valor mayor o igual a 13, por lo tanto se resistira el ataque");
      int dado2 = (aux.lanzarDados("d6")/2)*(-1);
      atacado.setPS(dado2);
      System.out.println("La vida de " + atacado.getNombre() + " luego del ataque es "  + atacado.getPS());
    }
    else if(dado <= 13){
      System.out.println("Se obtuvo un valor menor a 13, por lo tanto se recibira el ataque completamente");
      int dado2 = (aux.lanzarDados("d6"))*(-1);
      atacado.setPS(dado2);
      System.out.println("La vida de " + atacado.getNombre() + " luego del ataque es: "  + atacado.getPS());
    }
    else if(critico) System.out.println("Se obtuvo un 20, por lo tanto se esquivara completamente el ataque");
  }
  public void defender(){
	  super.setEstado('D');
  }

  // GETTERS AND SETTERS //

  public int getArmadura(){
    return super.getArmadura();
  }
  public String getAtk(){
    return super.getAtk();
  }
  public char getTipo(){
    return super.getTipo();
  }
  public char getEstado(){
    return super.getEstado();
  }
  public void ToString() {
	super.ToString();
  }

}
