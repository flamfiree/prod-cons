package firstVar;
class Consumer extends Thread {
    private MyQueue queue;

    public Consumer(MyQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            queue.consume();
            Thread.sleep((int) (Math.random() * 100)); // имитация работы
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}