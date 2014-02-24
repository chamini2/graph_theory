import java.util.ListIterator;

/**
 *		Laboratorio de Algoritmos y Estructuras III
 *		Proyecto 2
 *		Integrantes: 	Alberto Cols, 09-10177
 *							Matteo Ferrando, 09-10285
 *		Clase: Nodo.java		
 *		Descripcion: Implementacion del Arreglo de una Secuencia 	
 *		15/02/2012
 */
public class Arreglo<T> implements Secuencia<T> {
	
	private Object[] array;
	private	int	added;
	
	/**
	 * Constructor de la clase Arreglo<T>
	 */
	public Arreglo(){
		this.array = new Object[1];
		this.added = 0;
	}
	
	/**
	 * Verifica que el Arreglo<T> sea de tama–o suficiente para
	 * los nodos que queremos agregar, sino, lo duplica
	 */
	public void fitsArreglo(int cantidad) {
		int tot = cantidad + this.added; // Calcula cuantos nodos tendra en total
		int size = this.array.length;	 // Inicializa size en el tama–o actual
		
		// Mientras hayan mas nodos totales que capacidad
		while (tot > size)				 
			size *= 2;					 // Calcula cual es el tama–o necesario
		
		// Entra si se necesita aumentar el tama–o
		if (size > this.array.length)	
			this.expandir(size);
	}
	
	/**
	 * Agrega el elemento al final del arreglo
	 */
	@Override
	public void agregar_final(T elemento) {
		this.array[this.added++] = elemento;
	}

	/**
	 * Elimina el elemento al final del arreglo
	 */
	@Override
	public void eliminar_final() {
		this.array[--this.added] = null;
	}

	/**
	 * Devuelve el elemento en la posicion index,
	 * siempre que esta posicion exista
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T obtener_elemento(int index) {
		
		try {
			// Si la posicion pedida no existe
			if ((index < 0) || (index >= this.added))
				throw new IllegalArgumentException();
			
			return (T) this.array[index];			  // Retorna el elemento en la posicion
    	} catch (IllegalArgumentException e) {
    		System.out.println("Posicion invalida. \n");
    		return null;
    	}
	}

	/**
	 * Expande el Arreglo<T> al tama–o indicado
	 */
	public void expandir(int size) {
		
		Object[] clon = new Object[size];		//Nuevo arreglo a usar
		
		// Recorre el arreglo anterior
		for (int count = 0; count < this.added; count++)
			clon[count] = this.array[count];	//Copia datos del arreglo anterior
		
		this.array = clon;
	}	

	/**
	 * Metodo no implementado
	 */
	@Override
	public ListIterator<T> iterador() {
		
		System.out.println("Metodo no implementado. \n");
		return null;
	}
}
