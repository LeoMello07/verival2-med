package src.model;

import java.util.Date;

public class Allocation {
    Doctor doctor;
    SurgeryRoom room;
    Time time;

    public Allocation(Doctor doctor, SurgeryRoom room, Time time) {
        this.doctor = doctor;
        this.room = room;
        this.time = time;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public SurgeryRoom getRoom() {
        return room;
    }

    public void setRoom(SurgeryRoom room) {
        this.room = room;
    }

    public Time getTime() {
        return time;
    }

    public void setDate(Time time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Allocation { " +
                "doctor=" + doctor +
                ", room=" + room +
                ", date=" + time +
                " }";
    }
}
