import java.lang.IllegalArgumentException;
import java.util.ListIterator;

/**
 * Clase que implemena un grafo no dirigido.
 * Las aristas son instancias de la clase Lado.
 * Los nodos son instancias de la clase Nodo
 * 
 * Dado un grafo con N vertices, se asume que los identificadores de sus
 * nodos estan en el intervalo [0..n). Crear un grafo con N vertices iniciales
 * genera un grafo con cada uno de los vertices sin arcos.
 * 
 * El grafo debe permitir aristas multiples. Para fines de especificacion se
 * considera la implementacion de un grafo G = (V,E).
 * 
 * Debe ofrecer los mismos metodos que GrafoDirigido con las mismas firmas.
 * Puede implementar la jerarquia de clases que resulte conveniente a fines de
 * reutilizar codigo. Su decision debe estar justificada.
 */

public class GrafoNoDirigido  extends GrafoDirigido{
    
    public GrafoNoDirigido(int nVertices) {
    	
    	super(nVertices);
    }
    
    /**
     * Agrega un nodo al grafo. Se aconseja en este punto una implementacion
     * con costo amortizado O(1) y peor caso O(|V|).
     */
    public void agregarNodo() {
    		
    	super.agregarNodo();
    }
    
    /** 
     * Agrega tantos nodos al grafo como se especifique en cantidad. Se
     * aconseja en este punto una implementacion con costo amortizado
     * O(1) y peor caso O(|V|).
     * 
     * Arroja IllegalArgumentException si cantidad es un valor negativo.
     */ 
    public void agregarNodo(int cantidad) {
    	
    	super.agregarNodo(cantidad);
	}
    
    /**
     * Agrega el lado l a this. Se aconseja que el costo de esta operacion
     * sea costo amortizado o real O(1).
     */
    public void agregarLado(Lado l)
    	throws IllegalArgumentException {
    	try {
	        int src = l.obtenerInicial();
	        int dst = l.obtenerDestino();
	        
	        if ((src >= this.numVertices) || (src < 0))
	        	throw new IllegalArgumentException();
	        if ((dst >= this.numVertices) || (dst < 0))
	        	throw new IllegalArgumentException();
	        
	        vertices.obtener_elemento(src).obtener_Lista().agregar_final(l);					//Agrego el lado desde src a dst.
	        
	        vertices.obtener_elemento(dst).obtener_Lista().agregar_final(new Lado(dst, src)); 	//Agrego el lado inverso desde dst a src.

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

	        Lista<Lado> ele = vertices.obtener_elemento(src).obtener_Lista();
	        ListIterator<Lado> it = ele.iterador();
	        Lado side = null;
	        boolean b = false;
	        
	        while (it.hasNext()) {
	        	side = it.next();
	        	
	        	if (b = side.equals(l)){
	        		if (!it.hasNext())
	        			ele.eliminar_final();
	        		else
	        			it.remove();
	        		
	        		break;
	        	}
	        }
	        
	        if ((side == null) && (!b))
	        	return false;
	        
	        ele = vertices.obtener_elemento(dst).obtener_Lista();
	        it = ele.iterador();
	        Lado inv = new Lado(dst, src);
	        
	        while (it.hasNext()) {
	        	side = it.next();
	        	
	        	if (side.equals(inv)) {
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
        
    	return super.contains(src, dst);
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

		return super.sucesores(v);
	}

}
