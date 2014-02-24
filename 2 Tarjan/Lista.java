import java.util.ListIterator;
import java.lang.IllegalArgumentException;
import java.util.NoSuchElementException;

/**
 *		Laboratorio de Algoritmos y Estructuras III
 *		Proyecto 2
 *		Integrantes: 	Alberto Cols, 09-10177
 *							Matteo Ferrando, 09-10285
 *		Clase: Lista<T>.java		
 *		Descripcion: Implementacion del tipo Lista de una Secuencia	
 *		15/02/2012
 */
public class Lista<T> implements Secuencia<T> {

	private int tam;
	private Caja primero, ultimo;
			
	public class Caja {
		
		private T info;
		private Caja sig, prev;
		
		private Caja(T info) {
			
			this.info = info;
			this.sig = null;
			this.prev = null;
		}
	}

	/**
	 * Constructor de la clase Lista<T>.
	 */
	public Lista() {
		
		this.tam = 0;
		this.primero = null;
		this.ultimo = null;
	}
	
    /**
     * Agrega un elemento al final de la secuencia
     */
    public void agregar_final(T elemento) {
    	
    	Caja caja = new Caja(elemento);
    	
    	// Asigna el primer elemento a la lista.
    	if (this.tam == 0) {
    		this.primero = caja;
    	}
    	
    	// Asigna un elemento en la ultima posicion de la lista.
    	if (this.tam > 0) {
    		caja.prev = this.ultimo;
    		this.ultimo.sig = caja;
    	}
    	
    	this.ultimo = caja;
    	this.tam++;
    }
    
    /**
     * Elimina un elemento del final de la secuencia
     */
    public void eliminar_final() {
    	
    	// Si la lista esta vacia, no hace nada. 
    	if (this.tam == 0)
    		return;
    	
    	// Si la lista tiene un solo elemento, la vuelve null.
    	if (this.tam == 1) {
    		this.primero = null;
    	}
    	
    	this.ultimo = this.ultimo.prev;
    	
    	// Si la lista tiene mas de un elemento.
    	if (this.tam > 1) {
    		this.ultimo.sig.prev = null;
        	this.ultimo.sig = null;
    	}
    	
    	this.tam--;
    	return;
    }
    
    /**
     * Devuelve el elemento en la posicion index de la secuencia.
     */
    public T obtener_elemento(int index) {
    	
    	try {
    		
    		// Si la posicion pedida esta fuera del rango permitido.
			if ((index < 0) || (index >= this.tam)) 
				throw new IllegalArgumentException();
			
			// Si la posicion  pedida es la del ultimo elemento de la lista.
			if (index == this.tam - 1)
				return this.ultimo.info;
			
			// Si la posicion pedida es la del primer elemento de la lista.
			if (index == 0)
				return this.primero.info;
			
			int i = this.tam / 2;
			Caja actual;
			
			// Si el elemento pedido esta en la mitad inferior de la lista.
			if (index < i) {
				actual = this.primero.sig;  // Inicializo en el primer elemento.
			
				for (i = 1; i < index; i++) // Revisa hacia el siguiente elemento.
					actual = actual.sig;
			
			// Si el elemento pedido esta en la mitad superior de la lista.
			} else { 	 
				actual = this.ultimo.prev; // Inizializo en el ultimo elemento.
				
				for (i = this.tam - 2; i > index; i--) // Reviso hacia el elemento previo.
					actual = actual.prev;
			}
			
			return actual.info;
			
    	} catch (IllegalArgumentException e) {
    		System.out.println("Posicion invalida. \n");
    		return null;
    	}
		
    }
    
	/**
	 * Devuelve el tama–o de la lista.
	 */
    public int obtener_tam () {
		
		return this.tam;
	}
 
    /**
     * Clase ListIterator de la lista
     */
    public static class GeneradorLista<T> implements ListIterator<T> {

	    private Lista<T>.Caja elemActual;
	    private Lista<T> l;

	    public GeneradorLista (Lista<T> x) {

		    this.l = x;
		    this.elemActual = this.l.primero;
	    }
	    
	    @Override
	    public T next() {
	    	
	    	try {
			    if (this.elemActual == null)
				    throw new NoSuchElementException();
	
			    T s = (T) this.elemActual.info;
			    this.elemActual = this.elemActual.sig;
	
			    return s;
	    	} catch (NoSuchElementException e) {
	    		System.out.println("No hay mas elementos. \n");
	    		return null;
	    	}
	    }
	    
	    @Override
	    public boolean hasNext() {
	    	
		    return (this.elemActual != null);
	    }
	    
	    public int nextIndex() {
	    	
	    	return 0;
	    }
	    	    
	    public void remove() {				//Implementado para un next() antes de la llamada a remove()
	    	Lista<T>.Caja temp = this.elemActual.prev;
	    	
	    	if (this.elemActual.prev == this.l.primero)
	    		this.l.primero = this.elemActual;
	    	else
	    		this.elemActual.prev.prev.sig = this.elemActual;
	    	
	    	this.elemActual.prev = this.elemActual.prev.prev;
	   		temp.sig = null;
	   		temp.prev = null;
	    }
	    
	    public void add(T t) {}
	    
	    public void set(T t) {}
	    
	    public T previous() {
	    	
		    return null;
	    }
	    
	    public boolean hasPrevious() {
	    	
	    	return true;
	    }
	    
	    public int previousIndex() {
	    	
	    	return 0;
	    }
	    
    }

    /**
     * Devuelve un iterador de los elementos de la lista.
     */
    @Override
    public ListIterator<T> iterador(){
    	
    	return (new GeneradorLista<T>(this));
    }
}
