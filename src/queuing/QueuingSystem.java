package queuing;

import java.util.ArrayList;
import java.util.List;

public class QueuingSystem {
    private int currentNumber;
    private final List<HelpDeskStation> stations;
    private QueueMonitor monitor;

    public QueuingSystem() {
        this.currentNumber = 0;
        this.stations = new ArrayList<>();
        this.monitor = null;
    }

    public synchronized int getNextNumber() {
        currentNumber++;
        notifyMonitor();
        return currentNumber;
    }

    public synchronized void resetQueue(int newNumber) {
        this.currentNumber = newNumber;
        notifyMonitor();
        System.out.println("Queue reset to: " + currentNumber);
    }

    public synchronized int getCurrentNumber() {
        return currentNumber;
    }

    public void attachMonitor(QueueMonitor monitor) {
        this.monitor = monitor;
        monitor.update(currentNumber); // Initial sync
    }

    private void notifyMonitor() {
        if (monitor != null) {
            monitor.update(currentNumber);
        }
    }

    public void registerStation(HelpDeskStation station) {
        stations.add(station);
    }

    public List<HelpDeskStation> getStations() {
        return stations;
    }
}
