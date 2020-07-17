package tarea3;

//En la interfaz Personaje se comenta que realiza cada metodo
public class Enemigo implements Personaje{
  private String Nombre;
  private String NombR;
  private String NombC;
  private Raza R;
  private Clase C;
  private int PS;
  public void asignarNombre(String Nombre){
    this.Nombre = Nombre;
  }
  public void asignarRaza(int RazaJug){
    if (RazaJug == 1) {
      Raza humano = new Humano();
      this.R = humano;
      this.NombR = "Humano";
      R.crearRaza();
    }
    if (RazaJug == 2){
      Raza elfo = new Elfo();
      this.R = elfo;
      this.NombR = "Elfo";
      R.crearRaza();
    }
    if (RazaJug == 3) {
        Raza enano = new Enano();
        this.R = enano;
        this.NombR = "Enano";
        R.crearRaza();
    }
    if (RazaJug == 4) {
        Raza orco = new Orco();
        this.R = orco;
        this.NombR = "Orco";
        R.crearRaza();
    }
  }
  public void asignarClase(int ClaseJug){
	    if (ClaseJug == 1) {
	    	Clase barbaro = new Barbaro();
	    	this.C = barbaro;
	    	this.NombC = "Barbaro";
	    	C.crearClase();
	    }
	    if (ClaseJug == 2) {
	    	Clase picaro = new Picaro();
		    this.C = picaro;
		    this.NombC = "Picaro";
		    C.crearClase();
		  }
	    if (ClaseJug == 3) {
		    Clase mago = new Mago();
		    this.C = mago;
		    this.NombC = "Mago";
		    C.crearClase();
	    }
	    if (ClaseJug == 4) {
	    	Clase clerigo = new Clerigo();
		    this.C = clerigo;
		    this.NombC = "Clerigo";
		    C.crearClase();
	    }
  }

  public void ToString() {
	   System.out.println(NombR+ " "+ NombC);
	    C.ToString();
	    R.ToString();
	    System.out.println("Vida: " +PS+ " Nombre: "+Nombre);
  }

  public void asignarVida() {
	  this.PS = 8 + R.getConstitucion();
  }

  public int getPS(){
    return this.PS;
  }

  public void setPS(int danio){ //Si es + recupera vida, - si baja vida
    this.PS += danio;
  }

  public Clase getClase(){
	return C;
  }
  
  public void setClase( Clase clase) {
    this.C = clase;
  }
  
  public Raza getRaza(){
	return R;
  }
  
  public void setRaza( Raza raza) {
	this.R = raza;
  }
  
  public String getNombre(){
	return this.Nombre;
  }
  
  public void setNombre(String nombre) {
	this.Nombre = nombre;
  }
  
  public String getNombR(){
	return this.NombR;
  }
  
  public void setNombR(String NombR) {
	this.NombR = NombR;
  }
  
  public String getNombC(){
	return this.NombC;
  }
  
  public void setNombC(String NombC) {
	this.NombC = NombC;
  }

}
