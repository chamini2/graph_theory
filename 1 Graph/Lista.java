import java.util.ListIterator;
import java.lang.IllegalArgumentException;
import java.util.NoSuchElementException;

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

	public Lista() {
		
		this.tam = 0;
		this.primero = null;
		this.ultimo = null;
	}

	public int obtener_tam () {
		
		return this.tam;
	}
	
    /**
     * Agrega un elemento al final de la secuencia
     */
    public void agregar_final(T elemento) {
    	
    	Caja caja = new Caja(elemento);
    	
    	if (this.tam == 0) {
    		this.primero = caja;
    	}
    	
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
    	
    	if (this.tam == 0)
    		return;
    	
    	if (this.tam == 1) {
    		this.primero = null;
    	}
    	
    	this.ultimo = this.ultimo.prev;
    	
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
			if ((index < 0) || (index >= this.tam)) 
				throw new IllegalArgumentException();
			
			if (index == this.tam - 1)
				return this.ultimo.info;
			
			if (index == 0)
				return this.primero.info;
			
			int i = this.tam / 2;
			Caja actual;
			
			if (index < i) {
				actual = this.primero.sig;
			
				for (i = 1; i < index; i++) 
					actual = actual.sig;
			} else {
				actual = this.ultimo.prev;
				
				for (i = this.tam - 2; i > index; i--)
					actual = actual.prev;
			}
			
			return actual.info;
			
    	} catch (IllegalArgumentException e) {
    		System.out.println("Posicion invalida. \n");
    		return null;
    	}
		
    }
    
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
	    	    
	    public void remove() {										//Implementado para un next() antes de la llamada a remove()
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
     * Devuelve un iterador de los elementos de la secuencia.
     */
    @Override
    public ListIterator<T> iterador(){
    	
    	return (new GeneradorLista<T>(this));
    }
}
