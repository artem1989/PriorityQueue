package util;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.NoSuchElementException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PriorityQueueTest {

	public static final int N_THREADS = 20;
	private PriorityQueue<Integer> priorityQueue;
	private Thread thread1;
	private Thread thread2;
	
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

	@Test(expected = NoSuchElementException.class)
	public void testPopFromEmptyQueue() {
		priorityQueue.popMax();
	}

	
	@Test
	public void testInsertNode() {
		priorityQueue.insert(new Integer(10));
		priorityQueue.insert(new Integer(8));
		priorityQueue.insert(new Integer(17));
		priorityQueue.insert(new Integer(13));
		priorityQueue.insert(new Integer(14));

		assertEquals(5, priorityQueue.size());
		assertEquals(Integer.valueOf(17), priorityQueue.popMax());
	}

	@Test
	public void testPopMax() {
		priorityQueue.insert(new Integer(11));
		priorityQueue.insert(new Integer(10));
		priorityQueue.insert(new Integer(15));
		priorityQueue.insert(new Integer(12));
		priorityQueue.insert(new Integer(33));
		priorityQueue.insert(new Integer(13));
		priorityQueue.insert(new Integer(32));

		assertEquals(Integer.valueOf(33), priorityQueue.popMax());
		assertEquals(Integer.valueOf(32), priorityQueue.popMax());
		assertEquals(Integer.valueOf(15), priorityQueue.popMax());
		assertEquals(Integer.valueOf(13), priorityQueue.popMax());

		assertEquals(3, priorityQueue.size());
	}


}
