public class Heaps {
	public static String toString(int[] heap) {
		StringBuilder s = new StringBuilder();
		int enNivel = 1;
		int finNivel = 1;
		for (int i = 0; i < heap.length; i++) {
			s.append(heap[i]);

			if (i != heap.length -1)
				if (i == finNivel-1) {
					s.append("] [");
					enNivel *= 2;
					finNivel += enNivel;
				} else
					s.append(", ");
		}
		s.append("]");
		return "Heap: [" + s.toString() + " - size: " + heap.length;
	}

	/**
	 * @return -1 if the queue contains a MinHeap, +1 if it is a maxHeap, or = if its empty.
	 */
	public static int typeOfHeap(int [] data) {
		// TODO EJERCICIO 1
		if(data.length != 0){
			if (minHeap(data))
				return -1;
			else if (maxHeap(data))
				return 1;
			else
				return 0;
		}
		return 0;
	}
	private static boolean minHeap(int [] data){
		int min = data[0];
		for (int i = 1; i < data.length;i++){
			if (data[i] < min)
				return false;
			if (data[(i-1) / 2] > data[i])
				return false;
		}
		return true;
	}
	private static boolean maxHeap(int [] data){
		int max = data[0];
		for (int i = 1; i < data.length;i++){
			if (data[i] > max)
				return false;
			if (data[(i-1) / 2] < data[i])
				return false;
		}
		return true;
	}
	/** Sinks the element in the position p, assuming that v is a maxHeap */
	private static void sink(int[] v, int p, int size) {
		// TODO EJERCICIO 2
		int aux = v[p];
		int i = p *2 +1;
		if (i + 1 < size && v[i] < v[i + 1])
			i++;
		if (v[i] > aux){
			v[p] = v[i];
			p = i;
		}
		if (p != i)
			v[p] = aux;
	}
	private static void swap(int [] v, int i, int j){
		int aux = v[i];
		v[i] = v[j];
		v[j] = aux;
	}

	/**C onverts an unsorted vector into a maxHeap.**/
	public static void maxHeapify (int[] v) {
		// TODO EJERCIO 3
		for (int i = 0; i < v.length; i++)
			floatup(v,i);
	}
	private static void floatup(int [] v, int index){
		if (index == 0)
			return;
		int padre = (index -1) / 2;
		while (index > 0 && v[index] > v[padre]){
			swap(v,index, padre);
			index = padre;
			padre = (index -1)/ 2;
		}
	}
	
	/** Sorts the vector v using the heapsort algorithm **/
	static public void heapsort (int [] v) {
		// TODO EJERICIO 4
		heapify(v);
		int size=v.length;
		while (size > 1) {
			swap(v,0,size-1);
			size--;
			sink(v,0,size);
		}
	}
	private static void heapify(int [] v){
		for (int i=v.length/2-1; i>=0; i--)
			sink(v,i,v.length);
	}
}
