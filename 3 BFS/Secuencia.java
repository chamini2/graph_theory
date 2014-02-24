import java.util.ListIterator;

/**
 * Interfaz del tipo secuencia de tipo T.
 * 
 * Esta secuencia ofrece una cantidad muy reducida de metodos, puede ser
 * extendida por el usuario de ser necesario. Los metodos requeridos son
 * suficientes para implementar una lista LIFO con acceso a toda la secuencia.
 * 
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