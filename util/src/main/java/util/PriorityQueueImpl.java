package util;

public class PriorityQueueImpl<T extends Comparable<T>> implements PriorityQueue<T>{

	private static final int DEFAULT_CAPACITY = 10;
	
	private int capacity = 0;
	private T[] queues;
	private int maxLength;

	public PriorityQueueImpl(int maxLength) {
		if(maxLength <= 0) throw new IllegalArgumentException("Max lenght should be positive value");
		this.maxLength = maxLength;
		queues = (T[]) new Comparable[maxLength];
	}
	
	public PriorityQueueImpl() {
		this.maxLength = DEFAULT_CAPACITY;
		queues = (T[]) new Comparable[maxLength];
	}

	@Override
	public int size() {
		return capacity;
	}

	@Override
	public void insert(T element) {
		if(element == null) throw new IllegalArgumentException("Null elements is not allowed");
		if(capacity >= maxLength) {
			resize();
		}
		queues[capacity] = element; 
		capacity++;		
	}

	private void resize() {
		maxLength = 2 * maxLength;
		T[] temp = (T[]) new Comparable[maxLength];
		for(int i = 0 ; i < queues.length; i++) {
			temp[i] = queues[i];
		}
		queues = temp;
	}

	

	@Override
	public T popMax() {
		// TODO Auto-generated method stub
		return null;
	}

}
