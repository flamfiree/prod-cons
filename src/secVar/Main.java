package secVar;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    ArrayBlockingQueue<Integer> buffer = new ArrayBlockingQueue<>(5);

    private Runnable producer = () -> {
        Random r = new Random();
        while (true) {
            try {
                int item = r.nextInt();
                System.out.println("Produced: " + item);
                produce(item);
                Thread.sleep(1000 + r.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    private Runnable consumer = () -> {
        while (true) {
            try {
                Integer consumed = consume();
                System.out.println("Consumed: " + consumed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    private void produce(Integer item) {
        synchronized (buffer) {
            buffer.add(item);
            buffer.notifyAll();
        }
    }

    private Integer consume() throws InterruptedException {
        synchronized (buffer) {
            while (buffer.isEmpty()) {
                buffer.wait();
            }
            Integer removed = buffer.poll();
            buffer.notifyAll();
            return removed;
        }
    }

    public void play() {
        List<Thread> threads = IntStream.range(0, 10).boxed()
                .map(i -> new Thread(producer))
                .collect(Collectors.toList());
        IntStream.range(0, 10).boxed()
                .map(i -> new Thread(consumer))
                .forEach(threads::add);
        threads.forEach(Thread::start);
        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    public static void main(String[] args) {
        new Main().play();
    }
}
