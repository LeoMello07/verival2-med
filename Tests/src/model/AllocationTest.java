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
}