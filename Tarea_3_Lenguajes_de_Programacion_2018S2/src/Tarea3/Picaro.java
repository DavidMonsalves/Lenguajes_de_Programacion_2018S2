package tarea3;

public class Picaro extends Clase{
  Picaro(){
	  super();
  }
  public void crearClase(){
    Armadura = 10;
    Atk = "Destreza";
    Tipo = 'F';
  }
  //En la class Clase se encuentran los comentarios de los metodos
  public void ataque(Personaje atacado, Personaje atacante){
    //Se cambia el estado al de ataque
    atacante.getClase().setEstado('A');
    Juego aux = new Juego();
    char estado_atacado = atacado.getClase().getEstado();
    int dado = aux.lanzarDados("d20");
    boolean critico = false;
    if(dado == 20) critico = true;
    //Se aplican las habilidades dependiendo de la raza
    if(atacante.getRaza().getTipRaza().equals("Orco")) dado = atacante.getRaza().Habilidad(atacante,dado);
    if(atacante.getRaza().getTipRaza().equals("Humano")) dado = atacante.getRaza().Habilidad(atacante,dado);
    if(estado_atacado == 'D'){
      System.out.println(atacado.getNombre() + " se encuentra defendiendo, por lo tanto se debe lanzar nuevamente el D20");
      int dado_aux = aux.lanzarDados("d20");
      if(atacante.getRaza().getTipRaza().equals("Orco")) dado_aux = atacante.getRaza().Habilidad(atacante,dado_aux);
      if(atacante.getRaza().getTipRaza().equals("Humano")) dado_aux = atacante.getRaza().Habilidad(atacante,dado_aux);
      if(dado_aux < dado){
        dado = dado_aux;
        critico = false;
      }
      System.out.println("Se utilizara el menor valor correspondiente a "+dado);
    }
    if(dado >= atacado.getClase().getArmadura()){
      System.out.println("Se obtuvo un valor mayor o igual al de la armadura, por lo tanto se atacara");
      int dado2 = aux.lanzarDados("d8") + atacante.getRaza().getDestreza();
      if(critico) dado2 += dado2;
      dado2 = dado2 * (-1);
      atacado.setPS(dado2);
      System.out.println("La vida de " + atacado.getNombre() + " luego del ataque es "  + atacado.getPS());
    }
    if(dado < atacado.getClase().getArmadura()) System.out.println("El valor del dado es menor al de la armadura por lo tanto NO se atacara");
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
