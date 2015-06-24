package util;

public class PriorityQueueImpl<T extends Comparable<T>> implements PriorityQueue<T>{

	private static final int DEFAULT_CAPACITY = 10;
	
	private int capacity;
	private T[] data;

	public PriorityQueueImpl(int maxLength) {
		if(maxLength <= 0) throw new IllegalArgumentException("Max lenght should be positive value");
		data = (T[]) new Comparable[maxLength];
		capacity = 0;
	}
	
	public PriorityQueueImpl() {
		this(DEFAULT_CAPACITY);
	}

	@Override
	public int size() {
		return capacity;
	}

	@Override
	public void insert(T element) {
		if(element == null) throw new NullPointerException("Null elements is not allowed");
		
		if(capacity == data.length) {
			resize();
		}
		
		data[capacity] = element;
		int currentNode = capacity;
		int parent = (currentNode - 1)/2;
		while(currentNode > 0 && data[currentNode].compareTo(data[parent]) < 0) {
			T temp = data[currentNode];
			data[currentNode] = data[parent];
			data[parent] = temp;
			currentNode = parent;
			parent = (currentNode - 1)/2;
		}
		capacity++;
	}

	private void resize() {
		T[] temp = (T[]) new Comparable[2*data.length];
		for(int i = 0 ; i < data.length; i++) {
			temp[i] = data[i];
		}
		data = temp;
	}

	

	@Override
	public T popMax() {
		return data[capacity - 1];
	}

}
