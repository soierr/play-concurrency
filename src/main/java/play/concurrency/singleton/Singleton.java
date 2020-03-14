package play.concurrency.singleton;

public class Singleton {
    private Integer i = new Integer(5);
    private Integer j = new Integer(5);

    public String getI() {
        return i.toString();
    }

    public String getJ() {
        return j.toString();
    }
}
