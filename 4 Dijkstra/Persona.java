/**
 * Laboratorio de Algoritmos y Estructuras III
 * Proyecto 4
 * Integrantes: Alberto Cols, 09-10177
 * 				Matteo Ferrando, 09-10285
 * Clase: Persona.java
 * Descripcion: Implementacion de un objeto del tipo Persona.
 */

public class Persona implements Comparable<Persona>{

	protected int indice, leche, costo;
	
	/**
	 * Constructor de la clase.
	 */
	public Persona(int ind, int lch) {
		
		this.indice = ind;
		this.leche = lch;
		this.costo = Integer.MAX_VALUE;
	}
	
	/**
	 * Devuelve el indice de la persona.
	 */
	public int obtenerIndice() {
		
		return this.indice;
	}
	
	/**
	 * Devuelve la cantidad de leche que quiere la persona.
	 */
	public int obtenerLeche() {
		
		return this.leche;
	}
	
	/**
	 * Devuelve el costo de la persona.
	 */
	public int obtenerCosto() {
		
		return this.costo;
	}

	/**
	 * Asigna lch a la leche la persona.
	 */
	public void asignarLeche(int lch) {
		
		this.leche = lch;
	}
	
	/**
	 * Asigna cst al costo de llegada de la persona.
	 */
	public void asignarCosto(int cst) {
		
		this.costo = cst;
	}
	
	/**
	 * Comparador para dos objetos del tipo Persona.
	 */
	public int compareTo(Persona other) {
		
		if (this.costo > other.obtenerCosto())
			return 1;
		
		if (this.costo < other.obtenerCosto())
			return -1;
		
		if (this.indice > other.obtenerIndice())
			return 1;
		
		if (this.indice < other.obtenerIndice())
			return -1;
		
		return 0;
	}

	public String toString() {
		
		return "{" + this.indice + "," + this.leche + "}";
	}
}
