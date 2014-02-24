/** 
 * 
 * Implementacion del tipo Nodo de un Grafo
 * 
 */

public class Nodo {
    
    protected int id;
    
    public Nodo(int id) {
    	
    	this.id = id;
    }
    
    /**
     * Asigna a this el identificador id
     */
    public void asignarID(int id) {
    	
    	this.id = id;
    }
    
    /**
     * Devuelve el identificador de this
     */
    public int obtenerId() {
    	
        return this.id;
    }
    
    /**
     * Retorna una copia de this
     */
    @Override
    protected Object clone() {
    	
        Nodo clone = new Nodo(this.id);
    	return clone;
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
    	
        return this.id + "";
    }
}
