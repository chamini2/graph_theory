import java.lang.IllegalArgumentException;
import java.util.ListIterator;

/**
 * Clase que implemena un grafo dirigido.
 * Los arcos son instancias de la clase Lado.
 * Los nodos son instancias de la clase Nodo
 * 
 * Dado un grafo con N vertices, se asume que los identificadores de sus
 * nodos estan en el intervalo [0..n). Crear un grafo con N vertices iniciales
 * genera un grafo con cada uno de los vertices sin arcos.
 * 
 * El grafo debe permitir arcos multiples. Para fines de especificacion se
 * considera la implementacion de un grafo G = (V,E).
 */

public class GrafoDirigido {
    
    protected int numVertices;  // Numero de vertices
    protected int numLados;     // Numero de lados
    protected Arreglo<Par> vertices; // Arreglo con vertices
    
    public GrafoDirigido(int nVertices) {
    	
        this.numVertices = 0;
        this.numLados = 0;
        this.vertices = new Arreglo<Par>();
        this.agregarNodo(nVertices);
    }
    
    /**
     * Agrega un nodo al grafo. Se aconseja en este punto una implementacion
     * con costo amortizado O(1) y peor caso O(|V|).
     */
    public void agregarNodo() {
    	
    	this.vertices.fitsArreglo(1);
    	
		Par sito = new Par(new Nodo(this.numVertices++), new Lista<Lado>());
		this.vertices.agregar_final(sito);
    }
    
    /** 
     * Agrega tantos nodos al grafo como se especifique en cantidad. Se
     * aconseja en este punto una implementacion con costo amortizado
     * O(1) y peor caso O(|V|).
     * 
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
	        		Par sitos = new Par(new Nodo(this.numVertices++), new Lista<Lado>());
	        		this.vertices.agregar_final(sitos);
	        	}		
	        }
    	} catch (IllegalArgumentException e) {
    		System.out.println("Cantidad invalida. \n");
    	}
    }
    
    /**
     * Agrega el lado l a this. Se aconseja que el costo de esta operacion
     * sea costo amortizado o real O(1).
     */
    public void agregarLado(Lado l) {
    	
    	try {
	        int src = l.obtenerInicial();
	        int dst = l.obtenerDestino();
	        
	        if ((src >= this.numVertices) || (src < 0))
	        	throw new IllegalArgumentException();
	        if ((dst >= this.numVertices) || (dst < 0))
	        	throw new IllegalArgumentException();
	        
	        vertices.obtener_elemento(src).obtener_Lista().agregar_final(l);
	        this.numLados++;
	        
    	} catch (IllegalArgumentException e)  {
    		System.out.println("Inicio o destino invalidos >>> " + l.toString());
    	}
    }
    
    /**
     * Elimina el lado l de this. Retorna true si y solo si l se encontraba en
     * el grafo. Se espera costo peor caso O(|E|)
     */
    public boolean eliminarLado(Lado l) {
    	
    	try {
	        int src = l.obtenerInicial();
	        int dst = l.obtenerDestino();
	        
	        if ((src >= this.numVertices) || (src < 0))				//Verifica que estos nodos existen
	        	throw new IllegalArgumentException();
	        if ((dst >= this.numVertices) || (dst < 0))
	        	throw new IllegalArgumentException();

	        Lista<Lado> ele = this.vertices.obtener_elemento(src).obtener_Lista();
	        ListIterator<Lado> it = ele.iterador();
	        Lado side;
	        
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
     * Verifica si dst es sucesor de src. Se aconseja que el costo de esta
     * operacion sea en el peor caso lineal al numero de arcos en los que
     * inciden estos nodos.
     */
    public boolean contains(int src, int dst) {
    	
    	try {
	        if ((src >= this.numVertices) || (src < 0))				//Verifica que estos nodos existen
	        	throw new IllegalArgumentException();
	        if ((dst >= this.numVertices) || (dst < 0))
	        	throw new IllegalArgumentException();
	
	        Lista<Lado> ele = vertices.obtener_elemento(src).obtener_Lista();
	        ListIterator<Lado> it = ele.iterador();
	        Lado side;
	        Lado search = new Lado(src, dst);
	        
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
     * Devuelve las aristas en las que incide v como nodo inicial. Notese que
     * secuencia es una interfaz, usted debe decidir la estructura que retorne
     * en este punto de tal forma que pueda retornar dicha lista en tiempo
     * constante. Esta restriccion no es obligatoria, sin embargo, es
     * recomendable.
     */
	public Lista<Lado> sucesores(int v) {
		
		try {
			if ((v >= this.numVertices) || (v < 0))
				throw new IllegalArgumentException();
				    
			return vertices.obtener_elemento(v).obtener_Lista();
			    
		} catch (IllegalArgumentException e) {
			System.out.println("Nodo invalido.");
			return null;
		}
	}
    
}