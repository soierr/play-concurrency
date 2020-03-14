package play.concurrency.synchronousqueue;

import java.util.concurrent.SynchronousQueue;

public class AppSynchronousQueue {
    public static void main(String[] args) {
        SynchronousQueue<Message> messageQueue = new SynchronousQueue<>();
        Consumer consumer = new Consumer(messageQueue);
        Producer producer = new Producer(messageQueue);
        new Thread(consumer::startConsuming).start();
        new Thread(producer::startProducing).start();

        try {
            Thread.sleep(20000);
        }catch (InterruptedException ie) {
            ie.printStackTrace();
        }

    }

}
