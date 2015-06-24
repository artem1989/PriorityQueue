package util;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PriorityQueueTest {
	
	private PriorityQueue<Integer> priorityQueue;
	
	@Before
	public void initQueue() {
		priorityQueue = new PriorityQueueImpl<Integer>();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testIncorrectConstructorInput() {
		priorityQueue = new PriorityQueueImpl<Integer>(-5);
	}
	
	@Test
	public void testSizeEmpty() {
		assertEquals(0, priorityQueue.size());
	}

	@Test
	public void testInsertOneElement() {
		priorityQueue.insert(new Integer(5));
		assertEquals(1, priorityQueue.size());		
	}
	
	@Test
	public void testInsertMultipleElements() {
		priorityQueue.insert(new Integer(1));
		priorityQueue.insert(new Integer(5));
		priorityQueue.insert(new Integer(7));
		priorityQueue.insert(new Integer(8));
		
		assertEquals(4, priorityQueue.size());
	}
	
	@Test(expected = NullPointerException.class)
	public void testInsertNull() {
		priorityQueue.insert(null);
	}
	
	@Test
	public void testInsertNode() {
		priorityQueue.insert(new Integer(10));
		priorityQueue.insert(new Integer(8));
		priorityQueue.insert(new Integer(17));
		priorityQueue.insert(new Integer(13));
		priorityQueue.insert(new Integer(14));
		
		assertEquals(Integer.valueOf(17), priorityQueue.popMax());
		
	}
	
	


}
