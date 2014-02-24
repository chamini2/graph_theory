import java.lang.Comparable;

/** 
 * 
 * Implementacion del tipo Nodo de un Grafo
 * 
 */
public class Nodo implements Comparable<Nodo> {
    
    protected int id;
    protected int[] vasos;
    
    public Nodo(int id, int[] vasos) {
    	
    	this.id = id;
    	this.vasos = vasos;
    }
    
    /**
     * Asigna a this el identificador id
     */
    public void asignarId(int id) {
    	
    	this.id = id;
    }
    
    /**
     * Asigna a this el arreglo nuevo de vasos
     */
    public void asignarVasos(int[] vasos) {
    	
    	this.vasos = vasos;
    }
    
    /**
     * Devuelve el identificador de this
     */
    public int obtenerId() {
    	
        return this.id;
    }
    
    /**
     * Devuelve el arreglo de vasos de this
     */
    public int[] obtenerVasos() {
    	
        return this.vasos;
    }
    
    /**
     * Retorna una copia de this
     */
    @Override
    protected Object clone() {
    	
        Nodo clone = new Nodo(this.id, this.vasos.clone());
    	return clone;
    }
    
    @Override
    public int hashCode() {
    	
    	int result = 0;
    	
    	for (int i = 0; i < this.vasos.length; i++)
    		result += this.vasos[i] * (i + 1) * (i + 1);
    	
		return result;
    }
    
    @Override
    public int compareTo(Nodo other) { 
    	
    	for (int i = 0; (i < this.vasos.length) && (i < other.vasos.length); i++) {
    		if (this.vasos[i] < other.vasos[i])
    			return -1;
    		if (this.vasos[i] > other.vasos[i])
    			return 1;
    	}
    		
    	return 0;
    }
    
    /**
     * Indica si el objeto de entrada es igual a this.
     */
    public boolean equals(Object o) {
    	
    	if (this == o)
    		return true;
    	
    	if (o == null)
    		return false;
    	
    	if (this.getClass() != o.getClass())
    		return false;
    	
    	Nodo other = (Nodo) o;
    	
    	if (this.vasos.length != other.obtenerVasos().length)
    		return false;
    	
    	for (int i = 0; i < this.vasos.length; i++)
    		if (this.vasos[i] != other.obtenerVasos()[i])
    			return false;
    	
    	return true;
    }
    
    /**
     * Retorna la representacion en String del nodo.
     */
    @Override
    public String toString() {
    	
    	String s = this.id + " (";
        for (int i = 0; i < this.vasos.length; i++) {
        	s += " " + this.vasos[i] + " ";
        }
        s += ")";
        
        return s;
    }
}
