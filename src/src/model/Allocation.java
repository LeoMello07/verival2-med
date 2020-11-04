package src.model;

public class Allocation {
    private Doctor doctor;
    private SurgeryRoom room;
    private Period period;

    public Allocation(Doctor doctor, SurgeryRoom room, Period period) {
        this.doctor = doctor;
        this.room = room;
        this.period = period;
    }

    public Boolean overlaps(Period period) {
        return this.period.overlaps(period);
    }

    public Double getCost() {
        return room.getType().price() * (period.endHour - period.startHour) *
                (room.getType() == SurgeryRoomType.high_risk && period.startHour < 10 ? 0.9 : 1 ) ;
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

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    @Override
    public String toString() {
        return "Allocation { " +
                "doctor=" + doctor.getName() +
                ", room=" + room.getName() +
                ", period=" + period +
                ", price=" + getCost() +
                " }";
    }
}
