package play.concurrency.waitnotify;

import play.concurrency.singleton.Singleton;
import play.concurrency.singleton.SingletonFactory;

import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AppWaitNotify {
    public static void main(String[] args) {
        Consumer consumer = new Consumer();
        Producer producer = new Producer(consumer);

        new Thread(producer::startProducing).start();
        new Thread(consumer::startConsuming).start();

        try {
            Thread.sleep(30000);
        }catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }
}
