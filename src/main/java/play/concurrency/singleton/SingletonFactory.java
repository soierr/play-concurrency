package play.concurrency.singleton;


public class SingletonFactory {
    private volatile Singleton singleton = null;

    public Singleton getInstance() {
        if (singleton == null) {
            synchronized (this) {
                //if(ss == null) {
                    singleton = new Singleton();
                    //System.out.println("Singleton created");
                    //singleton = ss;
                    //return singleton;
                //}
            }
        }
        return singleton;
    }

    public void clear() {
        singleton = null;
        //System.out.println("Cleared");
    }

    public synchronized void print() {
        System.out.println("Hello there");
    }
}
