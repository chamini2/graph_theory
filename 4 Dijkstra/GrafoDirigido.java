/**
 * Laboratorio de Algoritmos y Estructuras III
 * Proyecto 4
 * Integrantes: Alberto Cols, 09-10177
 * 				Matteo Ferrando, 09-10285
 * Clase: GrafoDirigido.java
 * Descripcion: Clase que implementa un grafo dirigido.
 */
import java.lang.IllegalArgumentException;
import java.util.ListIterator;

public class GrafoDirigido {
    
    protected int numVertices;  // Numero de vertices
    protected int numLados;     // Numero de lados
    protected Arreglo<Par<Nodo, Lista<Lado>>> vertices; // Arreglo con vertices

    /**
     * Constructor de un grafo con numero de vertices = nVertices.
     */
    public GrafoDirigido(int nVertices) {
    	
        this.numVertices = 0;
        this.numLados = 0;
        this.vertices = new Arreglo<Par<Nodo, Lista<Lado>>>();
        this.vertices.expandir(nVertices);
        this.agregarNodo(nVertices);
    }
    
    /**
     * Agrega un nodo al grafo.
     */
    public void agregarNodo() {
    	
    	this.vertices.fitsArreglo(1);
    	
		Par<Nodo, Lista<Lado>> sito = new Par<Nodo, Lista<Lado>>(new Nodo(this.numVertices++), new Lista<Lado>());
		this.vertices.agregar_final(sito);
    }
    
    /** 
     * Agrega tantos nodos al grafo como se especifique en cantidad.
     * Arroja IllegalArgumentException si cantidad es un valor negativo.
     */ 
    public void agregarNodo(int cantidad) {
    	
    	try {
	        if (cantidad == 0)
	        	return;
	        
	        if (cantidad < 0)
	        	throw new IllegalArgumentException();
	        
	        if (cantidad > 0) {
	        	this.vertices.fitsArreglo(cantidad);
	
	        	for (int count = 0; count < cantidad; count++) {
	        		Par<Nodo, Lista<Lado>> sitos = new Par<Nodo, Lista<Lado>>(new Nodo(this.numVertices++), new Lista<Lado>());
	        		this.vertices.agregar_final(sitos);
	        	}		
	        }
    	} catch (IllegalArgumentException e) {
    		System.out.println("Cantidad invalida. \n");
    	}
    }
    
    /**
     * Agrega el lado l a this.
     */
    public void agregarLado(Lado l) {
    	
    	try {
	        int src = l.obtenerInicial();
	        int dst = l.obtenerDestino();
	        
	        if ((src >= this.numVertices) || (src < 0))
	        	throw new IllegalArgumentException();
	        if ((dst >= this.numVertices) || (dst < 0))
	        	throw new IllegalArgumentException();
	        
	        vertices.obtener_elemento(src).obtener_E().agregar_final(l);
	        this.numLados++;
	        
    	} catch (IllegalArgumentException e)  {
    		System.out.println("Inicio o destino invalidos >>> " + l.toString());
    	}
    }
    
    /**
     * Elimina el lado l de this. Retorna true si y solo si l se encontraba en
     * el grafo.
     */
    public boolean eliminarLado(Lado l) {
    	
    	try {
	        int src = l.obtenerInicial();
	        int dst = l.obtenerDestino();
	        
	        if ((src >= this.numVertices) || (src < 0))				//Verifica que estos nodos existen
	        	throw new IllegalArgumentException();
	        if ((dst >= this.numVertices) || (dst < 0))
	        	throw new IllegalArgumentException();

	        Lista<Lado> ele = this.vertices.obtener_elemento(src).obtener_E();
	        ListIterator<Lado> it = ele.iterador();
	        Lado side;
	        int count = 0;
	        
	        while (it.hasNext()) {
	        	side = it.next();
	        	
	        	if (side.equals(l)){
	        		
	        		ele.eliminar_elemento(count);
	        		this.numLados--;
	        		return true;
	        	}
	        	
	        	count++;
	        }
	        
	       return false;
	        
    	} catch (IllegalArgumentException e) {
    		System.out.println("Inicio o destino invalidos >>> " + l.toString());
    		return false;
    	}
    }
    
    /**
     * Verifica si dst es sucesor de src.
     */
    public boolean contains(int src, int dst, int cst) {
    	
    	try {
	        if ((src >= this.numVertices) || (src < 0))				//Verifica que estos nodos existen
	        	throw new IllegalArgumentException();
	        if ((dst >= this.numVertices) || (dst < 0))
	        	throw new IllegalArgumentException();
	
	        Lista<Lado> ele = vertices.obtener_elemento(src).obtener_E();
	        ListIterator<Lado> it = ele.iterador();
	        Lado side;
	        Lado search = new Lado(src, dst, cst);
	        
	        while (it.hasNext()) {
	        	side = it.next();
	        	
	        	if (side.equals(search))
	        		return true;
	        }
	        
	        return false;
    	} catch (IllegalArgumentException e) {
    		System.out.println("Nodos invalidos. \n");
    		return false;
    	}
    }
    
    /**
     * Devuelve el numero de nodos en el grafo
     */
    public int obtenerNumeroNodos() {
    	
        return this.numVertices;
    }
    
    /**
     * Devuelve el numero de lados en el grafo
     */
    public int obtenerNumeroLados() {
    	
        return this.numLados;
    }
    
    /**
     * Devuelve el Par almacenado en la posicion v.
     */
    public Par<Nodo, Lista<Lado>> obtenerElemento(int v) {
    	
    	try {
	    	if ((v < 0) || (this.numVertices <= v))
	    		throw new IllegalArgumentException();
	    	
	    	return this.vertices.obtener_elemento(v);
    	} catch (IllegalArgumentException e) {
    		System.out.println("Nodo invalido. \n");
    		return null;
    	}
    }
    
    /**
     * Devuelve las aristas en las que incide v como nodo inicial.
     */
	public Lista<Lado> sucesores(int v) {
		
		try {
			if ((v >= this.numVertices) || (v < 0))
				throw new IllegalArgumentException();
				    
			return vertices.obtener_elemento(v).obtener_E();
			    
		} catch (IllegalArgumentException e) {
			System.out.println("Nodo invalido.");
			return null;
		}
	}
    
	/**
	 * Devuelve un string con la informacion del grafo.
	 */
	public String toString() {
		Par<Nodo, Lista<Lado>> sito;
		StringBuilder s = new StringBuilder(this.numVertices);
		
		for (int i = 0; i < this.numVertices; i++){

			sito = vertices.obtener_elemento(i);
			
			s.append(sito.obtener_T().toString()).append(" >>>>>").append(sito.obtener_E().toString()).append("\n");
		}
		
		return s.toString();
	}
}