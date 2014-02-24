/**
 * Laboratorio de Algoritmos y Estructuras III
 * Proyecto 4
 * Integrantes: Alberto Cols, 09-10177
 * 				Matteo Ferrando, 09-10285
 * Clase: Leche.java
 * Descripcion: Clase con el metodo que calcula el costo minimo de llegada desde el nodo 0 a todos los demas.
 */
import java.util.ListIterator;

public class Leche {
	
	/**
	 * Implementacion del algoritmo Dijkstra con nodo inicial 0.
	 */
	public static void dijkstra(GrafoNoDirigido city) {
		
		//ColaPrioridadBinaria<ParNodo> cola = new ColaPrioridadBinaria<ParNodo>(city.obtenerNumeroNodos());
		ColaPrioridadFibonacci<ParNodo> cola = new ColaPrioridadFibonacci<ParNodo>();
		boolean[] visit = new boolean[city.obtenerNumeroNodos()];	//inicializado en false
		ParNodo act;
		Nodo next;
		ListIterator<Lado> suc;
		Lado side;
		int costo, nodos = city.obtenerNumeroNodos();
		
		city.obtenerElemento(0).obtener_T().asignarCosto(0);		//cambia el costo del incial a 0
		act = new ParNodo(0, 0);
		cola.add(act);												//agrega el primer nodo
	
		//Mientras queden elementos en la cola, y no haya visitado todos los nodos
		while (!cola.isEmpty() && nodos > 0) {
			act = cola.remove();									//desencola
			//verifica si el nodo ya fue visitado
			if (visit[act.obtenerId()])
				continue;
			
			nodos--;												//esta visitando un nodo mas
			visit[act.obtenerId()] = true;							//lo marca visitado
			suc = city.sucesores(act.obtenerId()).iterador();		//y obtiene sus sucesores
			
			//itera por la lista de lados del nodo
			while (suc.hasNext()) {
				
				//obtiene un lado, y saca su nodo destino
				side = suc.next();
				next = city.obtenerElemento(side.obtenerDestino()).obtener_T();
				
				//si el sucesor ya estaba visitado
				if (visit[next.obtenerId()])
					continue;
				
				//calcula el costo de llegada por el nodo actual
				costo = act.obtenerCosto() + side.obtenerCosto();
				
				//si consiguio un camino mas corto
				if (costo < next.obtenerCosto()) {
					next.asignarCosto(costo);
					cola.add(new ParNodo(next.obtenerId(), next.obtenerCosto()));
				}
			}
		}
	}
}
