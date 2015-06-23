package util;

public class PriorityQueueImpl<T extends Comparable<T>> implements PriorityQueue<T>{

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void insert(T element) {
		if(element == null) throw new IllegalArgumentException();
		T[] array = new T[10];
		
	}

	@Override
	public T popMax() {
		// TODO Auto-generated method stub
		return null;
	}

}
