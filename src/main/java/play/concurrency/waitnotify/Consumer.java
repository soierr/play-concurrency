package play.concurrency.waitnotify;

public class Consumer {

    private static String uuid;
    private boolean stopConsuming;

    public void startConsuming() {
        while (!stopConsuming) {
            try {
                System.out.println("Entering to wait");
                this.wait();
            }catch (InterruptedException ie) {
                System.out.println("Interrupted !");
            }
            System.out.println("Hello this uuid: " + uuid);
        }
    }

    public static void setUuid(String uuid) {
        Consumer.uuid = uuid;
    }

    public void stopConsuming() {
        this.stopConsuming = true;
        this.notify();
    }
}
