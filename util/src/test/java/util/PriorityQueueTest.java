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

	@Test
	public void testInsert() {
		priorityQueue.insert(new Integer(5));
		assertEquals(1, priorityQueue.size());		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInsertInvalid() {
		priorityQueue.insert(null);
	}


}
