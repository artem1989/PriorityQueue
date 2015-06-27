package util;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.Assert.assertEquals;

public class PriorityQueueITest {

    private static final int N_THREADS = 20;
    private PriorityQueue priorityQueue;

    @Before
    public void initQueue() {
        priorityQueue = new PriorityQueueImpl();
    }

    @Test
    public void testInsertionByMultipleThreads() {
        ExecutorService executor = Executors.newFixedThreadPool(N_THREADS);
        for(int i = 0; i < N_THREADS; i++){
            executor.execute(TestFactory.createNewRunnable(priorityQueue));
        }
        executor.shutdown();

        while(!executor.isTerminated()) {}
        assertEquals(20, priorityQueue.size());
    }

}
