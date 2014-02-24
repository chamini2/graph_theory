/**
 * Laboratorio de Algoritmos y Estructuras III
 * Proyecto 5
 * Integrantes: Alberto Cols, 09-10177
 * 				Matteo Ferrando, 09-10285
 * Clase: Main.java
 * Descripcion: Clase principal del proyecto.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

	/**
	 * Metodo que se llama al correr el programa.
	 * Se maneja lectura y el algoritmo de Kruskal que se usa para
	 * obtener la respuesta deseada.
	 */
	public static void main(String[] args) {
		
		Scanner entrada;
		ConjuntosDisjuntos comp;
		int partes, venas, total = 0;
		int ini, des, largo;
		
		try {
			entrada = new Scanner(new File(args[0]));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}
		
		partes = entrada.nextInt();		//Numero de componentes conexas del grafo
		venas = entrada.nextInt();		//Numero de Lados del grafo
		comp = new ConjuntosDisjuntos(partes);	//Una estructura para manejar
												//las componentes conexas.
		
		//Lee todos los lados, mientras no haya una sola componente conexa
		for (int i = 0; (i < venas) && (partes != 1); i++) {
			ini = entrada.nextInt();	//Nodo inicio del lado
			des = entrada.nextInt();	//Nodo destino del lado
			largo = entrada.nextInt();	//Costo del lado
			
			//Si los nodos son de la misma componente conexa, no hagas nada
			if (comp.find(ini) == comp.find(des))
				continue;
			
			//Resta el numero de componentes conexas, unelas y actualiza el total
			partes--;
			comp.union(ini, des);
			total += largo;
		}
		
		//Si hay una sola componente conexa
		if (partes == 1)
			System.out.println(total);
		
		//Si hay mas de una componente conexa
		else
			System.out.println("MOTHER OF EMO!");
	}
}
