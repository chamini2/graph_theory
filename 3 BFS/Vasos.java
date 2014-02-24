import java.util.TreeSet;


public class Vasos {

	private Nodo origen;
	private int[] capacidad;
	private int dest;
	private int many;
	
	/**
	 * Constructor de la clase Vasos
	 */
	public Vasos(Nodo ori, int destino, int[] cap) {
		
		this.origen = (Nodo) ori.clone();
		this.dest = destino;
		this.many = ori.obtenerVasos().length;
		this.capacidad = cap;
	}
	
	/**
	 * Este metodo busca el menor numero de movimientos para 
	 * llegar a que un vaso tenga la cantida de agua objetivo
	 */
	public int hacer() {
		
		TreeSet<Nodo> visitados = new TreeSet<Nodo>();
		Lista<Nodo> cola = new Lista<Nodo>();
		Nodo nodo, next;
		
		//Revisa si el primer tiene ya el destino
		if (found(origen.obtenerVasos()))
			return 0;
		
		cola.agregar_final(this.origen);			//Encolo el primer elemento
		visitados.add(this.origen);					//y lo marco visitado
		
		//Mientras hayan elementos en la cola
		while (cola.obtener_tam() > 0) {
			
			nodo = cola.obtener_elemento(0);		//Desencolo elemento actual
			cola.eliminar_inicio();
			
			//Recorre los vasos, vaso de origen
			for (int i = 0; i < this.many; i++) {
				
				//Si no hay agua en el vaso de origen salta la iteracion
				if (nodo.obtenerVasos()[i] == 0)
					continue;
				
				//Recorre los vasos, vaso destino
				for (int k = 0; k < this.many; k++) {
					
					//Si el vaso no se creo continua la iteracion
					if ((next = newVasos(i, k, nodo.obtenerVasos())) == null)
						continue;
					
					next.asignarId(nodo.obtenerId() + 1);			//Despues de cuantas movidas lo consigue
					
					//Verifica que no haya llegado a este estado anteriormente
					if (!visitados.contains(next)) {
						
						//Si este estado tiene la cantidad de agua objetivo, devuelvo cuantas movidas hizo
						if (found(next.obtenerVasos()))
							return next.obtenerId();
						
						cola.agregar_final(next);					//Encolar el elemento
						visitados.add(next);						//y lo marco como visitado
					}
					
					
				}
			}
		}
		
		return -1;													//Si no consiguio un camino
	}
	
	/**
	 * Este metodo crea un vaso sucesor con los datos introducidos
	 */
	public Nodo newVasos(int i, int k, int[] agua) {
		
		int[] vichiere;
		int total = 0;
		
		//Si no modificabamos los vasos al vertirlo/vaciarlo
		if (agua[i] == 0)
			return null;
		
		vichiere = (int[]) agua.clone();
		
		//Si las posiciones son iguales, vacio el vaso
		if (i == k) {
			
			vichiere[i] = 0;
			
			//Cualculo cuanta agua total tienen los vasos ahora
			for (int p = 0; p < vichiere.length; p++)
				total += vichiere[p];
			
			//Si el nuevo total de agua no alcanza para el objetivo
			if (total < dest)
				return null;
			
		//Si son diferentes vierto de vaso i a  vaso k
		} else {
			
			//Si puedo vertir el vaso origen entero en el destino
			if (this.capacidad[k] >= vichiere[k] + vichiere[i]) {
				vichiere[k] += vichiere[i];
				vichiere[i] = 0;
				
			//Si se llena el vaso destino antes de vaciarse el origen
			} else {
				int falta = this.capacidad[k] - vichiere[k];
				vichiere[k] += falta;
				vichiere[i] -= falta;
			}
		}
		
		//Si los vasos son iguales (No se modificaron) no lo creo
		for (int p = 0; p < vichiere.length; p++)
    		if (vichiere[i] != agua[i])
    			return new Nodo(0, vichiere);
		
		return null;
	}
	
	/**
	 * Revisa si algun vaso tiene la cantidad de agua objetivo
	 */
	public boolean found(int[] agua) {
		
		//Revisa vaso por vaso
		for (int i = 0; i < many; i++)
			//Si consigue alguno que tenga la cantidad destino devuelve true
			if (agua[i] == this.dest)
				return true;
		
		return false;
	}
}
