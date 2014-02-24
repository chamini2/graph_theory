/**
 * Laboratorio de Algoritmos y Estructuras III
 * Proyecto 4
 * Integrantes: Alberto Cols, 09-10177
 * 				Matteo Ferrando, 09-10285
 * Clase: Arreglo.java
 * Descripcion: Implementacion de la interfaz Secuencia<T> como Arreglos.
 */
import java.util.ListIterator;

public class Arreglo<T> implements Secuencia<T> {
	
	private Object[] array;
	private	int	added;
	
	/**
	 * Constructor de la clase con tamano 1
	 */
	public Arreglo(){
		this.array = new Object[1];
		this.added = 0;
	}
	
	/**
	 * Constructor de la clase con tamano inicial
	 */
	public Arreglo(int size) {
		this.array = new Object[size];
		this.added = 0;
	}

	/**
	 * Incrementa el tamano del arreglo si queremos agregar mas elementos de los que puede almacenar
	 */
	public void fitsArreglo(int cantidad) {
		int tot = cantidad + this.added;
		int size = this.array.length;
		
		while (tot > size)
			size *= 2;
		
		if (size > this.array.length)
			this.expandir(size);
	}
	
	/**
	 * Agrega un elemento al final de los elementos agregados.
	 */
	@Override
	public void agregar_final(T elemento) {
		this.array[this.added++] = elemento;
	}

	/**
	 * Elimina el ultimo elemento agregado
	 */
	@Override
	public void eliminar_final() {
		this.array[--this.added] = null;
	}
	
	/**
	 * Devuelve el elemento en la posicion index.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T obtener_elemento(int index) {
		
		try {
			if ((index < 0) || (index >= this.added)) 
				throw new IllegalArgumentException();
			
			return (T) this.array[index];				
    	} catch (IllegalArgumentException e) {
    		System.out.println("Posicion invalida. \n");
    		return null;
    	}
	}
	
	/**
	 * Expande el arreglo.
	 */
	public void expandir(int size) {
		
		Object[] clon = new Object[size];
		
		for (int count = 0; count < this.added; count++)
			clon[count] = this.array[count];
		
		this.array = clon;
	}
	
	/**
	 * Intercambia los elementos en las posiciones i y k.
	 */
	@SuppressWarnings("unchecked")
	public void change(int i, int k) {
		
		T temp = (T) this.array[i];
		this.array[i] = this.array[k];
		this.array[k] = temp;
	}
	
	/**
	 * Asigna valor a la posicion index.
	 */
	public void modificar_elemento(int index, T valor) {
		
		try {
			if ((index < 0) || (index >= this.added)) 
				throw new IllegalArgumentException();
			
			this.array[index] = valor;
    	} catch (IllegalArgumentException e){
    		System.out.println("Posicion invalida. \n");
    	}
	}
	
	/**
	 * Crea un string con la informacion del arreglo.
	 */
	public String toString() {
		
		StringBuilder string = new StringBuilder(this.added);
		string.append("<");
		
		for (int i = 0; i < this.added; i++)
			string.append(this.array[i].toString()).append(" | ");
		
		string.append(">\n");
		return string.toString();
	}
	
	@Override
	public ListIterator<T> iterador() {
		
		System.out.println("Metodo no implementado. \n");
		return null;
	}
}
