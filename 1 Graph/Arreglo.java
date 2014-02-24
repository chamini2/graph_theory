import java.util.ListIterator;

public class Arreglo<T> implements Secuencia<T> {
	
	private Object[] array;
	private	int	added;
	
	public Arreglo(){
		this.array = new Object[1];
		this.added = 0;
	}
	
	public void fitsArreglo(int cantidad) {
		int tot = cantidad + this.added;
		int size = this.array.length;
		
		while (tot > size)
			size *= 2;
		
		if (size > this.array.length)
			this.expandir(size);
	}
	
	@Override
	public void agregar_final(T elemento) {
		this.array[this.added++] = elemento;
	}

	@Override
	public void eliminar_final() {
		this.array[--this.added] = null;
	}

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
	
	public void expandir(int size) {
		
		Object[] clon = new Object[size];
		
		for (int count = 0; count < this.added; count++)
			clon[count] = this.array[count];
		
		this.array = clon;
	}	
	
	@Override
	public ListIterator<T> iterador() {
		
		System.out.println("Metodo no implementado. \n");
		return null;
	}
}
