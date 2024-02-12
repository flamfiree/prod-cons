package firstVar;
class Producer extends Thread {
    private MyQueue queue;

    public Producer(MyQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            queue.produce((int) Thread.currentThread().threadId());
            Thread.sleep((int) (Math.random() * 100)); // имитация работы
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}