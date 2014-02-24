/**
 * Laboratorio de Algoritmos y Estructuras III
 * Proyecto 4
 * Integrantes: Alberto Cols, 09-10177
 * 				Matteo Ferrando, 09-10285
 * Clase: ColaPrioridadFibonacci.java
 * Descripcion: Clase que implementa un heap Binario.
 */
 
public class ColaPrioridadBinaria<T extends Comparable<T>> {

	protected Arreglo<T> array;
	protected int size;
	
	/**
	 * Constructor de la clase
	 */
	public ColaPrioridadBinaria() {
		
		this.array = new Arreglo<T>();
		this.size = 0;
	}
	
	/**
	 * Constructor de la clase, que especifica de que tamano
	 * sera el arreglo a usarse de antemano
	 */
	public ColaPrioridadBinaria(int tam) {
		
		this.array = new Arreglo<T>(tam);
		this.size = 0;
	}
	
	/**
	 * Agrega un elemento organizandolo
	 */
	public void add(T elem) {
		
		//Verifica que quepa el nuevo elemento, si no, agranda el arreglo
		this.array.fitsArreglo(1);
		//Agrega el elemento a el arreglo
		this.array.agregar_final(elem);
		this.size++;
		//Y re ordena el heap
		this.upOrden(this.size - 1);
	}
	
	/**
	 * Este metodo se usa cuando se acaba de agregar un elemento
	 * al heap, para que lo coloque en una posicion donde
	 * el heap siga cumpliendo sus propiedades
	 */
	private void upOrden(int h) {
		
		T hij, pad;
		int p = (h - 1) / 2;						//Posicion en el arreglo del padre
		
		hij = this.array.obtener_elemento(h);		//El elemento
		pad = this.array.obtener_elemento(p);		//El padre del elemento
		
		//Si el padre es menor ya esta bien ordenado el arbol
		if (pad.compareTo(hij) == 1) {
			this.array.change(h, p);
			//Si no es el elemento raiz, verifica las propiedades
			if (p > 0)
				upOrden(p);
		}
	}

	/**
	 * Este metodo se usa cuando se acaba de eliminar el minimo elemento
	 * del heap, para asegurar que el heap siga cumpliendo sus propiedades
	 */
	private void downOrden(int p) {
		
		T izq, der;
		int hl, hr, min;
		
		hl = (p * 2) + 1;							//Posicion en el arreglo del hijo izquierdo
		hr = (p * 2) + 2;							//Posicion en el arreglo del hijo derecho
		
		//Verifica que tenga hijo izquierdo
		if (hl >= this.size)
			return;
		
		//Verfica que tenga hijo derecho
		if (hr >= this.size)
			min = hl;
		else {
			
			//Obtiene los elementos en la posicion de los hijos der e izq
			izq = this.array.obtener_elemento(hl);
			der = this.array.obtener_elemento(hr);
			
			//Colocalmoes en min la posicion del de mayor prioridad
			if (izq.compareTo(der) == -1)
				min = hl;
			else
				min = hr;
		}
		
		//Si ya se cumplen las propiedades
		if (this.array.obtener_elemento(p).compareTo
				(this.array.obtener_elemento(min)) == -1)
			return;
		
		this.array.change(p, min);
		downOrden(min);
	}
	
	/**
	 * Elimina el elemento con mayor prioridad y organiza el heap
	 * Devuelve el elemento eliminado
	 */
	public T remove() {
		
		T result;
		
		//Si el heap esta vacio
		if (this.size == 0)
			return null;
		
		//Coloca en result el elemento a devolver
		result = this.array.obtener_elemento(0);
		//Intercambia el ultimo agregado con el minimo (a eliminar)
		this.array.change(0, this.size - 1);
		this.array.eliminar_final();
		this.size--;
		//Re ordena el heap
		downOrden(0);
		
		return result;
	}

	/**
	 * Devuelve el elemento con mayor prioridad de la cola
	 * sin modificar la cola
	 */
	public T peek() {
		
		//Si el heap esta vacio
		if (this.size == 0)
			return null;
		
		return this.array.obtener_elemento(0);
	}
	
	public boolean verifyHeap() {
		
		int i = 0, l, r;
		T p, z, d;

		if (this.isEmpty())
			return true;
		
		while (true) {
			
			l = (i * 2) + 1;
			r = (i * 2) + 2;
			if (l >= this.size)
				break;
			
			p = this.array.obtener_elemento(i);
			z = this.array.obtener_elemento(l);
			
			if (p.compareTo(z) == 1)
				return false;
			
			if (r >= this.size)
				break;
			
			d = this.array.obtener_elemento(r);
			
			if (p.compareTo(d) == 1)
				return false;
			
			i++;
		}
		
		return true;
	}
	
	/**
	 * Revisa si la cola tiene elementos o esta vacia
	 * true = la cola esta vacia
	 * false = la cola tiene elementos
	 */
	public boolean isEmpty() {
		
		return (this.size == 0);
	}
}

