/**
 * Laboratorio de Algoritmos y Estructuras III
 * Proyecto 4
 * Integrantes: Alberto Cols, 09-10177
 * 				Matteo Ferrando, 09-10285
 * Clase: Nodo.java
 * Descripcion: Implementacion del tipo Nodo de un Grafo.
 */
import java.lang.Comparable;

public class Nodo implements Comparable<Nodo>{
    
    protected int id, costo;
    
    public Nodo(int id) {
    	
    	this.id = id;
    	this.costo = Integer.MAX_VALUE;
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
     * Retorna una copia de this
     */
    @Override
    protected Object clone() {
    	
        Nodo clone = new Nodo(this.id);
        
    	return clone;
    }
    
    public int compareTo(Nodo other) {

    	if (this.costo < other.obtenerCosto())
    		return -1;
    	
    	if (this.costo > other.obtenerCosto())
    		return 1;
    	
    	return 0;
    }
    
    /**
     * Indica si el objeto de entrada es igual a this.
     */
    public boolean equals(Object o) {
    	
    	if (this == o)
    		return true;
    	
    	if (this.getClass() != o.getClass())
    		return false;
    	
    	Nodo other = (Nodo) o;
    	if (this.id == other.id)
    		return true;
    	
    	return false;
    }
    
    /**
     * Retorna la representacion en String del nodo.
     */
    @Override
    public String toString() {
    	
    	return "[id: " + this.id + ", costo: " + this.costo + "]";
    }
}
