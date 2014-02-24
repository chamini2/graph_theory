/**
 * Laboratorio de Algoritmos y Estructuras III
 * Proyecto 4
 * Integrantes: Alberto Cols, 09-10177
 * 				Matteo Ferrando, 09-10285
 * Clase: Par.java
 * Descripcion: Implementacion de un par generico.
 */

public class Par<T, E> {
	
	private T t;
	private E e;
	
	/**
	 * Constructor de la clase.
	 */
	public Par(T t, E e) {
		this.t = t;
		this.e = e;
	}

	/**
	 * Devuelve el elemento T del par.
	 */
	public T obtener_T() {
		return this.t;
	}

	/**
	 * Devuelve el elemento E del par.
	 */
	public E obtener_E() {
		return this.e;
	}

	/**
	 * Asigna t al elemento T del par.
	 */
	public void asignar_T(T t) {
		this.t = t;
	}

	/**
	 * Asigna e al elemento E del par.
	 */
	public void asignar_E(E e) {
		this.e = e;
	}
}
