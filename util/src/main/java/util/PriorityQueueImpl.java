package util;

import java.util.NoSuchElementException;

public class PriorityQueueImpl<T extends Comparable<T>> implements PriorityQueue<T>{

	private static final int DEFAULT_CAPACITY = 10;
	
	private int capacity;
	private T[] data;

	public PriorityQueueImpl(int maxLength) {
		if(maxLength <= 0) throw new IllegalArgumentException("Max length should be positive value");
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
		percolateUp();
		capacity++;
	}

	private void percolateUp() {
		int currentNode = capacity;
		int parent = (currentNode - 1)/2;

		while(currentNode > 0 && data[currentNode].compareTo(data[parent]) > 0) {
			swap(currentNode, parent);
			currentNode = parent;
			parent = (currentNode - 1)/2;
		}
	}

	private void swap(int currentNode, int nextNode) {
		T temp = data[currentNode];
		data[currentNode] = data[nextNode];
		data[nextNode] = temp;
	}

	private void resize() {
		T[] temp = (T[]) new Comparable[2 * data.length];
		for(int i = 0 ; i < data.length; i++) {
			temp[i] = data[i];
		}
		data = temp;
	}

	public T popMax() {
		if(capacity < 1) throw new NoSuchElementException("No elements to pop");
		T max = data[0];
		putTailIntoRootAndRemove();
		capacity--;
		heapify(0);
		return max;
	}

	private void putTailIntoRootAndRemove() {
		data[0] = data[capacity - 1];
		data[capacity - 1] = null;
	}

	private void heapify(int i) {
		int largest = i;
		int left = 2*i + 1;
		int right = 2*i + 2;

		if(left < capacity && data[left].compareTo(data[largest]) > 0) {
			largest = left;
		}

		if(right < capacity && data[right].compareTo(data[largest]) > 0) {
			largest = right;
		}

		if(largest != i) {
			swap(i, largest);
			heapify(largest);
		}
	}

}
