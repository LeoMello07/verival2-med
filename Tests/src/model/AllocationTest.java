package src.model;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

public class AllocationTest extends TestCase {

    private Doctor doctor;
    private SurgeryRoom room;
    private Period period;
    private Allocation allocation;

    @BeforeEach
    public void setUp() {
        period = new Period(0, 10, 20);
    }

    @Test
    public void testTenHoursWithDermatologistSmallRoom() {
        doctor = new Doctor("Luiz", 127, Specialization.dermatologist);
        room = new SurgeryRoom("Orpheus", SurgeryRoomType.small);
        allocation = new Allocation(doctor, room, period);

        final Double expected = 400*10.0;
        final Double actual =  allocation.getCost();
        assertEquals(expected, actual);
    }

    @Test
    public void testFiveHoursNeurologistBigRoom() {
        doctor = new Doctor("Luiz", 127, Specialization.neurologist);
        room = new SurgeryRoom("Orpheus", SurgeryRoomType.big);
        allocation = new Allocation(doctor, room, period);
        period.startHour = 15;

        final Double expected = 650*5.0;
        final Double actual =  allocation.getCost();
        assertEquals(expected, actual);
    }

    @Test
    public void testFiveHoursCardiologistBigRoom() {
        doctor = new Doctor("Luiz", 127, Specialization.cardiologist);
        room = new SurgeryRoom("Orpheus", SurgeryRoomType.big);
        allocation = new Allocation(doctor, room, period);
        period.startHour = 15;

        final Double expected = 650*5.0;
        final Double actual =  allocation.getCost();
        assertEquals(expected, actual);
    }

    @Test
    public void testFiveHoursNeurologistHighRiskRoom() {
        doctor = new Doctor("Luiz", 127, Specialization.neurologist);
        room = new SurgeryRoom("Orpheus", SurgeryRoomType.high_risk);
        allocation = new Allocation(doctor, room, period);
        period.startHour = 15;

        final Double expected = 1200*5.0;
        final Double actual =  allocation.getCost();
        assertEquals(expected, actual);
    }

    @Test
    public void testFiveHoursCardiologistHighRiskRoom() {
        doctor = new Doctor("Luiz", 127, Specialization.cardiologist);
        room = new SurgeryRoom("Orpheus", SurgeryRoomType.high_risk);
        allocation = new Allocation(doctor, room, period);
        period.startHour = 15;

        final Double expected = 1200*5.0;
        final Double actual =  allocation.getCost();
        assertEquals(expected, actual);
    }

    @Test
    public void testTenHoursNeurologistHighRiskRoomWithDiscount() {
        doctor = new Doctor("Luiz", 127, Specialization.neurologist);
        room = new SurgeryRoom("Orpheus", SurgeryRoomType.high_risk);
        allocation = new Allocation(doctor, room, period);
        period.startHour = 8;
        period.endHour = 18;

        final Double expected = 1200*10*0.9;
        final Double actual =  allocation.getCost();
        assertEquals(expected, actual);
    }

    @Test
    public void testTenHoursCardiologistHighRiskRoomWithDiscount() {
        doctor = new Doctor("Luiz", 127, Specialization.cardiologist);
        room = new SurgeryRoom("Orpheus", SurgeryRoomType.high_risk);
        allocation = new Allocation(doctor, room, period);
        period.startHour = 8;
        period.endHour = 18;

        final Double expected = 1200*10*0.9;
        final Double actual =  allocation.getCost();
        assertEquals(expected, actual);
    }

}