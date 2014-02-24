/**
 * Laboratorio de Algoritmos y Estructuras III
 * Proyecto 4
 * Integrantes: Alberto Cols, 09-10177
 * 				Matteo Ferrando, 09-10285
 * Clase: ColaPrioridadFibonacci.java
 * Descripcion: Clase que implementa un heap Fibonacci.
 */
 
public class ColaPrioridadFibonacci<T extends Comparable<T>> {
	
	private Box min;
	private int size;
	
	/**
	 * Elementos de la Cola
	 */
	protected class Box {
		
		protected Box next, prev, up, child;
		protected int degree;
		protected T info;
		
		/**
		 * Constructor de los elementos de la Cola
		 */
		private Box(T info) {
			
			//Inicializa los elementos para una lista circular
			this.next = this.prev = this;
			this.child = this.up = null;
			this.degree = 0;
			this.info = info;
		}
	}
	
	/**
	 * Constructor de la clase
	 */
	public ColaPrioridadFibonacci() {
		
		this.min = null;
		this.size = 0;
	}
	
	/**
	 * Agrega un elemento justo antes del min
	 */
	public void add(T elem) {
		
		Box in = new Box(elem);
		
		//Si es el primer elemento
		if (this.min == null)
			this.min = in;
		else {
			
			//Agrega in justo antes de min
			in.next = this.min;
			in.prev = this.min.prev;
			this.min.prev = in;
			in.prev.next = in;
		}
		
		//Revisa si in es el nuevo min
		if (this.min.info.compareTo(elem) == 1)
			this.min = in;
		
		this.size++;
	}
	
	/**
	 * Saca min del heap, re-ordena el heap y devuelve la informacion de min
	 */
	public T remove() {
		
		Box a = this.min, b, temp;
		int mSize;
		
		//Si no hay elementos en el heap
		if (a == null)
			return null;
		
		b = a.child;
		
		//Para cada hijo de a
		for (mSize = a.degree; mSize > 0; mSize--) {
			temp = b.next;
			
			//Saca b de la lista de hijos
			b.prev.next = b.next;
			b.next.prev = b.prev;
			
			//Agrega b en la lista raiz
			b.next = this.min;
			b.prev = this.min.prev;
			this.min.prev = b;
			b.prev.next = b;
			
			//Asigna null al padre de b
			b.up = null;
			b = temp;
		}
		
		//Saca a de la lista raiz
		a.prev.next = a.next;
		a.next.prev = a.prev;
		
		//Si a (this.min) es el unico elemento del heap
		if (a == a.next) 
			this.min = null;
		else {
			this.min = a.next;
			//Re ordenomas el heap
			this.fixHeap();
		}
		
		this.size--;
		return a.info;
	}
	
	/**
	 * Devuelve un booleano que indica si la cola esta vacia (true) o no (false)
	 */
	public boolean isEmpty() {
		
		return this.min == null;
	}

	/**
	 * Re ordena el heap para que cumpla las condiciones del heap despues de eliminar min
	 */
	private void fixHeap() {
		//Asigna a aSize el maximo degree que puede haber en el heap 
		//para este numero de elementos, aSize = log2(size)
		int grado, trees;
		int aSize = (int) Math.floor((Math.log(this.size) / Math.log(2))) + 1;
		Arreglo<Box> array = new Arreglo<Box>(aSize);
		Box caja, next, cubo, temp;
		
		//Inicializa el arreglo
		for (int i = 0; i < aSize ; i++)
			array.agregar_final(null);
		
		trees = 0;
		caja = this.min;
		
		//Cuenta el numero de arboles en la lista raiz en la variable trees
		if (caja != null) {
			do {
				trees++;
				caja = caja.next;
			} while (caja != this.min);
		}
		
		//Itera por todos las cajas de la lista raiz
		while (trees > 0) {
			//Saca el degree de la caja actual
			grado = caja.degree;
			//Almacena en next cual debe ser el siguiente elemento a revisar
			next = caja.next;
			
			//Hasta que consiga un break
			while (true) {
				
				//Coloca en cubo algun elemento del mismo grado que la caja actual
				cubo = array.obtener_elemento(grado);
				
				//Cuando no hay del mismo grado
				if (cubo == null)
					break;
				
				//Busca cual sera el padre de cual
				if (caja.info.compareTo(cubo.info) == 1) {
					temp = caja;
					caja = cubo;
					cubo = temp;
				}
				
				//Vuelve a caja el padre de cubo
				this.makeChild(caja, cubo);
				
				//Coloca en la posicion referente a elementos
				//de ese grado null, porque este ya no esta en la
				//lista raiz del heap
				array.modificar_elemento(grado++, null);
			}
			
			//Cuando no consiguio mas elmentos, indica que este
			//elemento esta disponible para alguno siguiente
			array.modificar_elemento(grado, caja);
			
			caja = next;
			trees--;
		}
		
		//Le coloca a min null perdiendo la referencia a la lista
		this.min = null;
		
		//Recorre todo array
		for (trees = 0; trees < aSize; trees++) {
			
			//Saca el elemento del grado referido por trees
			caja = array.obtener_elemento(trees);
			
			//Si no hay elementos del grado referido por trees
			if (caja == null)
				continue;
			
			//Si es el primer elmento en agrgar, apunta min a este y ya
			if (this.min == null) {
				this.min = caja;
				continue;
			}
			
			//Saca a caja de la lista raiz
			caja.next.prev = caja.prev;
			caja.prev.next = caja.next;
			
			//Y coloca a caja justo antes del min
			caja.next = this.min;
			caja.prev = this.min.prev;
			caja.prev.next = caja;
			this.min.prev = caja;
			
			//Verifica si caja es el nuevo min
			if (caja.info.compareTo(this.min.info) == -1)
				this.min = caja;
		}
	}
	
	/**
	 * Saca a down de la raiz del heap y lo vuelve hijo de up
	 */
	private void makeChild(Box up, Box down) {
		
		//Saca a down de la lista raiz
		down.prev.next = down.next;
		down.next.prev = down.prev;
		
		//Pone a up como padre de down
		down.up = up;
		
		//Si up no tenia hijos previamente
		if (up.child == null) {
			up.child = down;
			down.prev = down;
			down.next = down;
		//Si up tenia al menos un hijo
		} else {
			down.next = up.child;
			down.prev = up.child.prev;
			up.child.prev.next = down;
			up.child.prev = down;
			up.child = down;
		}
		
		//Incrementa el grado de up
		up.degree++;
	}
	
	/**
	 * Devuelve la informacion de min sin modificar el heap
	 */
	public T peek() {
		
		return this.min.info;
	}
}
