package play.concurrency.synchronousqueue;

import java.util.concurrent.SynchronousQueue;

public class Consumer {

    private boolean stopConsuming;
    private SynchronousQueue<Message> synchronousQueue;

    public Consumer(SynchronousQueue<Message> synchronousQueue) {
        this.synchronousQueue = synchronousQueue;
    }

    public void startConsuming() {
        while (!stopConsuming) {
            try {
                Message message = synchronousQueue.take();
                System.out.println("Message received: " + message.getNumber());
            }catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }

    public void stopConsuming() {
        this.stopConsuming = true;
        this.notify();
    }
}
