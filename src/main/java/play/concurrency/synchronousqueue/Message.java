package play.concurrency.synchronousqueue;

public class Message {

    private int number;

    public Message(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
