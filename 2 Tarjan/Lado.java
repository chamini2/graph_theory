/**
 *		Laboratorio de Algoritmos y Estructuras III
 *		Proyecto 2
 *		Integrantes: 	Alberto Cols, 09-10177
 *							Matteo Ferrando, 09-10285
 *		Clase: Lado.java		
 *		Descripcion: Clase que almacena la informacion de las aristas/arcos del grafo.
 *		15/02/2012
 */
public class Lado {

    protected int inicial;
    protected int destino;
    
    /**
     * Crea una arista/arco entre los vertices ini y dst.
     */
    public Lado(int ini, int dst) {
    	
    	this.inicial = ini;
    	this.destino = dst;
    }

    /**
     * Retorna una nueva arista/arco que es copia de this.
     */
    @Override
    protected Object clone() {
    	
    	Lado clone = new Lado(this.inicial, this.destino);
        return clone;
    }

    /**
     * Indica si la arista/arco de entrada es igual a this.
     */
    public boolean equals(Object o) {
    	
    	//Si es el mismo apuntador
    	if (this == o)
    		return true;
    	
    	//Si no son de la misma clase
    	if (this.getClass() != o.getClass())
    		return false;
    	
    	Lado other = (Lado) o;
    	//Si tienen los mismos parametros
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
     * Retorna la representacion en String de la arista/arco.
     */
    @Override
    public String toString() {
    	
        return "(" + this.inicial + ", " + this.destino + ")";
    }

}
