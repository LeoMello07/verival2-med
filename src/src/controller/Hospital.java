package src.controller;

import src.model.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Hospital {

    private List <Doctor> doctors;
    private List <SurgeryRoom> surgeryRooms;
    private List <Allocation> allocations;

    public Hospital(List<Doctor> doctors, List<SurgeryRoom> surgeryRooms) {
        this.doctors = doctors;
        this.surgeryRooms = surgeryRooms;
        this.allocations = new ArrayList<>();
    }

    public Hospital(List<Doctor> doctors, List<SurgeryRoom> surgeryRooms, List<Allocation> allocations) {
        this.doctors = doctors;
        this.surgeryRooms = surgeryRooms;
        this.allocations = allocations;
    }

    public boolean allocate(Allocation newAllocation) {
        if (newAllocation == null) {
            System.out.println("Allocation cannot be null");
            return false;
        }
        if (!canAllocateDoctorToRoom(newAllocation.getDoctor().getSpecialization(), newAllocation.getRoom().getType())) {
            System.out.println("Doctor doesnt match room");
            return false;
        }
        if (!respectTimeStamp(newAllocation.getRoom().getType(), newAllocation.getPeriod())) {
            System.out.println("Period overlaps");
            return false;
        }
        for (Allocation allocation : allocations) {
            if (newAllocation.overlaps(allocation.getPeriod())) {
                return false;
            }
        }
        allocations.add(newAllocation);
        allocations.sort(Comparator.comparingInt(a -> a.getPeriod().rawValue()));
        return true;
    }

    public boolean respectTimeStamp(SurgeryRoomType type, Period period) {
        if (type == null || period == null) {
            System.out.println("Time input is null");
            return false;
        }
        if (period.startHour < 6 || period.endHour > 22) {
            System.out.println("Period doesnt respect hospital work hours");
            return false;
        }
        if (period.endHour - period.startHour < type.minimumTime()) {
            System.out.println("Surgery cannot be this fast");
            return false;
        }
        return true;
    }

    private boolean canAllocateDoctorToRoom(Specialization specialization, SurgeryRoomType type) {
        if (specialization == null || type == null) {
            System.out.println("Specialization input is null");
            return false;
        }
        if (specialization == Specialization.dermatologist && type != SurgeryRoomType.small) {
            System.out.println("Dermatologist can only work in small rooms");
            return false;
        }
        if ((specialization == Specialization.cardiologist ||
                specialization == Specialization.neurologist) &&
                type == SurgeryRoomType.small) {
            System.out.println("Cardiologist and neurologist cannot work in small rooms");
            return false;
        }
        return true;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    public List<SurgeryRoom> getSurgeryRooms() {
        return surgeryRooms;
    }

    public void setSurgeryRooms(List<SurgeryRoom> surgeryRooms) {
        this.surgeryRooms = surgeryRooms;
    }

    public List<Allocation> getAllocations() {
        return allocations;
    }

    public void setAllocations(List<Allocation> allocations) {
        this.allocations = allocations;
    }

    @Override
    public String toString() {
        return "Hospital { " +
                "doctors=" + doctors +
                ", surgeryRooms=" + surgeryRooms +
                ", allocations=" + allocations +
                " }";
    }

}
