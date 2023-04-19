package practica4;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Implementación de la interface List<T> usando nodos doblemenete enlazados de
 * forma circular y con nodo cabecera.
 * 
 * La clase admite nulls como elementos de la lista, por lo tanto deberá tratar
 * correctamente su inserción, búsqueda y borrado.
 *
 * @param <T> Tipo básico de la lista
 */
public class EDHeaderLinkedList<T> implements List<T> {

	/**
	 * Definición de nodo
	 */
	private class Node {
		public T data;
		public Node next;
		public Node prev;

		public Node(T element) {
			data = element;
			next = null;
			prev = null;
		}
	}

	// Enlace al nodo cabecera. A él se enlazan el primero y el último
	private Node header;
	// Número de elementos de la clase
	private int size;

	/**
	 * Constructor por defecto
	 */
	public EDHeaderLinkedList() {
		// TODO
			header = new Node(null);
			header.next = header;
			header.prev = header;
			size = 0;

	}

	/**
	 * Constructor que copía todos los elementos de otra clase
	 * 
	 * @param c
	 */
	public EDHeaderLinkedList(Collection<T> c) {
		this();
		addAll(c);
	}

	/**
	 * Compara si dos elementos son iguales teniendo en cuenta que uno o ambos
	 * pueden ser null. 
	 *
	 * @return Si ambos elementos son iguales
	 */
	private boolean equalsNull(T one, Object item) {
		if (one == null)
			return one == item;
		else
			return one.equals(item);
	}


	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return header.next == header;
	}

	@Override
	public void clear() {
		header.next = header.prev = header;
		size = 0;
	}

	@Override
	public boolean add(T item) {
		// TODO
		Node n = new Node(item);

		n.next = header;
		n.prev = header.prev;
		header.prev.next = n;
		header.prev = n;

		size++;
		return true;
	}

	@Override
	public void add(int index, T element) {
		//TODO
		if(index < 0 || index > size){
			throw new IndexOutOfBoundsException();
		}
		if(isEmpty() || index == size){
			add(element);
		} else {
			Node n = new Node(element);
			size++;
			if (index == 0){
				n.next = header.next;
				n.prev = header;
				header.next.prev = n;
				header.next = n;
			} else {
				Node aux = header.next;
				for (int i = 0; i < index; i++){
					aux = aux.next;
				}
				n.next = aux;
				n.prev = aux.prev;
				aux.prev.next = n;
				aux .prev = n;
			}
		}

	}

	@Override
	public boolean remove(Object item) {
		//TODO
		if (isEmpty()){
			return false;
		}
		Node aux = header.next;
		for (int i = 0; i < size;i++){
			if (equalsNull(aux.data,item)){
				size--;
				if (aux == header.next){
					header.next = aux.next;
					header.next.prev = header;
				}
				if (aux == header.prev){
					header.prev = aux.prev;
					header.prev.next = header;
				} else {
					aux.prev.next = aux.next;
					aux.next.prev = aux.prev;
				}
				return true;
			}
			aux = aux.next;
		}
		return false;
	}

	@Override
	public T remove(int index) {
		// TODO
		if (index < 0 || index >= size){
			throw  new IndexOutOfBoundsException();
		}
		Node aux = header.next;
		for(int i = 0; i < index;i++){
			aux = aux.next;
		}
		remove(aux.data);
		return aux.data;
	}


	@Override
	public boolean contains(Object item) {
		// TODO
		return indexOf(item) != -1;
	}

    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException();
    }

    @Override
	public T get(int index) {
		// TODO
		if (index < 0 || index >= size){
			throw new IndexOutOfBoundsException();
		}
		Node aux = header.next;
		for (int i = 0; i < index;i++){
			aux = aux.next;
		}
		return aux.data;
	}

	@Override
	public T set(int index, T element) {
		// TODO
		if (index < 0 || index >= size){
			throw new IndexOutOfBoundsException();
		}
		Node aux = header.next;
		for(int i = 0; i < index; i++){
			aux = aux.next;
		}
		T old = aux.data;
		aux.data = element;
		return old;
	}

	@Override
	public int indexOf(Object item) {
		Node aux = header.next;
		int i = 0;

		while (aux != header) {
			if (equalsNull(aux.data, item))
				return i;
			aux = aux.next;
			i++;
		}

		return -1;
	}

	@Override
	public int lastIndexOf(Object item) {
		// TODO
		int last = indexOf(item);
		if (last == -1){
			return -1;
		}
		Node aux = header.next;
		for (int i = 0; i < size; i++){
			if (equalsNull(aux.data,item)) {
				last = i;
			}
			aux = aux.next;
		}
		return last;
	}

    @Override
    public ListIterator<T> listIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
    throw new UnsupportedOperationException();
	}

    @Override
	public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
        throw new UnsupportedOperationException();
    }

	@Override
	public boolean addAll(int index, Collection<? extends T> c) {
        throw new UnsupportedOperationException();
    }

	@Override
	public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		//TODO
		if (c.isEmpty()){
			Node aux = header.next;
			while (header.next != null){
				remove(aux);
				aux = aux.next;
			}
			return true;
		}
		Node aux = header.next;
		int tamaño = size;

		for (int i= 0; i < tamaño;i++){
			if (!c.contains(aux.data)){
				remove(aux.data);
			}
			aux = aux.next;

		}
		if(size != tamaño) {
			return true;
		}
     	return false;
	}
	
	@Override
	public String toString(){
		StringBuilder str = new StringBuilder();
		str.append("[" );
		if (header.next != header) {
			Node aux = header.next;
			str.append(aux.data == null ? "null" : aux.data.toString());
			while (aux.next != header) { 
				aux = aux.next;
				str.append(", ");
				str.append(aux.data == null ? "null" : aux.data.toString());
			}
		}
		str.append("],compar size = ");
		str.append(size);
		
		return str.toString();
	}

	@Override
	public Object[] toArray() {
		Object retVal[] = new Object[size];

		Node aux = header.next;
		int i = 0;
		while (aux != header)
			retVal[i++] = aux.data;
		return retVal;
	}

	@Override
	public <U> U[] toArray(U[] a) {
		U[] retVal;
		if (a.length > size)
			retVal = a;
		else
			retVal = (U[]) new Object[size];

		Node aux = header.next;
		int i = 0;
		while (aux != header)
			retVal[i++] = (U) aux.data;

		return retVal;
	}



}
