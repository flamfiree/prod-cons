package firstVar;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class MyQueue {
    private LinkedBlockingQueue<Integer> queue;
    private int capacity;

    public MyQueue(int capacity) {
        this.capacity = capacity;
        queue = new LinkedBlockingQueue<>(capacity);
    }

    public synchronized void produce(int item) throws InterruptedException {
        // while (queue.size() == capacity) {
        //     System.out.println("Buffer is full");
        //     wait(); // ждем, пока буфер не освободится
        // }
        queue.put(item);
        System.out.println("Produced: " + item);
        // notifyAll(); // будим все потоки, которые ждут на этом мониторе
    }

    public synchronized int consume() throws InterruptedException {
        // while (queue.isEmpty()) {
        //     System.out.println("Buffer is empty");
        //     wait(); // ждем, пока буфер не наполнится
        // }
        int item = queue.take();
        System.out.println("Consumed: " + item);
        // notifyAll(); // будим все потоки, которые ждут на этом мониторе
        return item;
    }
}
