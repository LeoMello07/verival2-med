package src.controller;

import src.model.Allocation;
import src.model.Doctor;
import src.model.SurgeryRoom;
import src.model.Time;

import java.util.ArrayList;
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

    public boolean allocate(Doctor doctor, SurgeryRoom room, Time time) {
        for (Allocation allocation : allocations) {
            if (allocation.getTime().compareTo(time) > 0) {
                return false;
            }
        }
        allocations.add(new Allocation(doctor, room, time));
        allocations.sort( (a1, a2) -> a1.getTime().compareTo(a2.getTime()) );
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
