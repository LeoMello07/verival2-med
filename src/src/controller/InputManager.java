package src.controller;

import java.util.Scanner;

public class InputManager {
    private static final Scanner scanner = new Scanner(System.in);

    public static Integer decisionBranch() {
        System.out.println("Inform what you would like to do:\n");
        Integer next = null;
        System.out.println("1. See a table");
        System.out.println("2. Interact with data");
        while (next == null) {
            try {
                next = scanner.nextInt();
                if (next != 1 && next != 2) {
                    next = null;
                    System.out.println("Please, enter a correct number.\n");
                }
            } catch (Exception e) {
                System.out.println("Please, enter a correct number.\n");
            }
        }
        return next;
    }

    public static Integer visualize() {
        Integer next = null;
        System.out.println("Inform which list you want to see:");
        System.out.println("1. Doctors");
        System.out.println("2. Surgery Rooms");
        System.out.println("3. All allocations and prices");
        System.out.println("4. All allocations between 2 dates");
        System.out.println("5. All reservations");
        System.out.println("6. Generated cost by doctor");
        System.out.println("7. Generated cost by surgery room");
        System.out.println("8. Exit");
        while (next == null) {
            try {
                next = scanner.nextInt();
                if (next < 1 || next > 8) { next = null; }
                if (next == 8) {
                    System.out.println("Exiting...");
                    break;
                }
            } catch (Exception e) {
                System.out.println("Please, enter a correct number.\n");
            }
        }
        return next;
    }

    public static Integer interactWith() {
        Integer next = null;
        System.out.println("1. Create a reservation");
        System.out.println("2. Cancel a reservation");
        System.out.println("3. Exit");
        while (next == null) {
            try {
                next = scanner.nextInt();
                if (next < 1 || next > 8) { next = null; }
                if (next == 3) {
                    System.out.println("Exiting...");
                    break;
                }
            } catch (Exception e) {
                System.out.println("Please, enter a correct number.\n");
            }
        }
        return next;
    }

    public static int[] days() {
        int d1 = scanner.nextInt();
        int d2 = scanner.nextInt();
        return (new int[] {Math.min(d1, d2), Math.max(d1, d2)});
    }

}
