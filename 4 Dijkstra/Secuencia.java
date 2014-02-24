/**
 * Laboratorio de Algoritmos y Estructuras III
 * Proyecto 4
 * Integrantes: Alberto Cols, 09-10177
 * 				Matteo Ferrando, 09-10285
 * Interfaz: Secuencia.java
 * Descripcion: Interfaz del tipo secuencia de tipo T.
 */
import java.util.ListIterator;

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