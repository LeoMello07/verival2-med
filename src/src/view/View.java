package src.view;

import src.controller.Hospital;
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
    }

    public void createAllocation(Doctor doctor, SurgeryRoom room, Time time) {
        hospital.allocate(doctor, room, time);
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

