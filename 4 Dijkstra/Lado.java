/**
 * Laboratorio de Algoritmos y Estructuras III
 * Proyecto 4
 * Integrantes: Alberto Cols, 09-10177
 * 				Matteo Ferrando, 09-10285
 * Clase: Nodo.java
 * Descripcion: Clase que almacena la informacion de las aristas/arcos del grafo.
 */

public class Lado {

    protected int inicial;
    protected int destino;
    protected int costo;
    
    /**
     * Crea una arista/arco entre los vertices ini y dst y le asigna costo cst.
     */
    public Lado(int ini, int dst, int cst) {
    	
    	this.inicial = ini;
    	this.destino = dst;
    	this.costo = cst;
    }

    /**
     * Retorna una nueva arista/arco que es copia de this.
     */
    @Override
    protected Object clone() {
    	
    	Lado clone = new Lado(this.inicial, this.destino, this.costo);
        return clone;
    }

    /**
     * Indica si la arista/arco de entrada es igual a this.
     */
    public boolean equals(Object o) {
    	
    	if (this == o)
    		return true;
    	
    	if (this.getClass() != o.getClass())
    		return false;
    	
    	Lado other = (Lado) o;
    	if ((this.inicial == other.inicial) && (this.destino == other.destino))
    		return true;
    	
        return false;
    }
    
    /**
     * Retorna el vertice inicial de la arista/arco.
     */
    public int obtenerInicial() {
    	
        return this.inicial;
    }
        
    /**
     * Retorna el vertice destino de la arista/arco.
     */
    public int obtenerDestino() {
    	
        return this.destino;
    }
    
    /**
     * Retorna el costo de la arista/arco.
     */
    public int obtenerCosto() {
    	
        return this.costo;
    }

    /**
     * Asigna el costo de la arista/arco.
     */
    public void asignarCosto(int cst) {
    	
        this.costo = cst;
    }

    /**
     * Retorna la representacion en String de la arista/arco.
     */
    @Override
    public String toString() {
    	
        return "[(" + this.inicial + "," + this.destino + ") " + this.costo + "]";
    }

}
