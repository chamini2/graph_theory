/**
 *		Laboratorio de Algoritmos y Estructuras III
 *		Proyecto 2
 *		Integrantes: 	Alberto Cols, 09-10177
 *							Matteo Ferrando, 09-10285
 *		Clase: Par.java
 *		Descripcion: Implementacion de un Par (Nodo, Lista<Lado>)		
 *		15/02/2012
 */
public class Par {
	
	private Nodo elem;
	private Lista<Lado> list;
	
	/**
	 * Constructor de la clase. Crea un Par cuya inofmraci—n es un Nodo y un apuntador a la
	 * lista de sucesores de dicho nodo.
	 */
	public Par(Nodo e, Lista<Lado> l) {
		this.elem = e;
		this.list = l;
	}

	/**
	 * Devuelve el nodo almacenado en el par.
	 */
	public Nodo obtener_Nodo() {
		return this.elem;
	}
	
	/**
	 * Devuelve la lista almacenada en el par.
	 */
	public Lista<Lado> obtener_Lista() {
		return this.list;
	}

	/**
	 * Asigna un nodo al par.
	 */
	public void asignar_Nodo(Nodo e) {
		this.elem = e;
	}
	
	/**
	 * Asigna una lista al par.
	 */
	public void asignar_Lista(Lista<Lado> l) {
		this.list = l;
	}
}
