/**
 * Laboratorio de Algoritmos y Estructuras III
 * Proyecto 4
 * Integrantes: Alberto Cols, 09-10177
 * 				Matteo Ferrando, 09-10285
 * Clase: ParNodo.java
 * Descripcion: Implementacion del un par que contiene la ubicacion de un nodo en el grafo y 
 * 				el costo de llegada a el.
 */

public class ParNodo implements Comparable<ParNodo>{

	private int id, costo;
	
	/**
	 * Constructor de la clase.
	 */
	public ParNodo(int id, int cst) {
		
		this.id = id;
		this.costo = cst;
	}

	 /**
     * Asigna a this el identificador id
     */
    public void asignarID(int id) {
    	
    	this.id = id;
    }
    
    /**
     * Asigna a this el costo
     */
    public void asignarCosto(int cst) {
    	
    	this.costo = cst;
    }
    
    /**
     * Devuelve el identificador de this
     */
    public int obtenerId() {
    	
        return this.id;
    }
    
    /**
     * Devuelve el costo de this
     */
    public int obtenerCosto() {
    	
        return this.costo;
    }
	
    /**
     * Comparador entre elementos del tipo ParNodo.
     */
	@Override
	public int compareTo(ParNodo other) {
		
		if (this.costo < other.obtenerCosto())
			return -1;
		if (this.costo > other.obtenerCosto())
			return 1;
		
		return 0;
	}
	
	/**
	 * Devuelve un string con la informacion dentro del ParNodo.
	 */
	@Override
	public String toString() {
		
		return "[" + this.id + ", " + this.costo + "]";
	}
	
}
