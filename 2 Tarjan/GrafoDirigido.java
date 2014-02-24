import java.lang.IllegalArgumentException;
import java.util.ListIterator;

/**
 *		Laboratorio de Algoritmos y Estructuras III
 *		Proyecto 2
 *		Integrantes: 	Alberto Cols, 09-10177
 *							Matteo Ferrando, 09-10285
 *		Clase: GrafoDirigido.java		
 *		Descripcion: Implementacion un Grafo Dirigido	
 *		15/02/2012
 */
public class GrafoDirigido {
    
    protected int numVertices;  // Numero de vertices
    protected int numLados;     // Numero de lados
    protected Arreglo<Lista<Lado>> vertices; // Arreglo con vertices
    
    public GrafoDirigido(int nVertices) {
    	
        this.numVertices = 0;
        this.numLados = 0;
        this.vertices = new Arreglo<Lista<Lado>>();
        this.agregarNodo(nVertices);
    }
    
    /**
     * Agrega un nodo al grafo.
     */
    public void agregarNodo() {
    	
    	this.vertices.fitsArreglo(1);
    	
		Lista<Lado> sito = new Lista<Lado>();
		this.vertices.agregar_final(sito);
		this.numVertices++;
    }
    
    /** 
     * Agrega tantos nodos al grafo como se especifique en cantidad.
     * Arroja IllegalArgumentException si cantidad es un valor negativo.
     */ 
    public void agregarNodo(int cantidad) {
    	
    	try {
    		//Si intenta agregar 0 nodos
	        if (cantidad == 0)
	        	return;
	        
	        //Si intenta agregar una cantidad negativa de nodos
	        if (cantidad < 0)
	        	throw new IllegalArgumentException();
	        
	        //Si intenta agregar una cantidad aceptable de nodos
	        if (cantidad > 0) {
	        	this.vertices.fitsArreglo(cantidad);
	
	        	for (int count = 0; count < cantidad; count++) {
	        		Lista<Lado> sitos = new Lista<Lado>();
	        		this.vertices.agregar_final(sitos);
	        		this.numVertices++;
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
    		//Obtiene el nodo inicial y final del lado
	        int src = l.obtenerInicial();
	        int dst = l.obtenerDestino();
	        
	        //Determina si dichos nodos estan en el grafo
	        if ((src >= this.numVertices) || (src < 0))
	        	throw new IllegalArgumentException();
	        if ((dst >= this.numVertices) || (dst < 0))
	        	throw new IllegalArgumentException();
	        
	        //Agrega el lado desde el nodo src
	        vertices.obtener_elemento(src).agregar_final(l);
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
    		//Obtiene el nodo inicial y final del lado
	        int src = l.obtenerInicial();
	        int dst = l.obtenerDestino();
	        
	        //Determina si dichos nodos estan en el grafo
	        if ((src >= this.numVertices) || (src < 0))				
	        	throw new IllegalArgumentException();
	        if ((dst >= this.numVertices) || (dst < 0))
	        	throw new IllegalArgumentException();

	        Lista<Lado> ele = this.vertices.obtener_elemento(src);
	        ListIterator<Lado> it = ele.iterador();
	        Lado side;
	        
	        //Busca y elimina, si existe en el grafo, el lado indicado
	        while (it.hasNext()) {
	        	side = it.next();
	        	
	        	if (side.equals(l)){
	        		if (!it.hasNext())
	        			ele.eliminar_final();
	        		else
	        			it.remove();
	        		
	        		this.numLados--;
	        		return true;
	        	}
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
    public boolean contains(int src, int dst) {
    	
    	try {
	        //Determina si dichos nodos estan en el grafo
	        if ((src >= this.numVertices) || (src < 0))			
	        	throw new IllegalArgumentException();
	        if ((dst >= this.numVertices) || (dst < 0))
	        	throw new IllegalArgumentException();
	
	        Lista<Lado> ele = vertices.obtener_elemento(src);
	        ListIterator<Lado> it = ele.iterador();
	        Lado side;
	        Lado search = new Lado(src, dst);
	        
   	        //Determina si el lado existe
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
     * Devuelve las aristas en las que incide v como nodo inicial. 
     */
	public Lista<Lado> sucesores(int v) {
		
		try {
			if ((v >= this.numVertices) || (v < 0))
				throw new IllegalArgumentException();
				    
			return vertices.obtener_elemento(v);
			    
		} catch (IllegalArgumentException e) {
			System.out.println("Nodo invalido.");
			return null;
		}
	}    
}
