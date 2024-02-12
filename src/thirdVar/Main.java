package thirdVar;

import java.util.concurrent.ArrayBlockingQueue;

public class Main {
    public static void main(String[] args) {
        Integer num = 50;
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(1);

        //Producer
        new Thread(() -> {
            int i = 0;
            while(true){
                try {
                    queue.put(++i);
                    System.out.println("Producer:" + i);
                    Thread.sleep(10);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }).start();

        //Consumer
        new Thread(() -> {
            while(true){
                try {
                    Integer x = queue.take();
                    System.out.println("Consumer:" + x);
                    Thread.sleep(10);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
