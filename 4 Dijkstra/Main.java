/**
 * Laboratorio de Algoritmos y Estructuras III
 * Proyecto 4
 * Integrantes: Alberto Cols, 09-10177
 * 				Matteo Ferrando, 09-10285
 * Clase: Main.java
 * Descripcion: Clase principal del proyecto.
 */
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {

	/**
	 * Es el metodo que se llama al correr el programa.
	 * Este hace un dijkstra sobre el grafo introducido para saber
	 * los costos de llegada a cada nodo del grafo
	 */
	public static void main(String[] args) {
		
		GrafoNoDirigido craks;
		Scanner entrada;
		int m, T, t;
		int nod, lch, cst;
		//ColaPrioridadBinaria<Persona> gente;
		ColaPrioridadFibonacci<Persona> gente;
		Persona sed;
		
		//si no se introdujo el nombre del archivo
		if (args.length == 0) {
			System.out.println("Introduzca el nombre de un archivo.");
			return;
		}
		
		//si el archivo especificado no existe
		try {
			entrada = new Scanner(new File(args[0]));
		} catch (FileNotFoundException e) {
			System.out.println("Archivo no encontrado.");
			return;
		}
		
		craks = new GrafoNoDirigido(entrada.nextInt());			//Grafo con (#intersecciones) nodos
		m = entrada.nextInt();									//Numero de lados(#calles) del grafo
		T = entrada.nextInt();									//Numero de litros de leche del lechero
		
		//Lee cada calle y la agrega al grafo
		for (int i = 0; i < m; i++)
			craks.agregarLado(new Lado(entrada.nextInt(), entrada.nextInt(), entrada.nextInt()));
		
		t = entrada.nextInt();				//Numero de personas
		
		Leche.dijkstra(craks);				//Corremos un Dijkstra en el grafo
		
		//gente = new ColaPrioridadBinaria<Persona>();
		gente = new ColaPrioridadFibonacci<Persona>();
		
		//Lee la ubicacion de la persona, pide tal nodo
		//Luego en ese nodo agrega a la persona leyendo cuanta leche desea
		for (int i = 0; i < t; i++) {
			nod = entrada.nextInt();
			//si la persona no quiere leche
			if ((lch = entrada.nextInt()) == 0)
				continue;
			
			if ((cst = craks.obtenerElemento(nod).obtener_T().obtenerCosto()) == Integer.MAX_VALUE)
				continue;
			
			//crea una nueva Persona, con los datos de entrada y la encola en
			//la cola de prioridad con el costo de llegada a la 
			//interseccion donde se ubica en la cidudad
			sed = new Persona(i, lch);
			sed.asignarCosto(cst);
			gente.add(sed);
		}
		
		System.out.print(dameLeche(gente, T));
	}
	
	public static String dameLeche(ColaPrioridadFibonacci<Persona> gente, int milk) {
		
		StringBuilder string = new StringBuilder();
		Persona sed;
		
		//mientras la cola de prioridad de personas no este vacia
		while (!gente.isEmpty()) {
			
			sed = gente.remove();		//desencola
			
			//Si habia menos leche de la que queria
			if (milk < sed.obtenerLeche()) {
				//Si habia un poco de leche para dar
				if (milk > 0) {
					string.append(sed.obtenerIndice()).append("\n");
					milk = 0;
					//Si se quedo la cola vacia, no necesita el YANO
					if (gente.isEmpty())
						break;
				}
				string.append("YANO\n");
				break;
			}
			
			//Resta la leche que la persona queria
			milk -= sed.obtenerLeche();
			string.append(sed.obtenerIndice()).append("\n");
		}
		
		return string.toString();
	}
}
