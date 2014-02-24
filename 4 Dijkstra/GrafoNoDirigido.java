/**
 * Laboratorio de Algoritmos y Estructuras III
 * Proyecto 4
 * Integrantes: Alberto Cols, 09-10177
 * 				Matteo Ferrando, 09-10285
 * Clase: GrafoDirigido.java
 * Descripcion: Clase que implementa un grafo no dirigido.
 */
import java.lang.IllegalArgumentException;
import java.util.ListIterator;

public class GrafoNoDirigido  extends GrafoDirigido{
    
	/**
	 * Constructor de la clase.
	 */
    public GrafoNoDirigido(int nVertices) {
    	
    	super(nVertices);
    }
    
    /**
     * Agrega un nodo al grafo.
     */
    public void agregarNodo() {
    		
    	super.agregarNodo();
    }
    
    /** 
     * Agrega tantos nodos al grafo como se especifique en cantidad.
     * Arroja IllegalArgumentException si cantidad es un valor negativo.
     */ 
    public void agregarNodo(int cantidad) {
    	
    	super.agregarNodo(cantidad);
	}
    
    /**
     * Agrega el lado l No dirigido a this, es decir, agrega el lado l1=(src,dst) y el l2=(dst,src).
     */
    public void agregarLado(Lado l)
    	throws IllegalArgumentException {
    	try {
	        int src = l.obtenerInicial();
	        int dst = l.obtenerDestino();
	        int cst = l.obtenerCosto();
	        
	        if ((src >= this.numVertices) || (src < 0))
	        	throw new IllegalArgumentException();
	        if ((dst >= this.numVertices) || (dst < 0))
	        	throw new IllegalArgumentException();
	        
	        vertices.obtener_elemento(src).obtener_E().agregar_final(l);						//Agrego el lado desde src a dst.
	        
	        vertices.obtener_elemento(dst).obtener_E().agregar_final(new Lado(dst, src, cst)); 	//Agrego el lado inverso desde dst a src.
	      
	        this.numLados++;
    	} catch (IllegalArgumentException e)  {
    		System.out.println("Inicio o destino invalidos >>> " + l.toString());
    	}
    }
    
    /**
     * Elimina el lado l de this.
     */
	public boolean eliminarLado(Lado l) {
    	
    	try {
	        int src = l.obtenerInicial();
	        int dst = l.obtenerDestino();
	        int cst = l.obtenerCosto();
	        
	        if ((src >= this.numVertices) || (src < 0))				//Verifica que estos nodos existen
	        	throw new IllegalArgumentException();
	        if ((dst >= this.numVertices) || (dst < 0))
	        	throw new IllegalArgumentException();

	        Lista<Lado> ele = vertices.obtener_elemento(src).obtener_E();
	        ListIterator<Lado> it = ele.iterador();
	        Lado side = null;
	        boolean b = false;
	        int count = 0;
	        
	        while (it.hasNext()) {
	        	side = it.next();
	        	
	        	if (b = side.equals(l)){
	        		
	        		ele.eliminar_elemento(count);
	        		
	        		this.numLados--;
	        		break;
	        	}
	        	
	        	count++;
	        }
	        
	        if (!b)
	        	return false;
	        
	        ele = vertices.obtener_elemento(dst).obtener_E();
	        it = ele.iterador();
	        Lado inv = new Lado(dst, src, cst);
	        count = 0;
	        
	        while (it.hasNext()) {
	        	side = it.next();
	        	
	        	if (side.equals(inv)){
	        		
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
        
    	return super.contains(src, dst, cst);
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
     * Devuelve el elemento almacenado en la posicion v.
     */
    public Par<Nodo, Lista<Lado>> obtenerElemento(int v) {
    	
    	return super.obtenerElemento(v);
    }
    
    /**
     * Devuelve las aristas en las que incide v como nodo inicial.
     */
    public Lista<Lado> sucesores(int v) {

		return super.sucesores(v);
	}

    /**
     * Devuelve un string con la informacion del grafo.
     */
    public String toString() {
		
    	return super.toString();
	}
}
