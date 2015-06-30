package util;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

public class PriorityQueueImpl<T extends Comparable<T>> implements PriorityQueue<T>{

	private final transient Logger logger = Logger.getLogger(PriorityQueue.class.getName());

	private static final int DEFAULT_CAPACITY = 10;
	public static final int LOCK_TIME = 100;
	
	private volatile int capacity;
	private T[] data;
	private Lock lock;

	public PriorityQueueImpl(int maxLength) {
		if(maxLength <= 0) throw new IllegalArgumentException("Max length should be positive value");
		data = (T[]) new Comparable[maxLength];
		lock = new ReentrantLock();
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
		try {
			if (lock.tryLock(LOCK_TIME, TimeUnit.SECONDS)) {
				if(capacity == data.length) {
                    resize();
                }

				data[capacity] = element;
				percolateUp();
			}
		} catch (InterruptedException e) {
			logger.info("Failed to insert new element. Can't catch reentrant lock");
		} finally {
			lock.unlock();
		}
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
		T max = null;
		try {
			if (lock.tryLock(LOCK_TIME, TimeUnit.SECONDS)) {
				max = data[0];
				putTailIntoRootAndRemove();
				capacity--;
				heapify(0);
			}
		} catch (InterruptedException e) {
			logger.info("Failed to insert new element. Can't catch reentrant lock");
		} finally {
			lock.unlock();
		}
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
