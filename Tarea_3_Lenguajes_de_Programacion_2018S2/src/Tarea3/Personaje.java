package tarea3;

interface Personaje{
  //Asigna el tipo de raza
  void asignarRaza(int RazaJug);
  //Asigna el tipo de clase
  void asignarClase(int ClaseJug);
  //Asigna la vida
  void asignarVida();
  //Asigna el nombre
  void asignarNombre(String nombre);
  //Imprime en pantalla la informacion del Personaje
  void ToString();
  //Retorna la vida actual del Personaje
  int getPS();
  //Suma el valor danio a la vida actual del personaje
  void setPS(int danio);
  //Entrega el tipo de clase
  Clase getClase();
  // Cambia la tipo de clase
  void setClase(Clase clase);
  //Entrega el tipo de raza
  Raza getRaza();
  // Cambia la tipo de raza
  void setRaza(Raza raza);
  //Retorna el nombre del Personaje
  String getNombre();
  // Cambia el nombre del Personaje
  void setNombre(String nombre);
  //Retorna el nombre de la raza del Personaje
  String getNombR();
  // Cambia el nombre de la raza del Personaje
  void setNombR(String NombR);
  //Retorna el nombre de la clase del Personaje
  String getNombC();
  // Cambia el nombre de la clase del Personaje
  void setNombC(String NombC);
}
