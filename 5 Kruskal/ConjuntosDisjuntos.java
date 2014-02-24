
public class ConjuntosDisjuntos {

	private int[] capo;
	private int[] rango;

	/**
	 * El constructor de la clase.
	 */
	public ConjuntosDisjuntos(int size) {

		this.capo = new int[size];
		this.rango = new int[size];

		this.clean();
	}

	/**
	 * Inicializa el elemento.
	 */
	public void clean() {

		for (int i = 0; i < this.capo.length; i++) {
			this.capo[i] = i;
			this.rango[i] = 0;
		}
	}

	/**
	 * Une dos conjuntos disjuntos.
	 */
	public void union(int pri, int seg) {
		int pC, sC;

		pC = this.find(pri);	//Busca los representantes
		sC = this.find(seg);	//de cada conjunto disjunto

		//Si ya son parte del mismo conjunto disjunto
		if (pC == sC)
			return;

		//Si el rango del conjunto disjunto de pri
		//es mayor al rango del conjunto disjunto de seg
		if (this.rango[pC] > this.rango[sC])
			this.capo[sC] = pC;

		//Si el rango del conjunto disjunto de pri
		//es menor al rango del conjunto disjunto de seg
		else if (this.rango[pC] < this.rango[sC])
			this.capo[pC] = sC;

		//Si tienen el mismo rango
		else {
			this.capo[sC] = pC;
			this.rango[pC]++;
		}
	}

	/**
	 * Este metodo consigue y devuelve el representante
	 * del elemento indicado, al hacerlo modifica la estructura
	 * de manera que quede mas compacta.
	 * (Mayor eficiencia la proxima vez que se use)
	 */
	public int find(int id) {

		//Si el elemento pedido no existe.
		if ((id < 0) || (this.capo.length <= id)) {
			System.out.println("Elemento: " + id + " no existe." + this.capo.length);
			return -1;
		}

		//Si el reresentante del elemento pedido es el mismo,
		//conseguimos al representante del conjunto
		if (this.capo[id] == id)
			return id;

		//Asigna como representante del elemento al
		//representante del conjunto, para mayor eficiencia
		this.capo[id] = this.find(this.capo[id]);

		//Devuelve a tal representante
		return this.capo[id];
	}
}
