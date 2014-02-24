/**
 * Laboratorio de Algoritmos y Estructuras III
 * Proyecto 4
 * Integrantes: Alberto Cols, 09-10177
 * 				Matteo Ferrando, 09-10285
 * Clase: Lista.java
 * Descripcion: Implementacion de la interfaz Secuencia<T> utilizando Listas Enlazadas.
 */
import java.util.ListIterator;
import java.lang.IllegalArgumentException;
import java.util.NoSuchElementException;

public class Lista<T> implements Secuencia<T> {

	private int tam;
	private Caja primero, ultimo;
			
	public class Caja {
		
		private T info;
		private Caja sig;
		
		private Caja(T info) {
			
			this.info = info;
			this.sig = null;
		}
	}

	/**
	 * Constructor de la clase.
	 */
	public Lista() {
		
		this.tam = 0;
		this.primero = null;
		this.ultimo = null;
	}

	/**
	 * Devuelve la cantidad de elementos que hay en la lista
	 */
	public int obtener_tam () {
		
		return this.tam;
	}
	
	/**
	 * Agrega elemento al principio de la lista.
	 */
	public void agregar_inicio (T elemento) {
		
		Caja caja = new Caja(elemento);
		
		if (this.tam == 0) {
    		this.ultimo = caja;
    	}
    	
    	if (this.tam > 0) {
    		caja.sig = this.primero;
    	}
    	
    	this.primero = caja;
    	this.tam++;
	}
	
    /**
     * Agrega un elemento al final de la lista.
     */
    public void agregar_final(T elemento) {
    	
    	Caja caja = new Caja(elemento);
    	
    	if (this.tam == 0) {
    		this.primero = caja;
    	}
    	
    	if (this.tam > 0) {
    		this.ultimo.sig = caja;
    	}
    	
    	this.ultimo = caja;
    	this.tam++;
    }
    
    /**
     * Elimina el primer elemento de la lista.
     */
    public void eliminar_inicio(){
    	
    	//Si la lista esta vacia, no hace nada.
    	if (this.tam == 0)
    		return;
    	
    	//Si la lista tiene un solo elemento, asigna null a primero y ultimo.
    	if (this.tam == 1) {
    		this.primero = null;
    		this.ultimo = null;
        }
    	
    	//Si la lista tiene mas de un elemento.
    	if (this.tam > 1) {
    		Caja temp = this.primero;
    		
    		this.primero = this.primero.sig;
    		
        	temp.sig = null;
        	temp = null;
    	}
    	
    	this.tam--;
    }
    
    /**
     * Elimina un elemento del final de la secuencia
     */
    public void eliminar_final() {
    	
    	Caja temp, next;
    	
    	//Si no hay elementos.
    	if (this.tam == 0)
    		return;
    	
    	//Si hay un solo elemento.
    	if (this.tam == 1) {
    		this.primero = null;
    		this.ultimo = null;
    	}
    	
    	//Si hay mas de un elemento.
    	if (this.tam > 1) {
    		
    		temp = this.primero;
    		next = this.primero.sig;
    		
        	while (next.sig != null) {
        		temp = next;
        		next = next.sig;
        	}
        	
        	temp.sig = null;
        	this.ultimo = temp;
        	next = null;
    	}
    	
    	this.tam--;
    }
    
    /**
     * Elimina el elemento en la "posicion" index.
     */
    public boolean eliminar_elemento(int index) {
    	
			if ((index < 0) || (index >= this.tam)) 
				return false;
			
			if (index == this.tam - 1) 
				this.eliminar_final();
			
			if (index == 0)
				this.eliminar_inicio();
			if ((0 < index) &&(index < this.tam - 1)) {
				Caja actual = this.primero.sig, temp;
				
				for (int i = 1; i < index - 1; i++) 
					actual = actual.sig;
				
				temp = actual.sig;
				actual.sig = actual.sig.sig;
				temp.sig = null;
			}
			
			this.tam--;
			return true;	
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
			
			Caja actual;
			
			actual = this.primero.sig;
			
			for (int i = 1; i < index; i++) 
				actual = actual.sig;
			
			return actual.info;
			
    	} catch (IllegalArgumentException e) {
    		System.out.println("Posicion invalida. \n");
    		return null;
    	}
		
    }
    
    /**
     * Devuelve un string con la informacion en la lista.
     */
    public String toString() {
    	
    	ListIterator<T> iterator =  this.iterador();
    	StringBuilder string = new StringBuilder();
    	
    	while (iterator.hasNext()) {
    		string.append(iterator.next().toString());
    	}
    	
    	return string.toString();
    }
    
    /**
     * ListIterator de la lista.
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
	    	    
	    public void remove() {									
	    	
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
