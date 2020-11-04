package src.controller;

import src.model.*;

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

    public Hospital(List<Doctor> doctors, List<SurgeryRoom> surgeryRooms, List<Allocation> allocations) {
        this.doctors = doctors;
        this.surgeryRooms = surgeryRooms;
        this.allocations = allocations;
    }

    public boolean allocate(Doctor doctor, SurgeryRoom room, Period period) {
        if (!canAllocateDoctorToRoom(doctor.getSpecialization(), room.getType())) {
            return false;
        }
        if (!respectTimeStamp(room.getType(), period)) {
            return false;
        }
        for (Allocation allocation : allocations) {
            if (allocation.overlaps(period)) {
                return false;
            }
        }
        allocations.add(new Allocation(doctor, room, period));
        allocations.sort( (a1, a2) -> a1.getPeriod().rawValue() - a2.getPeriod().rawValue() );
        return true;
    }

    public boolean respectTimeStamp(SurgeryRoomType type, Period period) {
        if (type == null || period == null) { return false; }
        if (period.startHour < 6 || period.endHour > 22) { return false; }
        return period.endHour - period.startHour > type.minimumTime();
    }

    private boolean canAllocateDoctorToRoom(Specialization specialization, SurgeryRoomType type) {
        if (specialization == null || type == null) {
            return false;
        }
        if (specialization == Specialization.dermatologist && type != SurgeryRoomType.small) {
            return false;
        }
        if ((specialization == Specialization.cardiologist ||
                specialization == Specialization.neurologist) &&
                type == SurgeryRoomType.small) {
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
