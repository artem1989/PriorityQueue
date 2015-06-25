package util;

import java.util.NoSuchElementException;

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

	public int size() {
		return capacity;
	}

	public void insert(T element) {
		if(element == null) throw new NullPointerException("Null elements is not allowed");
		
		if(capacity == data.length) {
			resize();
		}
		
		data[capacity] = element;
		int currentNode = capacity;
		int parent = (currentNode - 1)/2;
		while(currentNode > 0 && data[currentNode].compareTo(data[parent]) > 0) {
			T temp = data[currentNode];
			data[currentNode] = data[parent];
			data[parent] = temp;
			currentNode = parent;
			parent = (currentNode - 1)/2;
		}
		capacity++;
	}

	private void resize() {
		T[] temp = (T[]) new Comparable[2 * data.length];
		for(int i = 0 ; i < data.length; i++) {
			temp[i] = data[i];
		}
		data = temp;
	}

	public T popMax() {
		if(capacity == 0) throw new NoSuchElementException("No elements to pop");
		T max = data[0];
		T[] temp = data;
		for(int i = 1; i < data.length; i++) {
			data[i - 1] = temp[i];
		}
		capacity--;
		return max;
	}

}
