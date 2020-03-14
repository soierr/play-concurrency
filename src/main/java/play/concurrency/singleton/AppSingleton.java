package play.concurrency.singleton;

import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AppSingleton {
    public static void main(String[] args) {
        SingletonFactory singletonFactory = new SingletonFactory();
        CyclicBarrier cyclicStart = new CyclicBarrier(2, singletonFactory::clear);
        CyclicBarrier cyclicFinish = new CyclicBarrier(2);
        for (int i = 0; i < 100000; i++) {
            List<Thread> threads = Stream.generate(() -> new Thread(() -> {
                try {
                    cyclicStart.await();
                    Singleton singleton = singletonFactory.getInstance();
                    if (singleton != null) {
                        try {
                            singleton.getJ();
                            singleton.getI();
                        } catch (NullPointerException npe) {
                            System.out.println("NPE detected");
                        }
                    }
                } catch (BrokenBarrierException | InterruptedException ie) {
                    ie.printStackTrace();
                }

                try {
                    cyclicFinish.await();
                } catch (BrokenBarrierException | InterruptedException ie) {
                    ie.printStackTrace();
                }
            }))
                    .limit(2)
                    .collect(Collectors.toList());
            threads.forEach(Thread::start);
        }
    }
}
