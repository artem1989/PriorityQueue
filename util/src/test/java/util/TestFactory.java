package util;

import java.util.Random;

public final class TestFactory {

    private TestFactory(){}

    public static Runnable createNewRunnable(PriorityQueue priorityQueue) {
        return new MRunnable(priorityQueue);
    }

    private static class MRunnable implements Runnable{

        private PriorityQueue<Integer> priorityQueue;

        public MRunnable(PriorityQueue priorityQueue) {
            this.priorityQueue = priorityQueue;
        }

        public void run() {
            priorityQueue.insert(new Random(20).nextInt());
        }
    }

}
