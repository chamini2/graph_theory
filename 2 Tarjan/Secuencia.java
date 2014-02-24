import java.util.ListIterator;

/**
 *		Laboratorio de Algoritmos y Estructuras III
 *		Proyecto 2
 *		Integrantes: 	Alberto Cols, 09-10177
 *						Matteo Ferrando, 09-10285
 *		Interfaz: Secuencia<T>.java
 *		Descripcion: Interfaz de una Secuencia generica		
 *		15/02/2012
 */
public interface Secuencia<T> {
    
    /**
     * Agrega un elemento al final de la secuencia
     */
    public void agregar_final(T elemento);
    
    /**
     * Elimina un elemento del final de la secuencia
     */
    public void eliminar_final();
    
    /**
     * Devuelve el elemento en la posicion index de la secuencia.
     */
    public T obtener_elemento(int index);
    
    /**
     * Devuelve un iterador de los elementos de la secuencia.
     */
    public ListIterator<T> iterador();
}
