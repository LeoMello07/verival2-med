package src.controller;

import src.model.*;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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
                    System.out.println("Exiting...\n");
                    break;
                }
            } catch (Exception e) {
                System.out.println("Please, enter a correct number.\n");
            }
        }
        return next;
    }

    public static Allocation createAllocation(List <Doctor> doctorList, List <SurgeryRoom> surgeryRooms) {
        Doctor doctor = doctorSelection(doctorList);
        if (verifyObject(doctor)) { return null; }
        System.out.println("Select a room:");
        SurgeryRoom room = roomSelection(
                surgeryRooms
                        .stream()
                        .filter( r -> doctor.getSpecialization() == Specialization.dermatologist ?
                                (r.getType() == SurgeryRoomType.small) :
                                (r.getType() == SurgeryRoomType.big || r.getType() == SurgeryRoomType.high_risk))
                        .collect(Collectors.toList())
        );
        if (verifyObject(room)) { return null; }

        System.out.println("Select a time period:");
        Period period = createPeriod();

        System.out.println("Allocation request created. Verifying database to finish operation...");
        return (new Allocation(doctor, room, period));
    }

    public static boolean deleteReservation(List<Allocation> allocations) {
        List <Allocation> reservations = reservations(allocations);
        if (reservations.isEmpty()) {
            System.out.println("There is no reservations!");
            return false;
        }
        int exitIndex = reservations.size() + 1;
        int index = -1;
        System.out.println("Select an index to delete");
        while (index < 1 || index > exitIndex) {
            for (int i = 1; i < exitIndex; i++) {
                System.out.println(i + ". " + reservations.get(i-1));
            }
            System.out.println(exitIndex + ". Exit");
            index = scanner.nextInt();
        }
        if (index == exitIndex) { return false; }
        return allocations.remove(reservations.get(index-1));
    }

    public static List<Allocation> reservations(List<Allocation> allocations) {
        GregorianCalendar calendar = new GregorianCalendar();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return allocations
                .stream()
                .filter( allocation -> allocation.getPeriod().day > day )
                .collect(Collectors.toList());
    }

    private static boolean verifyObject(Object obj) {
        if (obj == null) {
            System.out.println("Exiting...");
            return true;
        }
        return false;
    }

    private static Doctor doctorSelection(List <Doctor> doctorList) {
        int index = -1;
        int exitIndex = doctorList.size()+1;
        while (index < 1 || index > exitIndex) {
            System.out.println("Select a doctor:");
            for (int i = 1; i <= doctorList.size(); i++) {
                System.out.println(i + ". " + doctorList.get(i-1));
            }
            System.out.println(exitIndex + ". Exit");
            index = scanner.nextInt();
        }
        if (index == exitIndex) { return null; }
        return doctorList.get(index-1);
    }

    private static SurgeryRoom roomSelection(List <SurgeryRoom> surgeryRooms) {
        int index = -1;
        int exitIndex = surgeryRooms.size()+1;
        while (index < 1 || index > exitIndex) {
            System.out.println("Select a doctor:");
            for (int i = 1; i <= surgeryRooms.size(); i++) {
                System.out.println(i + ". " + surgeryRooms.get(i-1));
            }
            System.out.println(exitIndex + ". Exit");
            index = scanner.nextInt();
        }
        if (index == exitIndex) { return null; }
        return surgeryRooms.get(index-1);
    }

    private static Period createPeriod() {
        int day = -1, startHour = -1, endHour = -1;
        System.out.println("Inform day:");
        while (day < 0) {
            day = scanner.nextInt();
            System.out.println();
        }
        System.out.println("Inform start hour:");
        while (startHour < 6 || startHour > 20) {
            startHour = scanner.nextInt();
            System.out.println();
        }
        System.out.println("Inform end hour:");
        while (endHour < 8 || endHour > 22) {
            endHour = scanner.nextInt();
            System.out.println();
        }
        if (endHour < startHour) {
            System.out.println("Start hour cannot be after end hour! Please, do it again.");
            return createPeriod();
        }
        return (new Period(day, startHour, endHour));
    }

    public static int[] days() {
        System.out.println("\nInform the first day");
        int d1 = scanner.nextInt();
        System.out.println("\nInform the second day");
        int d2 = scanner.nextInt();
        return (new int[] {Math.min(d1, d2), Math.max(d1, d2)});
    }

}
