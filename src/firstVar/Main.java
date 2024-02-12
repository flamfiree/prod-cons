package firstVar;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        MyQueue queue = new MyQueue(10); // создаем общую очередь на 5 элементов

        List<Producer> prods = new ArrayList<>();
        List<Consumer> cons = new ArrayList<>();

        for(int i = 0; i < 20; i++){
            prods.add(new Producer(queue));
            cons.add(new Consumer(queue));
        }

        for(int i = 0; i < 20; i++){
            prods.get(i).start();
            cons.get(i).start();
        }
    }
}