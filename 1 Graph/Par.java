
public class Par {
	
	private Nodo elem;
	private Lista<Lado> list;
	
	public Par(Nodo e, Lista<Lado> l) {
		this.elem = e;
		this.list = l;
	}

	public Nodo obtener_Nodo() {
		return this.elem;
	}
	
	public Lista<Lado> obtener_Lista() {
		return this.list;
	}

	public void asignar_Nodo(Nodo e) {
		this.elem = e;
	}
	
	public void asignar_Lista(Lista<Lado> l) {
		this.list = l;
	}
}
