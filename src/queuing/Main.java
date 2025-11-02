package queuing;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        QueuingSystem system = new QueuingSystem();
        QueueMonitor monitor = new QueueMonitor();
        system.attachMonitor(monitor);

        HelpDeskStation desk1 = new HelpDeskStation("Desk 1", system);
        HelpDeskStation desk2 = new HelpDeskStation("Desk 2", system);
        HelpDeskStation desk3 = new HelpDeskStation("Desk 3", system);

        Scanner scanner = new Scanner(System.in);
        String command;

        System.out.println("Pag-ibig Centralized Queuing System");
        System.out.println("Commands: next [desk], reset [desk] [number], monitor, exit");

        while (true) {
            System.out.print("\n> ");
            command = scanner.nextLine().trim();

            if (command.equalsIgnoreCase("exit")) {
                System.out.println("System shutdown.");
                break;
            }

            String[] parts = command.split("\\s+");
            if (parts.length < 2) {
                System.out.println("Invalid command.");
                continue;
            }

            String action = parts[0].toLowerCase();
            String deskId = parts[1];

            HelpDeskStation desk = getDeskById(deskId, desk1, desk2, desk3);
            if (desk == null) {
                System.out.println("Invalid desk ID. Use: Desk1, Desk2, Desk3");
                continue;
            }

            try {
                switch (action) {
                    case "next":
                        desk.callNext();
                        break;
                    case "reset":
                        if (parts.length != 3) {
                            System.out.println("Usage: reset [desk] [number]");
                            break;
                        }
                        int newNum = Integer.parseInt(parts[2]);
                        desk.resetQueue(newNum);
                        break;
                    case "monitor":
                        monitor.display();
                        break;
                    default:
                        System.out.println("Unknown action: " + action);
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        scanner.close();
    }

    private static HelpDeskStation getDeskById(String id, HelpDeskStation... desks) {
        for (HelpDeskStation desk : desks) {
            if (desk.getStationId().equalsIgnoreCase(id)) {
                return desk;
            }
        }
        return null;
    }
}
