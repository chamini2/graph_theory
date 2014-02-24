import java.util.ListIterator;
import java.lang.Math;

/**
 *		Laboratorio de Algoritmos y Estructuras III
 *		Proyecto 2
 *		Integrantes: 	Alberto Cols, 09-10177
 *						Matteo Ferrando, 09-10285
 *		Clase: Tarjan.java
 *		Descripcion: 	Implementacion del algoritmo de Tarjan que consigue las
 *							componentes fuertemente conexas de un grafo.		
 *		15/02/2012
 */
public class Tarjan {

	private int count;
	private Lista<Integer> pilaNodos;
	private int[] componentes;
	private int[] indice;
	private int[] menor;
	private boolean[] enPila;
	private GrafoDirigido grafo;
	
	/**
	 * Constructor de la clase Tarjan.
	 * Automaticamente consigue las componentes fuertemente conexas.
	 */
	public Tarjan(GrafoDirigido graph) {
		this.count = 0;
		this.pilaNodos = new Lista<Integer>();
		this.componentes = new int[graph.obtenerNumeroNodos()];
		this.indice = new int[graph.obtenerNumeroNodos()];
		this.menor = new int[graph.obtenerNumeroNodos()];
		this.enPila = new boolean[graph.obtenerNumeroNodos()];
		this.grafo = graph;
		
		// Recorre todos los nodos del grafo
		for (int nodo = 0; nodo < this.grafo.obtenerNumeroNodos(); nodo++)
			if (this.indice[nodo] == 0)			// Para cada nodo no visitado
				tarjanIn(nodo);			// Buscar sus componenetes conexas
	}
	
	/**
	 * Indica la compenente del nodo introducido y sus sucesores por recurrencia.
	 */
	public void tarjanIn(int nodo) {
		
		// Crea el iterador para recorrer los sucesores del nodo introducido
		ListIterator<Lado> sucesores = this.grafo.sucesores(nodo).iterador();
		int suc;
		
		this.indice[nodo] = this.count;
		this.menor[nodo] = this.count++;			//Incremento count
		this.pilaNodos.agregar_final(nodo);
		this.enPila[nodo] = true;
		
		// Mientras hayan sucesores por revisar
		while (sucesores.hasNext()) {
			suc = sucesores.next().obtenerDestino();
			
			// Si el nodo no ha sido visitado anteriormente
			if (this.indice[suc] == 0) {
				tarjanIn(suc);		//Llama a la recursion del metodo
				this.menor[nodo] = Math.min(this.menor[nodo], this.menor[suc]);
				
			// Si el nodo ha sido visitado en esta corrida
			} else if (this.enPila[suc])
				this.menor[nodo] = Math.min(this.menor[nodo], this.indice[suc]);
		}
		
		if (this.menor[nodo] == this.indice[nodo]) {
			int comp = this.menor[nodo];
			
			// Desempila elementos de la pila y los coloca como parte de la
			// misma componente, hasta que desempile el nodo que llamo al metodo
			do {
				suc = this.pilaNodos.obtener_elemento(this.pilaNodos.obtener_tam() - 1);
				this.pilaNodos.eliminar_final();
				this.enPila[suc] = false;
				
				this.componentes[suc] = comp;
			} while (nodo != suc);	
		}
	}
	
	/**
	 * Retorna las componentes de la clase Tarjan
	 */
	public int[] obtenerComponentes() {
		return this.componentes;
	}

}
