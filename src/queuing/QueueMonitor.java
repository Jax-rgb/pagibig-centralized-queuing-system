package queuing;

public class QueueMonitor {
    private int currentNumber;

    public void update(int number) {
        this.currentNumber = number;
        display();
    }

    public void display() {
        System.out.println("🖥️  ONLINE MONITOR: Current Queue Number = " + currentNumber);
    }

    public int getCurrentNumber() {
        return currentNumber;
    }
}
