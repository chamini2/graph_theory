import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		
		//Para poder correr varios archivos uno tras otro
		for (int i = 0; i < args.length; i++) {
			
			File file = new File(args[i]);
			Scanner entrada;
			try {
				entrada = new Scanner(file);
			} catch (FileNotFoundException e) {
				System.out.println("\nParametro de entrada no existe.\n");
				return;
			}
			
			Vasos juego;
			boolean bool, par;
			int in, dest, max;
			int[] vasos;					//Para medir capacidad
			int[] agua;						//Para medir cuanta agua tienen al principio
			
			in = entrada.nextInt();			//El numero de vasos para el primer caso
					
			while ((0 < in) && (in <= 5)) {
				bool = true;
				par = true;					//Si el agua pedida es impar, y los vasos y la cantidad son de volumenes pares, es imposible llegar
				vasos = new int[in];
				agua = new int[in];
				max = 0;
				
				//Capacidad de los vasos
				for (int count = 0; count < in; count++) {
					vasos[count] = entrada.nextInt();
					
					//Revisa si los vasos tienen capacidades de agua impares
					if (vasos[count] % 2 != 0)
						par = false;
						
					max = Math.max(max, vasos[count]);		//Mantiene la capacidad mas alta de los vasos
					
					//Revisa las condiciones
					if ((vasos[count] < 1) || (100 < vasos[count]))
						bool = false;
				}
	
				//Primer nodo
				for (int count = 0; count < in; count++) {
					agua[count] = entrada.nextInt();
					
					//Revisa si los vasos comienzan con cantidades de agua impares
					if (agua[count] % 2 !=0)
						par = false;
					
					//Revisa que cumpla las condiciones
					if ((agua[count] < 0) || (vasos[count] < agua[count]))
						bool = false;
				}
				
				//Lee la cantidad de agua objetivo, revisa que cumpla las condiciones
				if (((dest = entrada.nextInt()) < 0) || (100 < dest))
					bool = false;
	
				in = 0;
				
				//Calcula cuanta agua total hay en los vasos
				for (int count = 0; count < agua.length; count++)
					in += agua[count];
				
				//Si la cantidad de agua objetivo es mas de la que hay
				if (dest > in)
					bool = false;
	
				//Si la cantidad de agua objetivo es mas que lo que cabe en el vaso mas grande
				if (dest > max)
					bool = false;
				
				//Revisa si la cantidad de agua objetivo es impar
				par = par && (dest % 2 != 0);
				
				//Si cumplio todas las condiciones
				if (bool && !par) {
					
					juego = new Vasos(new Nodo(0, agua), dest, vasos);		//Cargo en la variabe juego los datos
					
					//Busca el menor numero de pasos a la cantidad de agua objetivo, si no lo consigue devuelve -1
					if	((in = juego.hacer()) != -1)
						System.out.println(in);
					
					//Si no consiguio un camino
					else
						System.out.println("No");
						
				//Si no cumplio con las condiciones basicas
				} else
					System.out.println("No");
				
				in = entrada.nextInt();										//Busco el siguiente numero de vasos
			}
		}
	}
}