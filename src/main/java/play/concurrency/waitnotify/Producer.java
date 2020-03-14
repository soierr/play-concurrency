package play.concurrency.waitnotify;

import java.util.UUID;

public class Producer {
    private final Consumer consumer;
    private volatile boolean stopProducing;

    public Producer(Consumer consumer) {
        this.consumer = consumer;
    }

    public void startProducing() {
        while(!stopProducing) {
            System.out.println("Creating new UUID");
            Consumer.setUuid(UUID.randomUUID().toString());
            synchronized (consumer) {
                consumer.notify();
            }

            try {
                Thread.sleep(5000);
            }catch (InterruptedException ie) {
                ie.printStackTrace();
            }
            System.out.println("Waked");
        }
    }

    public void stopProducing() {
        stopProducing = true;
        consumer.stopConsuming();
    }
}
