package play.concurrency.synchronousqueue;

import java.util.concurrent.SynchronousQueue;

public class Producer {
    private volatile boolean stopProducing;
    private SynchronousQueue<Message> synchronousQueue;

    public Producer(SynchronousQueue<Message> synchronousQueue) {
        this.synchronousQueue = synchronousQueue;
    }

    public void startProducing() {
        while (!stopProducing) {
            System.out.println("Creating new message and put to queue");
            synchronousQueue.offer(new Message(8));
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
            System.out.println("Waked");
        }
    }

    public void stopProducing() {
        stopProducing = true;
    }
}
