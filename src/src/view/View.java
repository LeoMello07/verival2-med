package src.view;

import src.controller.Hospital;
import src.controller.InputManager;
import src.model.*;

import java.util.ArrayList;
import java.util.List;

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
//        System.out.println("Inform which list you want to see:");
//        System.out.println("1. Doctors");
//        System.out.println("2. Surgery Rooms");
//        System.out.println("3. All allocations and prices");
//        System.out.println("4. All allocations between 2 dates");
//        System.out.println("5. All reservations");
//        System.out.println("6. Generated cost by doctor");
//        System.out.println("7. Generated cost by surgery room");

    private void visualize() {
        int value = InputManager.visualize();
        if (value == 8) { return; }
        switch (value) {
            case 1: printDoctors(); break;
            case 2: printRooms(); break;
            case 3: printAllocations(); break;
            case 4:
            case 5:
            case 6:
            case 7:
            default: return;
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

}

