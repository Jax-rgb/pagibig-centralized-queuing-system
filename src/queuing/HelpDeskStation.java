package queuing;

public class HelpDeskStation {
    private final String stationId;
    private final QueuingSystem system;

    public HelpDeskStation(String stationId, QueuingSystem system) {
        this.stationId = stationId;
        this.system = system;
        system.registerStation(this);
    }

    public int callNext() {
        int number = system.getNextNumber();
        System.out.println("[" + stationId + "] Now serving: " + number);
        return number;
    }

    public void resetQueue(int newNumber) {
        System.out.println("[" + stationId + "] Requesting queue reset to: " + newNumber);
        system.resetQueue(newNumber);
    }

    public int getCurrentNumber() {
        return system.getCurrentNumber();
    }

    public String getStationId() {
        return stationId;
    }
}
