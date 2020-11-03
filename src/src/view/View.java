package src.view;

import src.controller.Hospital;
import src.controller.InputManager;
import src.model.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

public class View {
    private Hospital hospital;

    public View() {
//        Create doctors list
        List <Doctor> doctors = new ArrayList<>();
        doctors.add(new Doctor("Luiz Guerra", 135, Specialization.dermatologist));
        doctors.add(new Doctor("Leonardo de Mello", 468, Specialization.cardiologist));
        doctors.add(new Doctor("Daniel Callegari", 951, Specialization.neurologist));
//        Create rooms list
        List <SurgeryRoom> rooms = new ArrayList<>();
        rooms.add(new SurgeryRoom("Hera", SurgeryRoomType.small));
        rooms.add(new SurgeryRoom("Demeter", SurgeryRoomType.big));
        rooms.add(new SurgeryRoom("Hestia", SurgeryRoomType.high_risk));
        rooms.add(new SurgeryRoom("Hephaestus", SurgeryRoomType.high_risk));
//        Create hospital given doctors and rooms
        this.hospital = new Hospital(doctors, rooms);
//        Now that everything is set up, we wait for the user
        manageInput();
    }

    private void manageInput() {
        int action = 0;
        while (true) {
            if (InputManager.decisionBranch() == 1) {
                visualize();
            } else {
                interact();
            }
        }
    }

    private void visualize() {
        int value = InputManager.visualize();
        if (value == 8) { return; }
        switch (value) {
            case 1 -> printDoctors();
            case 2 -> printRooms();
            case 3 -> printAllocations();
            case 4 -> {
                int[] days = InputManager.days();
                printAllocations(days[0], days[1]);
            }
            case 5 -> printReservations();
            case 6 -> costByDoctor();
            case 7 -> costByRoom();
        }
    }

    private void interact() {

    }

    public void createAllocation(Doctor doctor, SurgeryRoom room, Period period) {
        hospital.allocate(doctor, room, period);
    }

    public void printDoctors() {
        System.out.println("Doctors:");
        System.out.println("--------");
        for (Doctor doctor : hospital.getDoctors()) {
            System.out.println(doctor);
        }
        System.out.println("--------\n");
    }

    public void printRooms() {
        System.out.println("Rooms:");
        System.out.println("--------");
        for (SurgeryRoom room : hospital.getSurgeryRooms()) {
            System.out.println(room);
        }
        System.out.println("--------\n");
    }

    public void printAllocations() {
        System.out.println("Allocations:");
        System.out.println("--------");
        for (Allocation allocation : hospital.getAllocations()) {
            System.out.println(allocation);
        }
        System.out.println("--------\n");
    }

    public void printAllocations(int day1, int day2) {
        System.out.println("Allocations between day " + day1 + " and " + day2 + ": ");
        System.out.println("--------");
        for (Allocation allocation : hospital.getAllocations()
                .stream()
                .filter( a -> a.getPeriod().day > day1 && a.getPeriod().day < day2 )
                .collect(Collectors.toList()) ) {
            System.out.println(allocation);
        }
        System.out.println("--------\n");
    }

    public void printReservations() {
        GregorianCalendar calendar = new GregorianCalendar();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println("All reservations:");
        System.out.println("--------");
        for (Allocation allocation : hospital.getAllocations()
                .stream()
                .filter( a -> a.getPeriod().day > day)
                .collect(Collectors.toList()) ) {
            System.out.println(allocation);
        }
        System.out.println("--------\n");
    }

    public void costByDoctor() {
        System.out.println("Costs by doctor: ");
        System.out.println("--------");
        for (Doctor doctor : hospital.getDoctors()) {
            int total = hospital.getAllocations()
                    .stream()
                    .filter( a -> a.getDoctor().getName().equals(doctor.getName()) )
                    .map(Allocation::getCost)
                    .reduce(0, Integer::sum);
            System.out.println("Doctor: " + doctor.getName() + " - Cost: " + total + ".");
        }
        System.out.println("--------\n");
    }

    public void costByRoom() {
        System.out.println("Costs by Surgery Room: ");
        System.out.println("--------");
        for (SurgeryRoom room : hospital.getSurgeryRooms()) {
            int total = hospital.getAllocations()
                    .stream()
                    .filter( a -> a.getRoom().getName().equals(room.getName()) )
                    .map(Allocation::getCost)
                    .reduce(0, Integer::sum);
            System.out.println("Surgery Room: " + room.getName() + " - Cost: " + total + ".");
        }
        System.out.println("--------\n");
    }

}

