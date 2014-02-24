import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *		Laboratorio de Algoritmos y Estructuras III
 *		Proyecto 2
 *		Integrantes: 	Alberto Cols, 09-10177
 *						Matteo Ferrando, 09-10285
 *		Clase: Programa.java
 *		Descripcion: Clase principal del Proyecto 2 	
 *		15/02/2012
 */
public class Programa {

	/**
	 * Devuelve un booleano indicando si la expresion logica
	 * es satisfacible o no
	 */
	public static boolean satisfacible(int[] componentes) {
		
		// Para todas las variables de la expresion logica
		for (int i = 0; i < componentes.length/2; i++)
			// Compara los negados, si son iguales
			if (componentes[i] == componentes[i + componentes.length/2])
				return false;		//Devuelve FALSE porque son alcanzables una a
									//la otra, ya que son de la misma componente.
		
		return true;				//Si no consiguio ninguna variable y su negado
									//en la misma componente
	}

	/**
	 * Recibe una la posicion de una variable y devuelve su negado
	 */
	public static int mulMenosUno(int val, int num) {
		
		//Si la variable es positiva
		if (val < num)	//cuando val es positivo
			val += num;
		
		//Si la variable es negativa
		else 			//cuando val es negativo
			val -= num;
		
		return val;
	}

	/**
	 * Recibe una variable y devuelve su posicion en el arreglo
	 */
	public static int posArreglo(int var, int num) {
		
		//La variable va en la segunda mitad del arreglo
		if (var < 0)
			var = (var)*(-1) + (num - 1);
		//La variable va en la primera mitad del arreglo
		else
			var--;
		
		return var;
	}
	
	/**
	 * Recibe una la poicion de una variable y devuelve su valor real
	 */
	public static int valVariable(int pos, int num) {
		
		//Si la variable es positiva
		if (pos < num)
			pos++;
		//Si la variable es negativa
		else
			pos = (pos)*(-1) + (num - 1);
		
		return pos;
	}
	
	public static void main(String[] args) {
		
		GrafoDirigido grafo;
		Scanner entrada = null;
		Lado side;
		int num, var1, var2, clau;
		Tarjan tarjan;
		
		try {												
			entrada = new Scanner(new File(args[0]));
		} catch (FileNotFoundException e) {
			System.out.println("\nArchivo no encontrado.\n");
			e.printStackTrace();
		}
		
		num = entrada.nextInt();					//Numero de variables
		grafo = new GrafoDirigido(num*2);			//Grafo con nodos = 2*numero de variables
		
		//Lee cuantas clausulas hay y busca esa cantidad de ceros
		for (clau = entrada.nextInt(); clau > 0; clau--) {
			var1 = entrada.nextInt();				//Primera variable de la clausula
			
			//Si es una clausula vacia
			if (var1 == 0){
				System.out.println("No");			//Automaticamente es insatisfacible
				return;								//Termina el programa
				
			//Si es una clausula de una variable o mas
			} else {
				var2 = entrada.nextInt();			//Segunda variable de la clausula
				
				//Si es una clausula de una sola variable
				if (var2 == 0) {
					
					var1 = posArreglo(var1, num);	//La hago de dos variables,
					var2 = var1;					//por idempotencia de la disjuncion
				
				//Si es una clausula de dos variables
				} else {
					
					var1 = posArreglo(var1, num);	//Busco la posicion para las variables
					var2 = posArreglo(var2, num);	//y agrego la implicacion !var1 => var2
					side = new Lado(mulMenosUno(var1, num), var2);
					grafo.agregarLado(side);
					
					entrada.nextInt(); //Saltar el cero del archivo
				}
				
				side = new Lado(mulMenosUno(var2, num), var1);
				grafo.agregarLado(side);			//Agrego la implicacion !var2 => var1
			}
		}
		
		tarjan = new Tarjan(grafo);					//Consigo las componentes conexas del grafo
		
		if (satisfacible(tarjan.obtenerComponentes()))
			System.out.println("Si");
		else
			System.out.println("No");
	}

}
